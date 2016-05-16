package presenceServer;

import java.util.ArrayList;

public class PresenceListHandler implements Runnable {

	private ArrayList<Client> clientList = new ArrayList<Client>();
	private long timeOut = 30000;

	private static PresenceListHandler instance;

	private PresenceListHandler() {};
	
	//Nur ein Objekt dieser Klasse möglich, Zugriff auf Liste nur darüber möglich
	public static synchronized PresenceListHandler getInstance() {
		
	    if (instance == null) {
	    	
	        instance = new PresenceListHandler();
	        
	    }
	    
	    return instance;
	    
	}
	
	@Override
	public synchronized void run() {

		while (true) {

			for (Client c : clientList) {

				if (c.getState() && System.currentTimeMillis() - c.getTimeStamp() > this.timeOut) {

					c.setState(false);
					Util.outputConsole("Client " + c.getID() + " timed out.");

				}

			}

			try {
				
				wait(1000);
				
			} catch (InterruptedException e) {
			
				e.printStackTrace();
				
			}
			
		}

	}

	public void insertClient(Client c) {

		clientList.add(c);

	}

	public Client getClient(String address) {

		for (Client c : clientList) {

			if (c.getAddress().equals(address)) {

				return c;

			}

		}

		return null;

	}

	public boolean hasClient(String address) {

		for (Client c : clientList) {

			if (c.getAddress().equals(address)) {

				return true;

			}

		}

		return false;

	}

	public ArrayList<Client> getClientList() {

		return clientList;

	}

}
