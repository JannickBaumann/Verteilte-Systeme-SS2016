package presenceServer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

	public static void outputConsole(String msg) {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		
		System.out.println("[" + dateFormatter.format(cal.getTime()) + "] " + msg);
		
	}
	
}
