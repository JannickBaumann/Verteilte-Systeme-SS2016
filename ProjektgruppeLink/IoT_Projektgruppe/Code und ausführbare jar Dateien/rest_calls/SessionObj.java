package rest_calls;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.json.JSONException;
import org.json.JSONObject;

public class SessionObj implements Serializable {
	//private String ip;
	//private int port;
	private LocalDateTime time;
	private String sessionID;
	
	public SessionObj(String sessionID){
		this.sessionID = sessionID;
		time = LocalDateTime.now();
	}
	public String getSessionID(){return sessionID;}
	public LocalDateTime getTime(){return time;}
	
	public String toString(){
		return sessionID+time.toString();
	}
	
	public String toJSON(){
		JSONObject obj = new JSONObject();
		try {
			obj.put("sessionID: ",sessionID);
			obj.put("Zeit: ", time);
			
		} catch (JSONException e) {
			System.out.println("ERROR: parsing JSON in class (UserDeviceMapper)");
			return "{\"code\":500,\"message\":\"Internal Server Error\"}";
		}
    	return obj.toString();
	}
}
