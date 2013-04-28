package edu.brown.cs32.scheddar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * This is the main class of the application
 * @author atutino
 *
 */

public class RealScheddar {
	
	private HashMap<String,Group> groups; // maps names of groups to Groups 
	private HashMap<String,Person> people; // maps emails of people People
	private HashMap<String,Meeting> meetings; // maps names of meetings to Meetings
	
	public static void main(String[] args){
		// Load in past data from the database structure
		
		// Start the GUI
		
		// Loop and accept input until the GUI is closed
		while(true){
			
		}
	}
	
	/**
	 * Adds a new person to the Hashmap of people
	 * 
	 * @param p the person to add to the Hashmap
	 */
	
	public void addPerson(Person p){
		this.people.put(p.getEmail(),p);
	}
	
	/** 
	 * Adds a group to the Hashmap of groups
	 * 
	 * @param g the group to add to the hashmap
	 */
	
	public void addGroup(Group g){
		this.groups.put(g.getName(),g);
	}
	
	/**
	 * Adds a meeting to the Hashmap of meetings
	 * 
	 * @param m the meeting to add
	 */
	
	public void addMeeting(Meeting m){
		this.meetings.put(m.getName(),m);
	}
	
	/**
	 * Removes a person from the HashMap of people
	 * 
	 * @param name the person's name to remove
	 */
	
	public void removePerson(String name){
		this.people.remove(name);
	}
	
	/**
	 * Removes a group from the Hashmap of groups
	 * 
	 * @param name the name of the group to remove
	 */
	
	public void removeGroup(String name){
		this.groups.remove(name);
	}
	
	/**
	 * Removes a meeting from the hashmap of meetings
	 * 
	 * @param name the name of the meeting to remove
	 */
	
	public void removeMeeting(String name){
		this.meetings.remove(name);
	}
	
	/**
	 * Returns a List of the email addresses of everyone in a group.
	 * 
	 * @param name the name of the group to get the emails of
	 */
	
	public List<String> getGroupEmails(String name){
		List<String> emailList = new LinkedList<String>();
		Group g = groups.get(name);
		if(g==null){
			System.out.println("That group does not exist");
			return emailList;
		}
		else{
			List<Person> groupMembers = g.getMembers();
			for(Person p : groupMembers){
				emailList.add(p.getEmail());
			}
			return emailList;
		}
	}
	
	/**
	 * Return all Meetings that are occurring in a given month
	 */
	
	public List<Meeting> monthMeetings(){
		return null;
	}
	
	/**
	 * Return all Meetings that are occurring in a given week
	 */
	
	public List<Meeting> weekMeetings(){
		return null;
	}
	
	
	/**
	 * Return all Meetings that are occurring in a given day
	 */
	
	public List<Meeting> dayMeetings(){
		return null;
	}
	
	
}
