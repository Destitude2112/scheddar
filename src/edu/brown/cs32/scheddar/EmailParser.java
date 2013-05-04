package edu.brown.cs32.scheddar;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailParser {
	
	//TODO : Make these initialized by the admin in some way
	
	final static String username = "scheddartest@gmail.com";
	final static String password = "DreamTheater";
	
	/**
	 * Sends an addedPersonEmail to the given user
	 * 
	 * @param toEmail the email address to send the mail to
	 * @param adminName the name of the admin
	 * @param personName the name of the person the message is being sent to
	 * @param groupList a list of the names of the groups the person was added to
	 * @throws UnsupportedEncodingException
	 */
	
	public static void sendAddedPersonEmail(String toEmail, String adminName, String personName, List<String> groupList){
		Properties props = new Properties();
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		
		String msgBody = EmailStubs.addedPersonEmail(adminName, personName, groupList);
		
		try{
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(username));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toEmail));
			msg.setSubject("You have been added to groups!");
			msg.setText(msgBody);
			
			Transport.send(msg);
		} catch (AddressException ex){
			ex.printStackTrace();
		} catch (MessagingException e){
			e.printStackTrace();
		}	
	}
	
	/**
	 * Sends a meeting request email to the given user
	 * 
	 * @param toEmail the person to email
	 * @param personName the name of the person
	 * @param meeting the Meeting the email is about
	 */
	
	public static void sendMeetingRequestEmail(String toEmail, String personName, Meeting meeting){
		Properties props = new Properties();
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		
		String msgBody = EmailStubs.meetingRequestEmail(personName, meeting);
		
		try{
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(username));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toEmail));
			msg.setSubject("You are requested at a meeting!");
			msg.setText(msgBody);
			
			Transport.send(msg);
		} catch (AddressException ex){
			ex.printStackTrace();
		} catch (MessagingException e){
			e.printStackTrace();
		}	
	}
	
	/**
	 * Sends a finalized meeting email to the given user
	 * 
	 * @param toEmail the address to email
	 * @param name the name of the person
	 * @param meeting the meeting
	 */
		
	public static void sendFinalizedMeetingEmail(String toEmail, String name, Meeting meeting){
		Properties props = new Properties();
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		
		String msgBody = EmailStubs.finalizedMeetingEmail(name, meeting);
		
		try{
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(username));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toEmail));
			msg.setSubject("A meeting time has been finalized!");
			msg.setText(msgBody);
			
			Transport.send(msg);
		} catch (AddressException ex){
			ex.printStackTrace();
		} catch (MessagingException e){
			e.printStackTrace();
		}	
	}
	
	// For testing purposes
	
	public static void main(String[] args){
	//	List<String> testList = new LinkedList<String>();
	//	testList.add("Win Group");
	//	testList.add("TSM");
	//	sendAddedPersonEmail("destitude2112@hotmail.com","Desteh Guyman","Mordecai",testList);
		
		List<ScheddarTime> potentialTimes = new LinkedList<ScheddarTime>();
		potentialTimes.add(new ScheddarTime(12,0,30,3,1,2,2112,false));
		potentialTimes.add(new ScheddarTime(14,30,30,3,1,2,2112,false));
		potentialTimes.add(new ScheddarTime(2,45,30,4,2,2,2112,false));
		Meeting testMeeting = new Meeting("Mayhem Festival",potentialTimes,null,"",null);
		testMeeting.setFinalTime(new ScheddarTime(7,30,90,3,3,3,2113,false));
		sendMeetingRequestEmail("destitude2112@hotmail.com","Mordecai",testMeeting);
		
		sendFinalizedMeetingEmail("destitude2112@hotmail.com", "Rigby", testMeeting);
	}
}