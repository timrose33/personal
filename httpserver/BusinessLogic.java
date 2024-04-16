package jsiproject;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.json.*;



public class BusinessLogic {
	
	   
		   
	   enum CommTypes { Chat, Email, Sms };
	   
	   
	   
	   // private ArrayList<Chat> chatCollection; 
	   
	   // For scalability and ease of accessing date ranges use a sorted collection index by Date/Time
	    private TreeMap<LocalDateTime, CommunicationMethod> emailCollection; 
	    private TreeMap<LocalDateTime, CommunicationMethod> smsCollection; 
	    
	    private TreeMap<LocalDateTime, CommunicationMethod> chatCollection;


	    
	    BusinessLogic () {
	    	
	    	chatCollection = new TreeMap<LocalDateTime, CommunicationMethod>();
	    	emailCollection = new TreeMap<LocalDateTime, CommunicationMethod>();
	    	smsCollection = new TreeMap<LocalDateTime, CommunicationMethod>();
	   	
	  	
	    }

	

	public  void readInputs() {
	       try {
	            FileReader reader = new FileReader("Chats.txt");
	            String line;
	            

	            // TODO Modularize and move some details like filename into the 
	            //  CommunicationMethod classes.
                BufferedReader bufferedReader = new BufferedReader(reader);

                // Skip header line 
	            bufferedReader.readLine();
	            while ((line = bufferedReader.readLine()) != null) {
                   processChatLine(line);
	            }
	            reader.close();
	            /*************************/
	            
	            reader = new FileReader("Emails.txt");
                  
                bufferedReader = new BufferedReader(reader);

                // Skip header line 
	            bufferedReader.readLine();
	            while ((line = bufferedReader.readLine()) != null) {
                  processEmailLine(line);
	            }
	            reader.close();
	            
	            /*************************/
	            
	            reader = new FileReader("Sms.txt");
                  
                bufferedReader = new BufferedReader(reader);

                // Skip header line 
	            bufferedReader.readLine();
	            while ((line = bufferedReader.readLine()) != null) {
	                 processSmsLine(line);
	            }
	            reader.close();
  
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	private void processChatLine (String line) {
		String[] tokens = line.split("\\|");
		
		if (tokens.length < 5) {
			// TODO improve error handling
			TimLogger.log("Error parsing Chat");
			return;
		}
		
		
		Chat chat = new Chat(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
		chatCollection.put(chat.getDateTime(), chat);

	}
	
	private void processEmailLine (String line) {
		
		// negative value to String.split allows empty fields to be tokenized.
		String[] tokens = line.split("\\|", -10);
		
		if (tokens.length < 7) {
			// TODO improve error handling
			System.out.println("Error parsing Email");
			return;
		}
		
		
		Email email = new Email(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
		emailCollection.put(email.getDateTime(), email);

		TimLogger.log(emailCollection.toString());

	}
	
	
	private void processSmsLine (String line) {
		String[] tokens = line.split("\\|");
		
		//System.out.println(tokens);
		
		if (tokens.length < 4) {
			// TODO improve error handling
			TimLogger.log("Error parsing Sms");
			return;
		}
		
		Sms sms = new Sms(tokens[0], tokens[1], tokens[2], tokens[3]);
		
	    smsCollection.put(sms.getDateTime(), sms); 

		TimLogger.log(smsCollection.toString());

	}


	public String buildGetTypesResponse() {
		
		String result = "[" + "\n" + CommTypes.Chat.name() + "," + "\n" + CommTypes.Email.name() + "," + "\n" + CommTypes.Sms.name() + ","+ "\n" + "]";

		for (SortedMap.Entry<LocalDateTime, CommunicationMethod> entry : chatCollection.entrySet())  {
 		    Chat chat = (Chat) entry.getValue();
		    TimLogger.log("Chat.application" + chat.application);
		}

		return result;
			
	}
	
	public String processTimeFilterRequest( String request) {
		String result = "";
		
		TimLogger.log("processTimeFilterRequest");
		
		try {
     		JSONObject json = new JSONObject (request);
    		
     		String from = json.getString("FromTime"); 
     		LocalDateTime fromTime = LocalDateTime.parse(from, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      		
     		String to = json.getString("ToTime"); 
    		LocalDateTime toTime = LocalDateTime.parse(from, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	 
        		
     		JSONArray commTypes = json.getJSONArray("DataTypes");
     		
     		JSONObject mainObj = new JSONObject();

    		 
     		for (int i = 0; i < commTypes.length(); i++) {
     		   String commType = (String) commTypes.get(i);
  
     		   if (commType.compareTo("ChatMessage") == 0 ) {
     			  JSONArray jsonarray = buildJsonArray(fromTime, toTime, chatCollection);
     	     	  mainObj.put("chat", jsonarray);
     			   
     		   }  else if (commType.compareTo("Email") == 0) {
      			  JSONArray jsonarray = buildJsonArray(fromTime, toTime, emailCollection);
     	     	  mainObj.put("email", jsonarray);
     			   
     		   } else if (commType.compareTo("Sms") == 0) {
      			  JSONArray jsonarray = buildJsonArray(fromTime, toTime, smsCollection);
     	     	  mainObj.put("sms", jsonarray);
     			   
     		   } else {
     			   System.out.println("Invalid Type");
     		   }
     		}
     		
     		result = mainObj.toString();
        		
     		
		} catch (JSONException e)  {
	        // return empty string
			TimLogger.log("JSONException");
			result = "";
		}
		
		return result;
	}
	
	// TODO - a more efficient way than passing in a collection as a parameter. 
	private JSONArray buildJsonArray (LocalDateTime from, LocalDateTime to, TreeMap<LocalDateTime, CommunicationMethod> collection ) {
		JSONArray jsonArray = new JSONArray();
		
		TimLogger.log("buildJSONArray");
		
			
		for (LocalDateTime key : collection.keySet()) {
 		    
 		    if (key.isBefore(from) ) continue; // value too low, keep going
 		    if (key.isAfter(to)) break; // value too high, stop looking 
 		    // otherwise....
 		    
 		    CommunicationMethod comm = collection.get(key);
 	 		JSONObject jsonObject = comm.formatJSONObject();
 		    
 		    TimLogger.log("******" + jsonObject.toString());
 		    
 		    jsonArray.put(jsonObject);
		}


		return jsonArray;
	}

}	


