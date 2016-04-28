package rest_calls;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Session {
	String id;
	HashMap<String, SessionObj> ipIdMap = new HashMap<>();
	HashMap<String, String> devMap = new HashMap<>();
	HashMap<String, String> devMapE = new HashMap<>();
	
	  @PostConstruct
	  public void loadHashmap() {
		  loadObjects();
	  }

	  @PreDestroy
	  public void saveHashmap() {
		  saveObjects();
	  }
	
	 /*
	 * Erstellen einer Session und weiterleiten zu einer leeren Seite mit
	 * SessionID
	 */
	@RequestMapping("session/create")
	public ModelAndView creatSession(HttpServletRequest request) {
		id = UUID.randomUUID().toString();
		ipIdMap.put(id, new SessionObj(id));
		System.out.println("Neu: "+ipIdMap.get(id).toString());
		return new ModelAndView("redirect:" + id);
	}

	@RequestMapping("session/{sessionID}")
	public String session(@PathVariable(value = "sessionID") String sessionID) {
		if (ipIdMap.containsKey(sessionID)) {
			return json_msg(000, sessionID);
		}
		return "Ungültige Session";
	}

	/*
	 * Löschen der aktuellen Session
	 * und Session aus Devices löschen die man genutzt hat
	 */
	@RequestMapping("session/{sessionID}/delete")
	public String delete(@PathVariable(value = "sessionID") String sessionID) {
		if (sessionPruefen(sessionID)) {
			ipIdMap.remove(sessionID);
			if(devMap.containsValue(sessionID)){
				devMap.put(devMapE.get(sessionID),"unused");
			}
			return json_msg(202, "Erfolgreich gelöscht");
		}
		return json_msg(404, "fehler");
	}

	/*
	 * Session Info ausgeben alle Arttribute von einem SessionObj in json
	 * ausgeben
	 */
	@RequestMapping("session/{sessionID}/sessionInfo")
	public String sessionInfo(@PathVariable(value = "sessionID") String sessionID) {
		if (sessionPruefen(sessionID)) {
			return ipIdMap.get(sessionID).toJSON();
		}
		return "Ungültige Session";
	}
	/*
	 * longpoll über REST für die Devices 
	 */
	@RequestMapping("heartbeat")
	public String heartBeat(@RequestParam(value = "did") String did){
		String dID = did;
		if(devMap.containsKey(dID)){
			if(!devMap.get(dID).equals("unused")){
				return json_msg(1,devMap.get(dID));
			}
			return json_msg(0,"");
		}
		devMap.put(dID, "unused");
		return json_msg(2, "");
	}
	
	/*
	 * Das Gerät wählen mit dem man verbunden werden möchte
	 * .../session/{sessionID}/dev?did=1
	 * sessionID wird per UDP an das gewählt Gerät übertragen
	 */
	@RequestMapping("session/{sessionID}/dev")
	public String chooseDev(@PathVariable(value = "sessionID") String sessionID,
					   	    @RequestParam(value="did") String did) {
		String dID = did;
		if(sessionPruefen(sessionID)){
			if(devMap.containsKey(dID)){
				devMapE.put(sessionID, dID);
				devMap.put(dID,sessionID);
				return json_msg(0,"Session wird an Dev übergeben");
			}
			return json_msg(0,"ungültige DID");
		}
		return json_msg(0,"ungültige SessionID");
	}
	
	/*
	 * Zum überprüfen der SessionID ob sie Gültig ist
	 */
	public boolean sessionPruefen(String session) {
		if (ipIdMap.containsKey(session)) {
			return true;
		}
		return false;
	}

	// Speichern der Session Objekte Hashmap auf Festplatte
	private boolean saveObjects() {

		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		
		try {
			
			fileOut = new FileOutputStream("sessions.backup");
			
			try {
				
				objectOut = new ObjectOutputStream(fileOut);
				objectOut.writeObject(ipIdMap);
				objectOut.close();
				fileOut.close();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				System.out.println("Failed to save Session Objects.");
				return false;
				
			}
			
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
			System.out.println("Failed to save Session Objects.");
			return false;
			
		}

		System.out.println("Successfully saved Session Objects.");
		return true;

	}

	// Laden der Session Objekte Hashmap von Festplatte
	private boolean loadObjects() {

		FileInputStream fileIn;
		ObjectInputStream objectIn;
		
		try {
			
			fileIn = new FileInputStream("sessions.backup");
			
			try {
				
				objectIn = new ObjectInputStream(fileIn);
				
				try {
					
					ipIdMap = (HashMap) objectIn.readObject();
					objectIn.close();
					fileIn.close();
					
				} catch (ClassNotFoundException | IOException e1) {
				
					e1.printStackTrace();
					System.out.println("Failed to load Session Objects.");
					return false;
					
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
				System.out.println("Failed to load Session Objects.");
				return false;
				
			}
			
		} catch (FileNotFoundException e) {
			
			System.out.println("Failed to load Session Objects. (No File found)");
			return false;
			
		}
		
		System.out.println("Successfully loaded Session Objects.");
		return true;

	}

	private String json_msg(int code, String msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("code", code);
			obj.put("message", msg);

		} catch (JSONException e) {
			System.out.println("ERROR: parsing JSON in class (UserDeviceMapper)");
			return "{\"code\":500,\"message\":\"Internal Server Error\"}";
		}
		return obj.toString();
	}
	
	
}
