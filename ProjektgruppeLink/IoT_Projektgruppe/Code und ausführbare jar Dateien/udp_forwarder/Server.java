import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;


public class Server {

	private Forwarder forwarder;
	private SessionChecker sessionChecker;
	
	private int port = 1451;
	private DatagramSocket socket;
	
	private String tempClientSessionID;
	
	// Packets
	DatagramPacket incomingPacket;
	DatagramPacket answerPacket;
	
	// Sessions
	private HashMap<String, String[]> list;	// SessionID || IP1, Port1, IP2, Port2
	private LinkedList<String> sessionlist;	// Aktive Sessions
	private HashMap<String, Long> timelist;
	private long TIMEOUT = 120000;
	
	
	public class Forwarder implements Runnable
	{
		Forwarder()
		{
			list = new HashMap<String, String[]>();
			sessionlist = new LinkedList<String>();
			timelist = new HashMap<String, Long>();
		}
		
		public void addToList(String session) 
		{
			//check if client is already in a session to prevent overwriting entries in string array
			String[] sessionValues;
			sessionValues = list.get(session);
			
			
			if( !list.containsKey(session) )
			{
				sessionValues = new String[4];
				sessionValues[0] = incomingPacket.getAddress().getHostAddress();
				sessionValues[1] = String.valueOf(incomingPacket.getPort());
				sessionValues[2] = "";
				sessionValues[3] = "";
				list.put(session, sessionValues);
			}
			else
			{
				if( !(incomingPacket.getAddress().getHostAddress().equals(sessionValues[0]) && Integer.parseInt(sessionValues[1]) == incomingPacket.getPort()) )
				{
					sessionValues = list.get(session);
					sessionValues[2] = incomingPacket.getAddress().getHostAddress();
					sessionValues[3] = String.valueOf(incomingPacket.getPort());
					list.put(session, sessionValues);
					sessionlist.add(session);
					timelist.put(session, System.currentTimeMillis());
					System.out.println("Session " + session + ":");
					System.out.println("IP 1:\t" + sessionValues[0] + "\n" + "Port 1:\t" + sessionValues[1] + "\n" + "IP 2:\t" + sessionValues[2] + "\n" + "Port 2:\t" + sessionValues[3]);
				}
				else
				{
					System.out.println("Client " + incomingPacket.getAddress() + "::" + incomingPacket.getPort() + " ist bereits in der Session " + session);
				}
			}
		}
		
		public InetAddress getDestinationIP() throws UnknownHostException
		{
			InetAddress destIP;
			
			String[] tmp = list.get(tempClientSessionID);
			if(incomingPacket.getAddress().getHostAddress().equals(tmp[0]))
			{
				destIP = InetAddress.getByName(tmp[2]);
			}
			else
			{
				destIP = InetAddress.getByName(tmp[0]);
			}
			return destIP;
		}
		
		public int getDestinationPort()
		{
			int destPort;
			
			String[] tmp = list.get(tempClientSessionID);
			if(incomingPacket.getPort() == Integer.parseInt(tmp[1]))
			{
				destPort = Integer.parseInt(tmp[3]);
			}
			else
			{
				destPort = Integer.parseInt(tmp[1]);
			}
			return destPort;
		}
		
		public void forwardPacket() throws IOException
		{
			InetAddress destIP = getDestinationIP();
			int destPort = getDestinationPort();
			
			answerPacket = new DatagramPacket(incomingPacket.getData(), incomingPacket.getLength(), destIP, destPort);
			socket.send(answerPacket);		
		}

		public int analysePacket()
		{
			/*
			 * return 0 - long poll
			 * return 1 - forwarding
			 */
			ByteArrayInputStream streambuffer = new ByteArrayInputStream(incomingPacket.getData());
			DataInputStream inputstream = new DataInputStream(streambuffer);
			boolean retVal = true;
			
			try 
			{
				retVal = inputstream.readBoolean();
				tempClientSessionID = inputstream.readUTF();

				if(retVal)
				{
					System.out.println("Long Poll detected\n-------------------------\n");
					return 0;
				}
				else
				{
					System.out.println("forwarding data...");
					return 1;
				}
			}
			catch(IOException e)
			{
				
				//System.out.println("analysePacket::IOException" + e.getMessage());
			}
			return 1;
		}
		
		public void run() 
		{
			while(true)
			{			
				try
				{
					socket = new DatagramSocket(port);
					incomingPacket = new DatagramPacket(new byte[2048], 2048);
					System.out.println("Warte auf Daten...\n");
					socket.receive(incomingPacket);
					
					int action = analysePacket();
					
					switch(action)
					{
					case 0:	addToList(tempClientSessionID);
							break;
					case 1: forwardPacket();
							break;
					}
					
					
				}
				catch(IOException e)
				{
					System.out.println("IOException -> socket.recieve()");
					System.out.println(e.getMessage());
				}
				socket.close();
			}
		}
		
	}

	public Forwarder createForwarderObject()
	{
		return new Forwarder();
	}
	
	class SessionChecker implements Runnable
	{

		public void checkClientList() 
		{
			if(!sessionlist.isEmpty())
			{
				for(String i : sessionlist)
				{	
					long tmp = timelist.get(i);
					if(System.currentTimeMillis() - tmp > TIMEOUT)
					{
						System.out.println("Session " + i + " wird entfernt");
						list.remove(i);
						sessionlist.remove(i);
						timelist.remove(i);
					}
				}
			}
		}
		
		public void run() 
		{
			while(true)
			{
				checkClientList();
			}
		}
		
	}
	
	public SessionChecker createSessionCheckerObject()
	{
		return new SessionChecker();
	}

	
	public static void main(String[] args) {
		Server s = new Server();
		
		Thread t1 = new Thread(s.createForwarderObject());
		Thread t2 = new Thread(s.createSessionCheckerObject());
		
		t1.start();
		System.out.println("Forwarder gestartet.");
		t2.start();
		System.out.println("SessionChecker gestartet.");
	}

}
