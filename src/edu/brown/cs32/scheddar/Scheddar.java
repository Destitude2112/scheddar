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
	 * Returns true if a given year number is a leap year, and
	 * false if it is not
	 * 
	 * @param year the year number
	 * @return true if it is a leap year, false if not
	 */
	
	public boolean isLeapYear(int year){
		if(year%400==0) return true;
		else if (year%100==0) return false;
		else if (year%4==0) return true;
		else return false;
	}
	
	/**
	 * Return all Meetings that are occurring in a given month
	 * 
	 * @param month the number of the month
	 * @param year the year number
	 * @return a list of all Meetings in that month
	 */
	
	public List<Meeting> monthMeetings(int month, int year){
		List<Meeting> meetingList = new LinkedList<Meeting>();
		for(Meeting m : this.meetings.values()){
			if(m.getTime().getMonth()==month && m.getTime().getYear()==year){
				meetingList.add(m);
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
	 * Return all Meetings that are occurring in a given day
	 * 
	 * @param day the day number
	 * @param month the month number
	 * @param year the year number
	 * @return a list of all the Meetings occurring on that day
	 */
	
	public List<Meeting> dayMeetings(int day, int month, int year){
		List<Meeting> meetingList = new LinkedList<Meeting>();
		for(Meeting m : this.meetings.values()){
			if(m.getTime().getDay()==day && m.getTime().getMonth()==month && m.getTime().getYear()==year){
				meetingList.add(m);
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
	
	//TODO : Since this our application is meant to be used mainly by businesses, I assume
	// here that there are no meetings that run from one day into the next (businesses
	// generally do not hold meetings that go through midnight into the next day)
	// We may want to change the behavior of ScheddarTimes to allow for that at some point,
	// but I feel like it's unnecessary   - atutino
	
	/**
	 * Return true if the two given ScheddarTimes overlap, and false if they do not
	 * @param t1 the first ScheddarTime
	 * @param t2 the second ScheddarTime
	 * @return true if the times overlap, false if they do not
	 */
	
	public boolean doTimesConflict(ScheddarTime t1, ScheddarTime t2){
		if (t1.getYear() != t2.getYear()) return false;
		if (t1.getMonth() != t2.getMonth()) return false;
		if (t1.getDay() != t2.getDay()) return false;

		int t1StartMinutes = t1.getStartHour() * 60 + t1.getStartMinutes();
		int t1EndMinutes = t1.getStartHour() * 60 + t1.getStartMinutes() + t1.getDuration();
		int t2StartMinutes = t2.getStartHour() * 60 + t2.getStartMinutes();
		int t2EndMinutes = t2.getStartHour() * 60 + t2.getStartMinutes() + t2.getDuration();
		
		if(t1StartMinutes >= t2StartMinutes && t1StartMinutes < t2EndMinutes){
			return true;
		}
		if(t2StartMinutes >= t1StartMinutes && t2StartMinutes < t1EndMinutes){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Create and return a ScheddarTime of the real life current time with a
	 * duration of 0
	 */
	
	public ScheddarTime getCurrentTime(){
		Date date = new Date();
		String dateString = date.toString();
		System.out.println(dateString);
		String[] splitDate = dateString.split(" ");
		String dayName = splitDate[0];
		
		// Convert the day of the week to the correct number
		
		int dayOfWeek = -1;
		if(dayName.equals("Sun")) dayOfWeek = 0;
		else if(dayName.equals("Mon")) dayOfWeek = 1;
		else if(dayName.equals("Tue")) dayOfWeek = 2;
		else if(dayName.equals("Wed")) dayOfWeek = 3;
		else if(dayName.equals("Thu")) dayOfWeek = 4;
		else if(dayName.equals("Fri")) dayOfWeek = 5;
		else if(dayName.equals("Sat")) dayOfWeek = 6;
		
		// Convert the month of the year to the correct number
		
		String monthName = splitDate[1];
		int month = -1;
		if(monthName.equals("Jan")) month = 0;
		else if(monthName.equals("Feb")) month = 1;
		else if(monthName.equals("Mar")) month = 2;
		else if(monthName.equals("Apr")) month = 3;
		else if(monthName.equals("May")) month = 4;
		else if(monthName.equals("Jun")) month = 5;
		else if(monthName.equals("Jul")) month = 6;
		else if(monthName.equals("Aug")) month = 7;
		else if(monthName.equals("Sep")) month = 8;
		else if(monthName.equals("Oct")) month = 9;
		else if(monthName.equals("Nov")) month = 10;
		else month = 11;
		
		int day = Integer.parseInt(splitDate[2]);
		String[] timeSpl = splitDate[3].split(":");
		int hour = Integer.parseInt(timeSpl[0]);
		int minute = Integer.parseInt(timeSpl[1]);
		int year = Integer.parseInt(splitDate[5]);
		
		return new ScheddarTime(hour,minute,0,dayOfWeek,day,month,year,false);
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
