package jsiproject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpContext;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BusinessLogic webapp = new BusinessLogic();
		
		webapp.readInputs();
		
		// webapp.buildGetTypesResponse();
		
	    InetAddress localAddress= null;
		try {
			localAddress = InetAddress.getByName("127.0.0.1");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     HttpServer server= null;
	     
		 try {
			    server = HttpServer.create(new InetSocketAddress(localAddress, 8080), 0);
		 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }


      	 GetTypesHttpHandler getHandler = new GetTypesHttpHandler();
      	 getHandler.setBusinessLogic(webapp);
      	 PostTimeFilterHttpHandler postHandler = new PostTimeFilterHttpHandler();
     	 postHandler.setBusinessLogic(webapp);

	     HttpContext getContext = server.createContext("/api/GetTypes", getHandler); 
	     HttpContext postContext = server.createContext("/api/TimeFilter", postHandler); 


	     server.start();

	}
	


}
