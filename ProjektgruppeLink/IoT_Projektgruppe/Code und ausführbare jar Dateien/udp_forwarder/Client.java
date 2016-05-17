import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import org.json.*;


public class Client {

	private DatagramSocket socket;
	private InetAddress forwarderIP, signalingServerIP;
	private int forwarderPort, signalingServerPort;
	
	private DatagramPacket pOut, pIn, pLongPoll, pRequest;
	
	private String sessionID;
	private String deviceID;
	private int userID = 10;
	
	private JSONObject jsonobject;
	
	private boolean connectionEstablished = false;
	
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
	
	public static String excutePost(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;  
        try {
          //Create connection
          URL url = new URL(targetURL);
          connection = (HttpURLConnection)url.openConnection();
          connection.setRequestMethod("POST");
          connection.setRequestProperty("Content-Type",
              "application/x-www-form-urlencoded");

          connection.setRequestProperty("Content-Length",
              Integer.toString(urlParameters.getBytes().length));
          connection.setRequestProperty("Content-Language", "en-US");  

          connection.setUseCaches(false);
          connection.setDoOutput(true);

          //Send request
          DataOutputStream wr = new DataOutputStream (
              connection.getOutputStream());
          wr.writeBytes(urlParameters);
          wr.close();

          //Get Response  
          InputStream is = connection.getInputStream();
          BufferedReader rd = new BufferedReader(new InputStreamReader(is));
          StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
          String line;
          while((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
          }
          rd.close();
          return response.toString();
        } catch (Exception e) {
          e.printStackTrace();
          return null;
        } finally {
          if(connection != null) {
            connection.disconnect();
          }
        }
      }

	
	Client()
	{
		try
		{
			socket = new DatagramSocket();
			socket.setSoTimeout(15000);
			forwarderIP = InetAddress.getByName("139.13.81.104");
			forwarderPort = 31451;
			signalingServerIP = InetAddress.getByName("139.13.81.104");
			signalingServerPort = 31880;
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

	public void setDevID(String s)
	{
		deviceID = s;
	}
	
	public boolean isConnectionEstablished()
	{
		return connectionEstablished;
	}
	
	public String getSessionID()
	{
		return sessionID;
	}
	
	public void longPoll()
	{
		ByteArrayOutputStream streambuffer = new ByteArrayOutputStream();
		DataOutputStream outputstream = new DataOutputStream(streambuffer);
		
		System.out.println("...start longpolling...");
		
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
	 * sendRequest ist ausschliesslich fuer die Kommunikation
	 * mit dem Signalisierungsserver gedacht
	 */
	public void sendRequest()
	{
		try 
		{	
			String tmp = excutePost("http://139.13.81.104:31880/session/create", "test");
			jsonobject = new JSONObject(tmp);
			System.out.println("JSON: " + jsonobject.toString());
			sessionID = (String) jsonobject.get("message");
	
			excutePost("http://139.13.81.104:31880/session/" + sessionID + "/dev?did=" + deviceID, "test");
			
			connectionEstablished = true;
		}
		catch (JSONException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteSession()
	{
		excutePost("http://139.13.81.104:31880/session/" + sessionID + "/delete", "test");
		sessionID = "";

	}
	
	public void sendPacket()
	{		
		String msg = "Hallo Device Target";
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
	
	// recievePacket() empfaengt Daten vom Endgeraet
	public void recievePacket()
	{
		pIn = new DatagramPacket(new byte[2048], 2048);
		
		try 
		{
			System.out.println("Warte auf Antwort...");
			socket.receive(pIn);
			System.out.println("Antwort erhalten von Server " + pIn.getAddress() + "::" + pIn.getPort());
			String data = new String(pIn.getData());
			System.out.println("Daten: " + data);
			pIn = null;
		}
		catch (IOException e) 
		{
			System.out.println("IOException: " + e.getMessage());
		}
	}
	
	public void showSessionID()
	{
		System.out.println("Meine SessionID ist: " + sessionID);
	}
	
	public void closeSocket()
	{
		socket.close();
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Client c = new Client();
		Scanner sc = new Scanner(System.in);
		System.out.println("DeviceID angeben: ");
		String devID;
		devID = sc.next();
		c.setDevID(devID);
		
		while(true)
		{			
			System.out.print("-> ");
			String input = sc.next();
			System.out.println("\n");
			if(input.equals("longpoll"))
			{
				c.longPoll();
			}
			if(input.equals("showid"))
			{
				c.showSessionID();
			}
			if(input.equals("request"))
			{
				c.sendRequest();
				System.out.println("Warte auf SessionID");
				System.out.println("SessionID: " + c.getSessionID());
			}
			if(input.equals("empfangen"))
			{
				c.recievePacket();
			}
			if(input.equals("delete"))
			{
				c.deleteSession();
			}
			if(input.equals("senden"))
			{
				if(!c.isConnectionEstablished())
				{
					System.out.println("Noch keine Session erstellt.");
				}
				else
				{
					c.sendPacket();
					System.out.println("Warte 2 Sekunden...");
					Thread.sleep(2000);
				}
				
			}
			if(input.equals("exit"))
			{
				c.closeSocket();
				System.exit(0);
			}
		}		
	}

}
