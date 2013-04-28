package edu.brown.cs32.scheddar;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Meeting {
	private String name;  // a name for the event
	private ScheddarTime time; // the block of time when this meeting occurs
	private List<Group> groupsInvolved; // a list of the groups involved in this meeting
	private String description; // a description of this event
	private List<Person> peopleAttending; // a list of the people who are currently attending this meeting
	private double importanceThreshold; // the importance threshold that must be met for the meeting to occur
	
	/**
	 * Getter Functions
	 */
	
	public String getName(){
		return this.name;
	}
	
	public ScheddarTime getTime(){
		return this.time;
	}
	
	public List<Group> getGroupsInvolved(){
		return this.groupsInvolved;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public List<Person> getPeopleAttending(){
		return this.peopleAttending;
	}
	
	public double getImportanceTreshold(){
		return this.importanceThreshold;
	}
	
	/**
	 * Setter Functions (to completely reset values)
	 */
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setTime(ScheddarTime newTime){
		this.time = newTime;
	}
	
	public void setGroupsInvolved(List<Group> newGroupList){
		this.groupsInvolved = newGroupList;
	}
	
	public void setDescription(String newDescription){
		this.description = newDescription;
	}
	
	public void setPeopleAttending(List<Person> newList){
		this.peopleAttending = newList;
	}
	
	public void setImportanceThreshold(double t){
		this.importanceThreshold = t;
	}
	
	/**
	 * More convenient setters
	 */
	
	public void addGroupInvolved(Group group){
		this.groupsInvolved.add(group);
	}
	
	public void removeGroupInvolved(Group group){
		this.groupsInvolved.remove(group);
	}
	
	public void addPerson(Person person){
		this.peopleAttending.add(person);
	}
	
	public void removePerson(Person person){
		this.peopleAttending.remove(person);
	}
	
	/**
	 * Constructor
	 **/
	
	public Meeting(String name, ScheddarTime time,List<Group> groupsInvolved,String description,List<Person> peopleAttending){
		this.name = name;
		this.time = time;
		this.groupsInvolved = groupsInvolved;
		this.description = description;
		this.peopleAttending = peopleAttending;
	}
	
	/**
	 * Returns a Set containing the emails of each person in a Group involved
	 * in this meeting (Set used to prevent emailing a person multiple times
	 * if they belong to more than one of the groups involved)
	 */
	
	public Set<String> getAllEmails(){
		Set<String> allEmails = new TreeSet<String>();
		for(Group g : groupsInvolved){
			for(Person p : g.getMembers()){
				allEmails.add(p.getEmail());
			}
		}
		return allEmails;
	}
	
	/**
	 * Returns a List of emails of people who are already attending this meeting
	 */
	
	public List<String> getAttendingEmails(){
		List<String> attendingEmails = new LinkedList<String>();
		for(Person p : peopleAttending){
			attendingEmails.add(p.getEmail());
		}
		return attendingEmails;
	}
	
	
	/**
	 * Returns true if attendance for this meeting is above a certain
	 * percentage, returns false if not 
	 * 
	 * @param percentage the percentage to check
	 * @param numPeople the number to find the percentage of
	 */
	
	public boolean attendanceAbovePercentage(double percentage,int numPeople){
		double currAttending = (double) peopleAttending.size();
		return (currAttending/numPeople >= percentage);
	}
	
	/**
	 * Returns true if the sum of attending people's importance is above
	 * a given threshold
	 * 
	 * @param threshold the level of importance that must be passed
	 */
	
	public boolean importanceAboveThreshold(double threshold){
		double currImportance = 0.0;
		for(Person p : peopleAttending){
			List<Group> groups = p.getGroups();
			double maxImportance = 1.0;
			for(Group g : groups){
				double possibleReplace = g.getMemberRanking(p);
				if(possibleReplace>maxImportance){
					maxImportance = possibleReplace;
				}
			}
			currImportance += maxImportance;
		}
		return currImportance >= threshold;
	}
	
	
	
}
