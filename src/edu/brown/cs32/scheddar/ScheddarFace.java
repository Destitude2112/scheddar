package edu.brown.cs32.scheddar;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface ScheddarFace {
	
	public void save(File file);
	
	
	/**
	 * Project option getters and setters
	 */
	
	public String getStartHour();
	public String getEndHour();
	public void setStartHour(String time);
	public void setEndHour(String time);
	public File getSaveFile();
	
	/**
	 * Need to be able to get the actual object by a name
	 * Need to be able to get relevant lists of things from name
	 * Need to a method to returns people in a group who are not in
	 * any subgroups of that group as Strings
	 */

	public Group getRootGroup();
	public HashMap<String,Group> getGroups();
	public HashMap<String,Person> getPersons();
	
	/**
	 * Methods to get the object associated with a name
	 */
	
	public Person getPersonFromName(String personName);
	public Group getGroupFromName(String groupName);
	public Meeting getMeetingFromName(String meetingName);
	
	
	/**
	 * Methods to get data about a person from their name
	 */
	
	public String getPersonEmail(String personName);
	public String getPersonPhoneNum(String personName);
	public String getPersonDescription(String personName);
	
	/**
	 * Methods to get data about a group from its name
	 */
	
	public List<String> getMemberNames(String groupName);
	public List<String> getSubgroupNames(String groupNames);
	public List<Group> getSubgroups(String name);
	public String getParentGroupName(String name);
	public Group getParentGroup(String name);
	
	/**
	 * Get a list of names of people who are in a group who are NOT
	 * in any subgroup of that group
	 * 
	 * @param name the name of the group
	 * @return a list of the people in the given group but not its subgroups
	 */
	
	public List<String> getPeopleOnlyMainGroup(String name);
	
	/**
	 * Methods to get data about a meeting from the meeting's name
	 */
	
	
	public List<Meeting> dayMeetings(int day, int month, int year);
	public List<Meeting> monthMeetings(int month, int year);
	
	
	
	/**
	 * Methods for adding and removing things in the schedule
	 */
	public void addPerson(Person p);
	public void addGroup(Group g);
	public void addMeeting(Meeting m);
	public void removePerson(String name);
	public void removeGroup(String name);
	public Collection<Person> getAllPeople();
	public Collection<Group> getAllGroups();
	public Collection<Meeting> getAllMeetings();

	
	/**
	 * Methods for sending emails
	 */

	public void sendInvalidSubjectEmail(String toEmail, String subject);
	public void sendInvalidBodyEmail(String toEmail, String body);
	public void sendFinalizedMeetingEmail(String toEmail, String name, Meeting meeting);
	public void sendAddedPersonEmail(String toEmail, String adminName,
			String personName, List<String> groupList);
	public void sendMeetingRequestEmail(String toEmail, String personName,
			Meeting meeting);
	public void sendCustomEmail(String toEmail, String body, String subject);

	/**
	 * Methods for OptionsForm
	 */
	
	public String getAdminName();
	public String getPassword();
	public String getUsername();
	public void setAdminName(String name);
	public void setAdminUsername(String user);
	public void setAdminPassword(String pass);
	public void readEmail();
	
	public HashMap<String,Double> getGroupMemberRankings(Group g);
	
}
