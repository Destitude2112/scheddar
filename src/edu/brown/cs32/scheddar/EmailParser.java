package edu.brown.cs32.scheddar;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailParser {
	public static void main(String[] args) throws UnsupportedEncodingException{
	
	final String username = "alec_tutino@brown.edu";
	final String password = "placeholder";
	
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
	
	String msgBody = "Test message!";
	
	try{
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username));
		msg.addRecipient(Message.RecipientType.TO,
				new InternetAddress(username));
		msg.setSubject("Email sent yo");
		msg.setText(msgBody);
		
		Transport.send(msg);
		System.out.println("got here");
	} catch (AddressException ex){
		ex.printStackTrace();
	} catch (MessagingException e){
		e.printStackTrace();
	}
	}
}
