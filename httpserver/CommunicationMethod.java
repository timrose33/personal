package jsiproject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public abstract class CommunicationMethod {
	

	String from;
	String to; 
    LocalDateTime dateTime;
	
	public void setDateTime (String datetimeString) {
		
//		this.dateTime = LocalDateTime.parse(datetime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		
		
		// Input is in this format - 2021-01-21 9:00, which is not in a ISO standard Java DateTimeFormatter can parse. 
		// Split into Date and Time.
		// and then further split the date. Assume the North American format of mm-dd-yyyy
		String[] tokens = datetimeString.split(" ", 2);
		
		String dateString = tokens[0];
		String[] dateTokens = dateString.split("-", 3);
		
		int year = Integer.parseInt(dateTokens[2]);
		int month = Integer.parseInt(dateTokens[0]);
		int day = Integer.parseInt(dateTokens[1]);
		
		
		String timeString = tokens[1];
		String[] timeTokens = timeString.split(":", 3); //split into 3 just in case seconds are present
		
		int hour = Integer.parseInt(timeTokens[0]);
		int minute = Integer.parseInt(timeTokens[1]);
		
		
		dateTime = LocalDateTime.of(year, month, day, hour, minute);
		
	}
	
	public LocalDateTime getDateTime() { return dateTime; }
	
    abstract public JSONObject formatJSONObject();
    
	//TODO abstract String getInputFilename() // e.g. Chats.txt
    //TODO abstract String getJSONResponseName() // e.g. ChatMessage
	
	

}
