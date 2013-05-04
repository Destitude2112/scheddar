package edu.brown.cs32.scheddar;

import java.util.List;

public interface ScheddarFace {
	
	/**
	 * Need to be able to get the actual object by a name
	 * Need to be able to get relevant lists of things from name
	 * Need to a method to returns people in a group who are not in
	 * any subgroups of that group as Strings
	 */

	
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
	
	
	
	
	
	
	/**
	 * Methods for adding things to the schedule
	 */
	public void addPerson(Person p);
	
}
