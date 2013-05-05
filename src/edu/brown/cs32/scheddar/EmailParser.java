package edu.brown.cs32.scheddar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.*;
import javax.mail.search.FlagTerm;

public class EmailParser {
	
	static String username;
	static String password;
	
	/**
	 * Constructor
	 * 
	 * @param username
	 * @param password
	 */
	
	public EmailParser(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	
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
	
	//TODO : When you read an email here, set it to being read so the program does not needlessly check old scheduling
	// emails and the admin does not have to clear the inbox out manually
	
	/**
	 * Read in all of the unread emails that the account has, and return a List of Tuple<String,String>
	 * The first String is the subject of the email, and the second is the body.
	 */
	
	public static List<Tuple<String,String>> getEmailTuples(){
		Folder inbox;
		List<Tuple<String,String>> emailTuples = new LinkedList<Tuple<String,String>>();
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try{
			Session session = Session.getDefaultInstance(props,null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com",username,password);
			
			inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			
			Message messages[] = inbox.search(new FlagTerm(new Flags(Flag.SEEN),false));
			
			FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);
			fp.add(FetchProfile.Item.CONTENT_INFO);
			inbox.fetch(messages, fp);
			
			try{
				printAllMessages(messages,emailTuples);
				inbox.close(true);
				store.close();
				return emailTuples;
			} catch (Exception ex) {
				System.out.println("Exception occurred at time of read mail");
				ex.printStackTrace();
			}
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null; // should never be reached
	}
	
	public static void printAllMessages(Message[] msgs, List<Tuple<String,String>> emailTuples) throws Exception{
		for(int i=0;i<msgs.length;i++){
			String subject = msgs[i].getSubject();
			emailTuples.add(new Tuple<String,String>(subject,"")); // add the subject to the list of emailTuples
			getContent(msgs[i],emailTuples,i);
		}
	}
	
	public static void getContent(Message msg, List<Tuple<String,String>> emailTuples,int index){
		try{
			Multipart mp = (Multipart) msg.getContent();
		//	System.out.println(mp.getBodyPart(0));
			emailTuples.get(index).y = dumpPart(mp.getBodyPart(0));
		} catch (Exception ex) {
			System.out.println("Exception arise at get Content");
			ex.printStackTrace();
		}
	 }
	 
	 public static String dumpPart(Part p) throws Exception{
		 InputStream is = p.getInputStream();
		 String result = getStringFromInputStream(is);
		 return result;
//		 return;
//		 if (!(is instanceof BufferedInputStream)){
//			 is = new BufferedInputStream(is);
//		 }
//		 int c;
//		 System.out.println("Message : ");
//		 while ((c = is.read()) != -1){
//			 System.out.write(c);
//		 }
	 }
	 
	 public static String getStringFromInputStream(InputStream is){
		 BufferedReader br = null;
		 StringBuilder sb = new StringBuilder();
		 
		 String line;
		 try{
			 br = new BufferedReader(new InputStreamReader(is));
			 while((line = br.readLine()) != null){
				 sb.append(line);
			 }
		 } catch (IOException e){
			 e.printStackTrace();
		 } finally {
			 if (br!=null){
				 try{
					 br.close();
				 } catch (IOException e){
					 e.printStackTrace();
				 }
			 }
		 }
		 return sb.toString();
	 }
	 
	 
	// For testing purposes
	
	public static void main(String[] args){
		
		EmailParser testParser = new EmailParser("scheddartest@gmail.com","DreamTheater");
		
		List<String> testList = new LinkedList<String>();
		testList.add("Win Group");
		testList.add("TSM");
	//	testParser.sendAddedPersonEmail("destitude2112@hotmail.com","Desteh Guyman","Mordecai",testList);
		
		List<ScheddarTime> potentialTimes = new LinkedList<ScheddarTime>();
		potentialTimes.add(new ScheddarTime(12,0,30,3,1,2,2112,false));
		potentialTimes.add(new ScheddarTime(14,30,30,3,1,2,2112,false));
		potentialTimes.add(new ScheddarTime(2,45,30,4,2,2,2112,false));
		Meeting testMeeting = new Meeting("Mayhem Festival",potentialTimes,null,"",null);
		testMeeting.setFinalTime(new ScheddarTime(7,30,90,3,3,3,2113,false));
	//	testParser,sendMeetingRequestEmail("destitude2112@hotmail.com","Mordecai",testMeeting);
		
	//	testParser.sendFinalizedMeetingEmail("destitude2112@hotmail.com", "Alec", testMeeting);
	}
}
