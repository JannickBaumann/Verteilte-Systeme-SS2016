package presenceServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class Main {

	public static void main(String[] args) {
		
		HttpServer server;
		
		try {
			
			server = HttpServer.create(new InetSocketAddress( 80 ), 0 );
			server.createContext( "/", new ResponseHandler());
		    server.start();
			
		} catch (IOException e) {
		
			Util.outputConsole("Failed to create HTTP Server - " + e.getMessage());
			System.exit(-1);
			
		}
	    

	    PresenceServer pServer = new PresenceServer();
	    pServer.start();
	    
	    PresenceListHandler.getInstance().insertClient(new Client("127.0.0.1:55042", true));
	    PresenceListHandler.getInstance().insertClient(new Client("127.0.0.1:55043", true));
	    PresenceListHandler.getInstance().insertClient(new Client("127.0.0.1:55044", false));
	    PresenceListHandler.getInstance().insertClient(new Client("127.0.0.1:55045", true));
	    PresenceListHandler.getInstance().insertClient(new Client("127.0.0.1:55046", true));
	    PresenceListHandler.getInstance().insertClient(new Client("127.0.0.1:55047", true));
	    PresenceListHandler.getInstance().insertClient(new Client("127.0.0.1:55048", true));
	    PresenceListHandler.getInstance().insertClient(new Client("127.0.0.1:55049", true));
	    PresenceListHandler.getInstance().insertClient(new Client("127.0.0.1:55050", true));
	    PresenceListHandler.getInstance().insertClient(new Client("127.0.0.1:55051", false));
	    PresenceListHandler.getInstance().insertClient(new Client("127.0.0.1:55052", false));
	    
	}

}
