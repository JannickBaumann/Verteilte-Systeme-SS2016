package presenceServer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Client {

	private int ID;
	private String address;
	private boolean state;
	private long timestamp;
	private String timeString;
	
	public Client(String address, boolean state) {
		
		this.ID = PresenceListHandler.getInstance().getClientList().size();
		this.address = address;
		this.state = state;
		this.timestamp = System.currentTimeMillis();
		updateTimeString();
		
	}
	public Client(Client a) {
		
		this.ID = a.getID();
		this.address = a.getAddress();
		this.state = a.getState();
		this.timestamp = System.currentTimeMillis();
		updateTimeString();
		
	}

	public int getID() {
		
		return ID;
		
	}

	public String getAddress() {
		
		return address;
		
	}

	public boolean getState() {
		
		return state;
		
	}
	
	public void setState(boolean state) {
		
		this.state = state;	
		
	}
	
	public long getTimeStamp() {
		
		return timestamp;
		
	}
	
	public void updateTimeStamp() {
		
		this.timestamp = System.currentTimeMillis();
		updateTimeString();
		
	}
	
	public String getTimeString() {
		
		return timeString;
		
	}
	
	public void updateTimeString() {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.timestamp);
		this.timeString = dateFormatter.format(cal.getTime());		
		
	}
	
}
