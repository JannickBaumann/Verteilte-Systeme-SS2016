import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.*;


public class TargetDevice {

	private DatagramSocket socket;
	private InetAddress forwarderIP, signalingServerIP;
	private int forwarderPort, signalingServerPort;
	
	private DatagramPacket pOut, pIn, pLongPoll;
	
	private String sessionID = "";
	private String deviceID = "FF88";
	
	private boolean hasSession;
	
	private JSONObject jsonobject;
	
	private Heartbeat heartbeat;
	private Device device;
	
	/*
	 * ============== Statische Methoden fuer JSON ===========================================
	 */
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}
	
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	}
	
	/*
	 * ============== ENDE Statische Methoden fuer JSON ======================================
	 */
	
	TargetDevice()
	{
		try
		{
			socket = new DatagramSocket();
			socket.setSoTimeout(15000);
			forwarderIP = InetAddress.getByName("139.13.81.104");
			forwarderPort = 31451;
			signalingServerIP = InetAddress.getByName("139.13.81.104");
			signalingServerPort = 31880;
			hasSession = false;
		}
		catch(SocketException e)
		{
			System.out.println("SocketException: " + e.getMessage());
		}
		catch (UnknownHostException e) 
		{
			System.out.println("UnknownHostException: " + e.getMessage());
		}
	}
	
	public void longPollForwarder()
	{
		ByteArrayOutputStream streambuffer = new ByteArrayOutputStream();
		DataOutputStream outputstream = new DataOutputStream(streambuffer);
		
		System.out.println("Sende Longpoll zum Forwarder...");
		
		/*
		 * boolean true signalisiert long polling
		 * boolean false signalisert z.B Daten
		 */
		
		try 
		{
			outputstream.writeBoolean(true);
			outputstream.writeChars(sessionID);
			byte[] packetData = streambuffer.toByteArray();
			pLongPoll = new DatagramPacket(packetData, packetData.length, forwarderIP, forwarderPort);
			System.out.println("Data Length: " + pLongPoll.getLength());
			socket.send(pLongPoll);
		}
		catch (IOException e) 
		{
			System.out.println("Write boolean into packet: " + e.getMessage());
		}
		
	}
	
	/*
	 * Die innere Klasse Device stellt die prinzipiellen Grundfunktionen
	 * des Endgeraetes zur Verfuegung. 
	 */
	class Device implements Runnable
	{
		
		public void sendPacket()
		{		
			String msg = "Hallo Client";
			pOut = new DatagramPacket(new byte[2048], 2048, forwarderIP, forwarderPort);
			pOut.setData(msg.getBytes());
			
			try 
			{
				System.out.println("Verschicke Paket an Server...");
				socket.send(pOut);
				pOut = null;
				System.out.println("Paket abgeschickt.");
			}
			catch (IOException e) 
			{
				System.out.println("IOException: " + e.getMessage());
			}
		}
		
		public boolean checkID(String s)
		{
			if(s.length() == 36 && s.charAt(8) == '-' && s.charAt(13) == '-' && s.charAt(18) == '-' && s.charAt(23) == '-')
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		// recievePacket() empfaengt Daten vom Endgeraet
		public void recievePacket()
		{
			pIn = new DatagramPacket(new byte[2048], 2048);
			
			try 
			{
				System.out.println("Warte auf Antwort...");
				socket.receive(pIn);
				System.out.println("Antwort erhalten von Server " + pIn.getAddress() + "::" + pIn.getPort());
				String s = new String(pIn.getData());
				if(checkID(s))
				{
					sessionID = s;
				}
				else
				{
					System.out.println("Nachricht:\t" + s);
				}
				pIn = null;
			}
			catch (IOException e) 
			{
				System.out.println("IOException: " + e.getMessage());
			}
		}
		
		public void closeSocket()
		{
			socket.close();
		}
		
		public void showSessionID()
		{
			System.out.println("Meine SessionID ist: " + sessionID);
		}
		
		public void run() 
		{
			Scanner sc = new Scanner(System.in);
			while(true)
			{
				System.out.print("-> ");
				String input = sc.next();
				System.out.println("\n");

				if(input.equals("showid"))
				{
					this.showSessionID();
				}
				if(input.equals("empfangen"))
				{
					this.recievePacket();
				}
				if(input.equals("senden"))
				{
					this.sendPacket();
					System.out.println("Paket abgeschickt\n");
				}
				if(input.equals("exit"))
				{
					this.closeSocket();
					sc.close();
					System.exit(0);
				}
			}
		}
		
	}
	
	public Device createDeviceObject()
	{
		device = new Device();
		return device;
	}
	
	/*
	 * Die Klasse Heartbeat bewirkt einen Poll auf den Signaling Server
	 * alle 15 Sekunden. Dadurch bleibt das Endgeraet dem Server bekannt
	 * und damit ansprechbar.
	 */
	class Heartbeat implements Runnable
	{
		public void run() 
		{
			try 
			{
				/*
				 * Die innere while-Schleife ist solange aktiv wie keine Session
				 * eingetragen ist.
				 */
				while(true)
				{
					while(!hasSession)
					{
						URL url = new URL("http://139.13.81.104:31880/heartbeat");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("POST");
						
						jsonobject = readJsonFromUrl("http://139.13.81.104:31880/heartbeat?did=" + deviceID);
						sessionID = (String) jsonobject.get("message");
						if(sessionID.equals(""))
						{
							System.out.println("Keine Session");
							hasSession = false;
							Thread.sleep(15000);
							System.out.println("*bomp*");
						}
						else
						{
							System.out.println("Session erhalten");
							hasSession = true;
							longPollForwarder();
						}
					}
					jsonobject = readJsonFromUrl("http://139.13.81.104:31880/heartbeat?did=" + deviceID);
					if(jsonobject.get("message").equals(""))
					{
						System.out.println("Session beendet.");
						sessionID = "";
						hasSession = false;
					}
					System.out.println("*bomp*");
					Thread.sleep(15000);
				}
			}
			catch (MalformedURLException e) 
			{
				System.out.println(e.getMessage());
			}
			catch (IOException e) 
			{
				System.out.println(e.getMessage());
			} 
			catch (InterruptedException e) 
			{
				System.out.println(e.getMessage());
			} 
			catch (JSONException e) 
			{
				System.out.println(e.getMessage());
			}
		}		
	}
	
	public Heartbeat createHeartbeatObject()
	{
		heartbeat = new Heartbeat();
		return heartbeat; 
	}
	
	public static void main(String[] args) throws InterruptedException {
		TargetDevice td = new TargetDevice();
		
		Thread t1 = new Thread(td.createHeartbeatObject());
		Thread t2 = new Thread(td.createDeviceObject());
		
		t1.start();
		System.out.println("Heartbeat gestartet\n");
		t2.start();
		System.out.println("Device gestartet\n");
	}

}
