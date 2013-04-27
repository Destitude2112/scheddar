package edu.brown.cs32.scheddar;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class Person {
	private String name;  // the person's name
	private String email; // the person's email address
	private String phoneNum; // the person's phone number
	private String description; // administrative note on the person
	private HashMap<Group,Double> groups; // a mapping of groups the person is in to their importance in that group
	private List<ScheddarTime> conflicts; // conflicting times
	
	/**
	 * Getter Functions
	 **/
	
	public String getName(){
		return this.name;
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
	
	public Set<Group> getGroups(){
		return this.groups.keySet();
	}
	
	public double getImportance(Group g){
		return this.groups.get(g);
	}
	
	public List<ScheddarTime> getConflicts(){
		return this.conflicts;
	}
	
	/**
	 * Setter Functions (completely reset values)
	 */
	
	public void setName(String name){
		this.name = name;
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
	
	public void setGroups(HashMap<Group,Double> newGroupHash){
		this.groups = newGroupHash;
	}
	
	public void setConflicts(List<ScheddarTime> newConflictList){
		this.conflicts = newConflictList;
	}
	
	/**
	 * More convenient setters
	 */
	
	
	/**
	 * Adds a person to a group with the default importance level of 1.0
	 * @param group the group to add the person to
	 */
	
	public void addGroup(Group group){
		this.groups.put(group,1.0);
	}
	
	/**
	 * Adds a person to a group with a specified importance level
	 * @param group the group to add the person to
	 * @param importance the importance to map the group to
	 */
	
	public void addGroupImportance(Group group, double importance){
		this.groups.put(group, importance);
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
			this.groups.put(g,1.0);
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
