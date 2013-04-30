package edu.brown.cs32.scheddar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Person {
	private String firstName;
	private String lastName;
	private String email; // the person's email address
	private String phoneNum; // the person's phone number
	private String description; // administrative note on the person
	private List<Group> groups; // a list of groups that the person is in
	private List<ScheddarTime> conflicts; // conflicting times
	
	/**
	 * Getter Functions
	 **/
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getPhoneNum(){
		return this.phoneNum;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public List<Group> getGroups(){
		return this.groups;
	}
	
	public List<ScheddarTime> getConflicts(){
		return this.conflicts;
	}
	
	@Override
	public String toString() {
		return getFullName();
	}
	
	/**
	 * Setter Functions (completely reset values)
	 */
	
	public void setFirstName(String name){
		this.firstName = name;
	}
	
	public void setLastName(String name){
		this.lastName = name;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setGroups(List<Group> groups){
		this.groups = groups;
	}
	
	public void setConflicts(List<ScheddarTime> newConflictList){
		this.conflicts = newConflictList;
	}
	
	/**
	 * Constructor
	 */
	
	//TODO : Figure out where in the GUI people are added to groups, might want to
	// add group list in constructor
	
	public Person(String firstName, String lastName, String email, String phoneNum,
			String description){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phoneNum;
		this.description = description;
		this.groups = new LinkedList<Group>();
		this.conflicts = new LinkedList<ScheddarTime>();
	}
	
	
	/**
	 * Adds a person to a group with the default importance level of 1.0
	 * @param group the group to add the person to
	 */
	
	public void addGroup(Group group){
		this.groups.add(group);
	}
	
	public void removeGroup(Group group){
		this.groups.remove(group);
	}
	
	/**
	 * Adds all of the groups in a given list to the groups field with default importance 1.0
	 * 
	 * @param groupList
	 */
	
	public void addGroupList(List<Group> groupList){
		for(Group g : groupList){
			this.groups.add(g);
		}
	}
	
	/**
	 * Removes all of the groups in a given list from the groups field
	 * 
	 * @param groupList
	 */
	
	public void removeGroupList(List<Group> groupList){
		for(Group g : groupList){
			this.groups.remove(g);
		}
	}
	
	public void addConflict(ScheddarTime conflict){
		this.conflicts.add(conflict);
	}
	
	public void removeConflict(ScheddarTime conflict){
		this.conflicts.remove(conflict);
	}
	
	/**
	 * Adds a list of conflicts to the conflicts field
	 * 
	 * @param conflictList a list of conflicts to add
	 */
	
	public void addConflictList(List<ScheddarTime> conflictList){
		for(ScheddarTime c : conflictList){
			this.addConflict(c);
		}
	}
	
	/**
	 * Removes a list of conflicts from the conflicts field
	 * 
	 * @param conflictList a list of conflicts to remove
	 */
	
	public void removeConflictList(List<ScheddarTime> conflictList){
		for(ScheddarTime c : conflictList){
			this.removeConflict(c);
		}
	}
}
