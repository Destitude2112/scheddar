package edu.brown.cs32.scheddar;

import java.util.ArrayList;
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
	private List<ScheddarTime> conflicts; // recurring conflicts
	
	//TODO:Either remove or add dummy so Prateek can save. Depends on usefulness to GUI peeps  ~atutino
	private List<Group> groupList; // groups that the person is in
	
	private UsefulMethods methods = new UsefulMethods();
	
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
	
	public List<ScheddarTime> getConflicts(){
		return this.conflicts;
	}
	
	public List<Group> getGroups() {
		return this.groupList;
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
	
	public void setConflicts(List<ScheddarTime> newConflictList){
		this.conflicts = newConflictList;
	}
	
	/**
	 * Constructor
	 */
	
	// The person's conflict list is not requested here, as a person is created by the adminstrator at
	// a time, and the conflict list is not known until the person responds with an email giving
	// specific recurring conflicts.    ~atutino
	
	public Person(String firstName, String lastName, String email, String phoneNum,
			String description){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phoneNum;
		this.description = description;
		this.conflicts = new LinkedList<ScheddarTime>();
		this.groupList = new LinkedList<Group>();
	}
	
	Person(String emailID,String firstName, String lastName, String phone, String description, List<ScheddarTime> conflicts){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = emailID;
		this.phoneNum = phone;
		this.description = description;
		this.conflicts = conflicts;
	}

	/**
	 * Methods to add/remove ScheddarTime conflicts. The boolean isRecurring should be
	 * set to true for these times
	 */
	
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
	
	// TODO : Decide if keeping group list or not
	
	public void addGroup(Group g){
		this.groupList.add(g);
	}
}
