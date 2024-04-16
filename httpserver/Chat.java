package jsiproject;

import org.json.JSONObject;

public class Chat extends CommunicationMethod{
	
	   String application; 
       String text;

		   // Application|From|To|DateTime|Text
	    	public Chat (String application, String from, String to, String dateTime, String text) {
	    		this.application = application;
	    		this.from = from;
	    		this.to = to;
	    		setDateTime(dateTime);
	    		this.text = text;
	    	}
	    	
	    	public JSONObject formatJSONObject() {
	    		JSONObject jo = new JSONObject();
	    		jo.put("application", application);
	    		jo.put("from", from);
	    		jo.put("to", to);
	    		jo.put("text", text);
	    		
	    		return jo;
	    	}

}
