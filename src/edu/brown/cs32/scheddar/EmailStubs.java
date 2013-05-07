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
	
	/**
	 * Returns a String that should be the body of an email sent to a Person that was just added to Groups
	 * @param adminName the name of the administrator of this Scheddar
	 * @param personName the name of the person that was added
	 * @param groupList a list of the names of the groups that the person was added to
	 * @return the body of an email to send to that person
	 */
	
	public static String addedPersonEmail(String adminName, String personName, List<String> groupList){
		String toRet = new String();
		toRet += ("Hello, " + personName + "!\n\n");
		toRet += ("You have been added to the following groups by " + adminName + ":\n\n");
		for(String g : groupList){
			toRet += (g + "\n");
		}
		toRet += "\n";
		toRet += "Please respond to this email with any recurring conflicts you have.\n";
		toRet += "The proper format is a SINGLE LINE of as many <Day Abbrev> <Time Range> as you need. \n";
		toRet += "For example: \n\n";
		toRet += "Mon 11:30-12:30 Tue 3:00-4:00 Wed 17:30-17:45 Thu 8:00-9:00 Fri 9:00-10:00 Sat 23:00-23:30 Sun 12:00-13:00\n\n";
		toRet += "Make sure the day abbreviations are correct. Ranges should NOT span from one day into another.\n";
		toRet += "The subject of your email should be <YourFirstName> <YourLastName> Conflicts\n";
		toRet += "Do NOT put anything else in the subject or body of your response.\n\n";
		toRet += "Thank you!";
		
		return toRet;
	}
	
	/**
	 * Returns a String to be used in the body of an email sent to a person who is a member of a group
	 * that a Meeting was scheduled for
	 * @param personName the name of the person
	 * @param meeting the Meeting that was scheduled
	 * @return the body of an email to send to that person
	 */
	
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
		toRet += "The subject line of the email should be <YourFirstName> <YourLastName> MeetingTimes <MeetingName> \n\n";
		toRet += "Do not put anything else in the body or subject of the email. Thank you!";
		
		return toRet;
	}
	
	/**
	 * Returns a String that should be used as the body of an email sent to a person for
	 * whom a meeting time has just been finalized
	 * @param name the name of the person
	 * @param meeting the meeting that was finalized
	 * @return the body of an email for a finalized meeting
	 */
	
	public static String finalizedMeetingEmail(String name, Meeting meeting){
		String toRet = new String();
		toRet += ("Hello, " + name + "!\n\n");
		toRet += ("A time for the meeting " + meeting.getName() + " has been decided.\n");
		ScheddarTime time = meeting.getFinalTime();
		toRet += ("It will be on " + time.getMonth() + "/" + time.getDay() + "/" + time.getYear() + " from "
				+ time.timeToString() + "-" + time.getEndTime().timeToString());
		return toRet;
	}
	
	/**
	 * Returns a String used for the body of an email to be sent to a person who sent an email
	 * to the admin's account with an invalid subject
	 * @param name the person's name
	 * @param subject the invalid subject that was sent
	 * @return the body of the email to send
	 */
	
	public static String invalidSubjectEmail(String subject){
		String toRet = new String();
		toRet += ("You recently sent an email with the subject " + subject+ " \n");
		toRet += ("This is an invalid subject for our email parsing system. \n");
		toRet += ("To have your response be recognized by our system, please send another email. \n\n");
		toRet += ("To set or update your recurring conflicts, the subject should be : \n");
		toRet += ("<FirstName> <LastName> Conflicts\n\n");
		toRet += ("To indicate availability for an upcoming meeting, the subject should be : \n");
		toRet += ("<FirstName> <LastName> MeetingTimes <MeetingName>\n\n");
		return toRet;
	}
	
	/**
	 * Returns a String used for the body of an email to be sent to a person who sent an email with
	 * an invalid body
	 * 
	 * @param name the person's name
	 * @param body the invalid body that was sent
	 * @return the body of the email to send
	 */
	
	public static String invalidBodyEmail(String body){
		String toRet = new String();;
		toRet += ("You recently sent an email with the following body : \n\n");
		toRet += (body += "\n\n");
		toRet += ("To have your response be recognized by our system, please send another email with a correctly" +
				" formatted body.\n");
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
//		System.out.println(addedPersonEmail(admin,noob,groups));
		
		String testName = "Roy Mustang";
		String invalidSubject = "Roy Mustard invaliadfasdf";
		
		System.out.println(invalidSubjectEmail(invalidSubject));
		System.out.println(invalidBodyEmail(invalidSubject));
	}
	
	
	
}
