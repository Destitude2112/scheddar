package edu.brown.cs32.scheddar;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * This is the main class of the application
 * @author atutino
 *
 */


public class Scheddar implements ScheddarFace {
	
	private HashMap<String,Group> groups; // maps names of groups to Groups 
	private HashMap<String,Person> people; // maps names of people People
	private HashMap<String,Meeting> meetings; // maps names of meetings to Meetings
	private String name;
	private String adminName; // the name of the admin of this Scheddar
	
	
	// This is the number of hours before the EARLIEST proposed time for a meeting
	// that the program will calculate the best meeting time, decide on it,
	// tell the GUI to update accordingly, and email all people involved
	// with the decided time. I set it to 3 days, can change.   ~ atutino
	
	//TODO:Decide if this should be here on a meeting by meeting basis
	
	private int timeBeforeMeetingFinalized = 72;
	
	/**
	 * Hi, I implemented a constructor and getRootGroup() to work with my group tree.
	 * Feel free to reimplement if you want it done differently. 
	 * 
	 * This one constructs a completely new Scheddar
	 * ~sdemane
	 * 
	 * 
	 * @param name
	 */
	
	public Scheddar(String name) {
		this.name = name;
		groups = new HashMap<String,Group>();
		people = new HashMap<String,Person>();
		meetings = new HashMap<String,Meeting>();
		addGroup(new Group(name));
	}
	
	/**
	 * Construct a Scheddar from an XML file
	 * 
	 * Ask Prateek what data structure represents an XML file
	 */
	
	public Scheddar(){
		
	}
	
	/**
	 * @return The root group has the same name as the overall scheddar project, and contains all other groups and members
	 */
	public Group getRootGroup() {
		return groups.get(name);
	}
	
	/**
	 * Adds a new person to the Hashmap of people
	 * 
	 * @param p the person to add to the Hashmap
	 */
	
	public void addPerson(Person p){
		this.people.put(p.getFullName(),p);
	}
	
	/** 
	 * Adds a group to the Hashmap of groups
	 * 
	 * @param g the group to add to the hashmap
	 */
	
	public void addGroup(Group g){
		this.groups.put(g.getName(),g);
	}
	
	public Group getGroup(String name) {
		return groups.get(name);
	}
	
	public Person getPerson(String name) {
		return people.get(name);
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
	 * Return all Meetings that have a proposed time in a given month
	 * 
	 * @param month the number of the month
	 * @param year the year number
	 * @return a list of all Meetings in that month
	 */
	
	public List<Meeting> monthMeetings(int month, int year){
		List<Meeting> meetingList = new LinkedList<Meeting>();
		for(Meeting m : this.meetings.values()){
			for(ScheddarTime t : m.getProposedTimes()){
			    if(t.getMonth()==month && t.getYear()==year){
				    meetingList.add(m);
				    break;
			    }
			}
		}
		return meetingList;
	}
	
	/**
	 * Return all Meetings that are occurring in a given week
	 * 
	 * @param day the start day of the week
	 * @param month the starting month of the week
	 * @param year the starting month of the week
	 * @return a list of all Meetings in a week
	 */
	
	public List<Meeting> weekMeetings(int day, int month, int year){
		return null;
	}
	
	
	/**
	 * Return all Meetings that have a proposed time in a given day
	 * 
	 * @param day the day number
	 * @param month the month number
	 * @param year the year number
	 * @return a list of all the Meetings occurring on that day
	 */
	
	public List<Meeting> dayMeetings(int day, int month, int year){
		List<Meeting> meetingList = new LinkedList<Meeting>();
		for(Meeting m : this.meetings.values()){
			for(ScheddarTime t : m.getProposedTimes()){
				if(t.getDay()==day && t.getMonth()==month && t.getYear()==year){
					meetingList.add(m);
					break;
				}
			}
		}
		return meetingList;
	}

	/**
	 * Interface methods
	 */
	
	@Override
	public Person getPersonFromName(String personName) {
		return this.people.get(personName);
	}

	@Override
	public Group getGroupFromName(String groupName) {
		return this.groups.get(groupName);
	}

	@Override
	public Meeting getMeetingFromName(String meetingName) {
		return this.meetings.get(meetingName);
	}

	@Override
	public String getPersonEmail(String personName) {
		return this.people.get(personName).getEmail();
	}

	@Override
	public String getPersonPhoneNum(String personName) {
		return this.people.get(personName).getPhoneNum();
	}

	@Override
	public String getPersonDescription(String personName) {
		return this.people.get(personName).getDescription();
	}

	@Override
	public List<String> getMemberNames(String groupName) {
		return this.groups.get(groupName).getPeopleFullNamesInGroup();
	}

	@Override
	public List<String> getSubgroupNames(String groupName) {
		List<Group> subgroups = this.groups.get(groupName).getSubgroups();
		List<String> subgroupNames = new LinkedList<String>();
		for(Group g : subgroups){
			subgroupNames.add(g.getName());
		}
		return subgroupNames;
	}

	@Override
	public List<Group> getSubgroups(String name) {
		return this.groups.get(name).getSubgroups();
	}
	
	//TODO: Decide if this is the behavior we want out of this function
	
	/**
	 * Returns the name of a group's parent group if one exists.
	 * If not, returns "".
	 */
	
	@Override
	public String getParentGroupName(String name) {
		Group parentGroup = this.groups.get(name).getParentGroup();
		if(parentGroup==null){
			return "";
		}
		else{
			return parentGroup.getName();
		}
	}

	@Override
	public Group getParentGroup(String name) {
		return this.groups.get(name).getParentGroup();
	}
	
	/**
	 * Returns a list of the people only in the main group of the given
	 * group name and not in any of its subgroups
	 */
	
	@Override
	public List<String> getPeopleOnlyMainGroup(String name) {
		List<String> memberNames = this.groups.get(name).getPeopleFullNamesInGroup();
		List<Group> subgroups = getSubgroups(name);
		for(Group g : subgroups){
			List<String> subgroupMemberNames = g.getPeopleFullNamesInGroup();
			for(String n : subgroupMemberNames){
				memberNames.remove(n);
			}
		}
		return memberNames;
	}
	
	/**
	 * Return a Collection of all the people in this Scheddar instance
	 */
	
	public Collection<Person> getAllPeople(){
		return this.people.values();
	}
	
	/**
	 * Return a Collection of all the groups in this Scheddar instance
	 */
	
	public Collection<Group> getAllGroups(){
		return this.groups.values();
	}
	
	/**
	 * Return a Collection of all the meetings in this Scheddar instance
	 */
	
	public Collection<Meeting> getAllMeetings(){
		return this.meetings.values();
	}
	
}
