package jsiproject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class PostTimeFilterHttpHandler implements HttpHandler {
	
	private BusinessLogic busLogic;

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		String reqMethod = exchange.getRequestMethod();
		if (reqMethod.compareTo("POST") == 0) {
			
			// process input message (request)
			InputStream inputStream = exchange.getRequestBody();
			
			String requestString = new String(inputStream.readAllBytes());
			
			TimLogger.log (requestString);
			
			String response = busLogic.processTimeFilterRequest(requestString);

            // setup output message (response)
			exchange.sendResponseHeaders(200, response.getBytes().length);
			 
			OutputStream outputStream = exchange.getResponseBody();	
						
			outputStream.write(response.getBytes());
			outputStream.flush();
			
		} else {
			exchange.sendResponseHeaders(405, -1);
			TimLogger.log("405");

		}
		
	}
	
	public void setBusinessLogic(BusinessLogic busLogic) {
		this.busLogic = busLogic;
	}

}
