package edu.brown.cs32.scheddar;

import java.util.LinkedList;
import java.util.List;

/**
 * This class contains methods that return text to be put into
 * the different types of emails that the program will send out.
 * Might move to overall email parsing class, depending on how
 * structure actually ends up working
 * 
 * @author atutino
 *
 */

public class EmailStubs {
	
	public static String addedPersonEmail(String adminName, String personName, List<String> groupList){
		String toRet = new String();
		toRet += ("Hello, " + personName + "!\n\n");
		toRet += ("You have been added to the following groups by " + adminName + ":\n\n");
		for(String g : groupList){
			toRet += (g + "\n");
		}
		toRet += "\n";
		toRet += "Please respond to this email with any recurring conflicts you have.\n";
		toRet += "The proper format is :";
		
		return toRet;
	}
	
	public static String meetingRequestEmail(String personName, Meeting meeting){
		String toRet = new String();
		toRet += ("Hello, " + personName + "!\n\n");
		toRet += ("Your presence is requested at the meeting " + meeting.getName() + ".\n");
		toRet += ("The proposed times for the meeting are as follows : \n\n");
		
		List<ScheddarTime> timeList = meeting.getProposedTimes();
		for(int i=0;i<timeList.size();i++){
			toRet += (i + ") " + timeList.get(i).getMonth() + "/" + timeList.get(i).getDay() + "/"
					+ timeList.get(i).getYear() + " " + timeList.get(i).timeToString()
					+ "-" + timeList.get(i).getEndTime().timeToString() + "\n");
		}
		toRet += "\n";
		toRet += "Please respond to this email with a list of times you cannot make on the first" +
				" line of the email body. \n\n ";
		toRet += "Example: If you cannot make meetings 0 and 2, respond with : \n";
		toRet += "0 2\n\n";
		toRet += "Do not put anything else in the body of the email. Thank you!";
		
		return toRet;
	}
	
	
	public static String finalizedMeetingEmail(String name, Meeting meeting){
		String toRet = new String();
		toRet += ("Hello, " + name + "!\n\n");
		toRet += ("A time for the meeting " + meeting.getName() + " has been decided.\n");
		ScheddarTime time = meeting.getFinalTime();
		toRet += ("It will be on " + time.getMonth() + "/" + time.getDay() + "/" + time.getYear() + " from "
				+ time.timeToString() + "-" + time.getEndTime().timeToString());
		return toRet;
	}
	
	// Testing purposes
	
	public static void main(String[] args){
		String admin = "Jayce";
		String noob = "Teemo";
		List<String> groups = new LinkedList<String>();
		groups.add("TSM");
		groups.add("CLG");
		groups.add("BROBROBRO");
		System.out.println(addedPersonEmail(admin,noob,groups));
	}
	
}