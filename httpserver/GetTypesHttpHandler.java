package jsiproject;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetTypesHttpHandler implements HttpHandler {
	
	private BusinessLogic busLogic;

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("http method - " + exchange.getRequestMethod());
		String reqMethod = exchange.getRequestMethod();
		if (reqMethod.compareTo("GET") == 0) {
			System.out.println( "GET");
			
			String response = busLogic.buildGetTypesResponse();

			OutputStream stream = exchange.getResponseBody();	
			
			// TODO filter on GetTypes endpoint
			exchange.sendResponseHeaders(200, response.getBytes().length);
			 
			
			stream.write(response.getBytes());
			stream.flush();
			
		} else if (exchange.getRequestMethod() == "POST") {
			// TODO filter on TimeFilter endpoint
			System.out.println( "POST");

			
		} else {
			exchange.sendResponseHeaders(405, -1);
			System.out.println( " 405");

		}
	
	}
	
	public void setBusinessLogic(BusinessLogic busLogic) {
		this.busLogic = busLogic;
	}

}
