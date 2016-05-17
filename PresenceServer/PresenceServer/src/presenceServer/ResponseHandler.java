package presenceServer;

import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ResponseHandler implements HttpHandler {
	
	@Override
	public void handle(HttpExchange httpExchange ) throws IOException {
		
		httpExchange.getResponseHeaders().add( "Content-type", "text/html" );
	    String response = createResponse();
	    httpExchange.sendResponseHeaders( 200, response.length() );
	    OutputStream os = httpExchange.getResponseBody();
	    os.write( response.getBytes() );
	    os.close();
	    
	    Util.outputConsole("Web Access");
	    
	}

	public String createResponse() {
		
		String response = "<head><title>Client State Information</title></head>"
				+ "<h1 align='center'>Client State Information</h1>"
				+ "<body style='background-color:#f2f2f2;'>"
				+ "<table border='1' style='width:100%'> "
				+ "<th> ID </th>"
				+ "<th> Address </th>"
				+ "<th> Registered </th>"
				+ "<th> State </th>";
		
		for(Client c : PresenceListHandler.getInstance().getClientList()) {
			
			response = response + "<tr>"
					+ "<td align='center'>" + c.getID() + "</td>"
					+ "<td align='center'>" + c.getAddress() + "</td>"
					+ "<td align='center'>" + (c.getState() ? c.getTimeString() : "") + "</td>"
					+ "<td align='center'>" + (c.getState() ? "<font color=#00ff00>Online</font>" : "<font color=#ff0000>Offline</font>") + "</td>"
					+ "</tr>";
			
		}
		
		response = response + "</table></body>";
		
		return response;
				
		
		
	}
	
	
}
