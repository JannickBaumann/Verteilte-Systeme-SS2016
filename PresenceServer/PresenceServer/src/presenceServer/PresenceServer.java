package presenceServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class PresenceServer implements Runnable {

	private DatagramSocket serverSocket;
	private byte[] receiveData;
	private PresenceListHandler listHandler;
	
	public void start() {

		try {
			
			serverSocket = new DatagramSocket(9876);
			
		} catch (SocketException e) {
			
			Util.outputConsole("Failed to create Socket - " + e.getMessage());
			System.exit(-1);
			
		}
		receiveData = new byte[1024];
		
		new Thread(PresenceListHandler.getInstance()).start();;
		
		Util.outputConsole("Server ready to receive..");

	}

	@Override
	public void run() {
	
		while (true) {
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

			try {
				
				serverSocket.receive(receivePacket);
				
			} catch (IOException e) {
				
				Util.outputConsole("Socket Error while receiving - " + e.getMessage());
				System.exit(-1);
				
			}
			
			Client data = new Client( De_Encoder.decode(receivePacket.getData()));
			Util.outputConsole("RECEIVED: " + data);
			DatagramPacket ack = new DatagramPacket(De_Encoder.encode());
			serverSocket.send(ack);
			if(!listHandler.hasClient(data.getAddress())) {
				
				//Client c = new Client("127.0.0.2:55040", true);
				listHandler.insertClient(data);
				
			}
			else {
				
				Client c = listHandler.getClient(data.getAddress());
				
				if(c != null) {
					
					c.updateTimeStamp();
					
				}
				
				
			}
			
		}
		
	}
	
}
