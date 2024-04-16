package jsiproject;

import org.json.JSONObject;

public class Email extends CommunicationMethod{
	
	   String application; 
       String text;
       String cc;
       String bcc;
       String subject;
       String body;

		   // Application|From|To|DateTime|Text
	    	public Email (String from, String to, String cc, String bcc, String dateTime, String subject, String body) {
	    		
	
	    		this.from = from;
	    		this.to = to;
	    		setDateTime(dateTime);
    		    this.cc = cc;
	    		
	    		this.subject = subject;
	    		this.body = body;
	    	}
	    	
	    	public JSONObject formatJSONObject() {
	    		JSONObject jo = new JSONObject();
	    		jo.put("application", application);
	    		jo.put("from", from);
	    		jo.put("to", to);
	    		if (!cc.isEmpty()) jo.put("cc",cc);
	    		if (!bcc.isEmpty()) jo.put("bcc",bcc);
	    		jo.put("subject", subject);
	    		jo.put("body", body);
	    		
	    		return jo;
	    	}
	    	
   	
 	


}
