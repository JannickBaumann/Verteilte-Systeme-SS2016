package rest_calls;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

@RestController
public class UserDeviceMapper {
	
	private HashMap<Long, Set<Long>> user_deviceID = new HashMap<Long, Set<Long>>();
	
	/* Erstellen eines neuen Nutzers mit der UID
	 * Bsp: http://localhost:1880/adduser?uid=42
	 */
    @RequestMapping("/adduser")
    public String adduser(@RequestParam(value="uid") String uid) {
    	long uidl;
    	Set<Long> uid_ec;
    	
    	try{
    		uidl = Long.valueOf(uid).longValue();
    	} catch (NumberFormatException e) { 
    		return json_msg (400,"wrong number format (long)");
    	}
    	
    	uid_ec = user_deviceID.get(uidl);
    	
    	if (uid_ec == null) {
    		Set<Long> tmp = new HashSet<Long>();
    		user_deviceID.put(uidl, tmp);
    	} else {
    		return json_msg (400,"uid already existing");
    	}
    	
    	return json_msg (201,"user successfully created");
    }
    
	/* Löschen eines Nutzers mit der UID
	 * Bsp: http://localhost:1880/deleteuser?uid=42
	 */
    @RequestMapping("/deleteuser")
    public String deleteuser(@RequestParam(value="uid") String uid) {
    	long uidl;
    	Set<Long> uid_ec;
    	
    	try{
    		uidl = Long.valueOf(uid).longValue();
    	} catch (NumberFormatException e) { 
    		return json_msg (400,"wrong number format (long)");
    	}
    	
    	uid_ec = user_deviceID.get(uidl);
    	
    	if (uid_ec != null) {
    		user_deviceID.remove(uidl);
    	} else {
    		return json_msg (400,"uid not found");
    	}
    	
    	return json_msg (200, "user successfully deleted");
    }
    
    /* Hinzufügen eines Devices mit dem device ID zu einem Nutzer (uid)
	 * Bsp: http://localhost:1880/adddevices?uid=42&did=1,2,3,4
	 */
    @RequestMapping("/adddevices")
    public String adddevice(@RequestParam(value="uid") String uid, @RequestParam(value="did") String did) {
    	long uidl;
    	Set<Long> tmp;
    	String[] did_array = did.split(",");
    	long[] didl_array = new long[did_array.length];
    	
    	try{
    		uidl = Long.valueOf(uid).longValue();
    		for(int i=0;i < did_array.length;i++)
    			didl_array[i] = Long.valueOf(did_array[i]).longValue();
  
    	} catch (NumberFormatException e) { 
    		return json_msg (400,"wrong number format (long)");
    	}
    	
    	tmp = user_deviceID.get(uidl);
    	
    	if (tmp != null) {
    		for(int i=0;i < didl_array.length;i++)
    			tmp.add(didl_array[i]);
    		
    		user_deviceID.put(uidl, tmp);
    	} else {
    		return json_msg (400,"uid not found");
    	}
    	
    	return json_msg (200,"devices successfully added");
    }
    
    /* Löschen von Devices mit dem device ID zu einem Nutzer (uid)
	 * Bsp: http://localhost:1880/removedevices?uid=42&did=1,2,3,4
	 */
    @RequestMapping("/removedevices")
    public String removedevices(@RequestParam(value="uid") String uid, @RequestParam(value="did") String did) {
    	long uidl;
    	long iterl;
    	Set<Long> tmp;
    	boolean rm = false;
    	boolean run = true;
    	String[] did_array = did.split(",");
    	long[] didl_array = new long[did_array.length];
    	
    	try{
    		uidl = Long.valueOf(uid).longValue();
    		for(int i=0;i < did_array.length;i++)
    			didl_array[i] = Long.valueOf(did_array[i]).longValue();
  
    	} catch (NumberFormatException e) { 
    		return json_msg (400,"wrong number format (long)");
    	}
    	
    	tmp = user_deviceID.get(uidl);
    	
    	if (tmp != null) {
    		for (Iterator<Long> iterator = tmp.iterator(); iterator.hasNext();) {
    			iterl = iterator.next();
    		    for(int i=0;i < didl_array.length && run;i++){
    		    	if(iterl==didl_array[i]){
    		    		rm = true;
    		    		run = false;
    		    	}
    		    }
    		    if(rm){
    		    	 iterator.remove();
    		    }  
    		    rm = false;
    		    run = true;
    		}
    		
    		user_deviceID.put(uidl, tmp);
    	} else {
    		return json_msg (400,"uid not found");
    	}
    	
    	return json_msg (200, "devices successfully removed");
    }
    
	/* Alle Devices einer uid anzeigen
	 * Bsp: http://localhost:1880/getdevices?uid=1
	 */
    @RequestMapping("/getdevices")
    public String getdevices(@RequestParam(value="uid") String uid) {
    	long uidl;
    	Set<Long> tmp;
    	JSONObject obj;
    	
    	try{
    		uidl = Long.valueOf(uid).longValue();
    	} catch (NumberFormatException e) { 
    		return json_msg (400,"wrong number format (long)");
    	}
    	tmp = user_deviceID.get(uidl);
    	
    	if (tmp != null) {
    		obj = new JSONObject();
    		
    		try {
	    		obj.put("devices",tmp);
    		} catch (JSONException e) {
    			System.out.println("ERROR: parsing JSON in class (UserDeviceMapper)");
    			return "{\"code\":500,\"message\":\"Internal Server Error\"}";
    		}
    		
    		return obj.toString();
    	} else {
    		return json_msg (400,"uid not found");
    	}
    }
    
    /*
   //Speichern der HashMap auf der Festplatte
   public void save (String path){
	   Properties properties = new Properties();

	   for (Map.Entry<Long, Set<Long>> entry : user_deviceID.entrySet()) {
	       properties.put(entry.getKey(), entry.getValue());
	   }

	   try {
		   properties.store(new FileOutputStream(path), null);
	   } catch (FileNotFoundException e) {
		   System.out.println("ERROR: saving user-device map, file not found in class (UserDeviceMapper)");
	   } catch (IOException e) {
		   System.out.println("ERROR: saving user-device map, IOException (no permission ?) in class(UserDeviceMapper)");
	   }
   }
   
   //Laden der HashMap von der Festplatte
   public void load(String path){
	   user_deviceID = new HashMap<Long, Set<Long>>();
	   Properties properties = new Properties();
	   Set<Long> tmp_set;
	   
	   properties.load(new FileInputStream("data.properties"));

	   for (String key : properties.stringPropertyNames()) {
		   String bla = properties.get(key).toString();
		   tmp_set = new HashSet<Long>(Arrays.asList(bla)); 
		   user_deviceID.put(Long.valueOf(key).longValue(), tmp_set);
	   }
   }*/
   
   @RequestMapping(method = RequestMethod.GET)
   public String defaultfunc() {
	   return json_msg (400,"paramter error.");
   }
    
   private String json_msg (int code, String msg){
    	
    	JSONObject obj = new JSONObject();
    	try {
			obj.put("code",code);
			obj.put("message",msg);
		} catch (JSONException e) {
			System.out.println("ERROR: parsing JSON in class (UserDeviceMapper)");
			return "{\"code\":500,\"message\":\"Internal Server Error\"}";
		}
    	return obj.toString();
    } 
}
