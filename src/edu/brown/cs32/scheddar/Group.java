package edu.brown.cs32.scheddar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Group {
	
	//TODO: Note to self : might want to use Sets instead of the Lists if we want to prevent
	// adding multiple copies of the same person/group to these fields
	
	private String name; // the name of the group
	private List<Person> members; // the members of the group
	private HashMap<Person,Double> memberRankings; // maps person names to importance rank in this group
	private List<Group> subgroups; // a list of the subgroups of this group
	private Group parentGroup; // the parent of this group if one exists
	
	/**
	 * Getter Functions
	 */
	
	public String getName(){
		return this.name;
	}
	
	public List<Person> getMembers(){
		return this.members;
	}
	
	public HashMap<Person,Double> getMemberRankings(){
		return this.memberRankings;
	}
	
	public List<Group> getSubgroups(){
		return this.subgroups;
	}
	
	public Group getParentGroup(){
		return this.parentGroup;
	}
	
	/**
	 * Setter functions (complete reset of values)
	 */
	
	public void setName(String newName){
		this.name = newName;
	}
	
	public void setMembers(List<Person> newMemberList){
		this.members = newMemberList;
	}
	
	public void setMemberRankings(HashMap<Person,Double> newMemberRankings){
		this.memberRankings = newMemberRankings;
	}
	
	public void setSubgroups(List<Group> newSubgroups){
		this.subgroups = newSubgroups;
	}
	
	public void setParentGroup(Group newParent){
		this.parentGroup = newParent;
	}
	
	/**
	 * More convenient setters 
	 */
	
	public void addMember(Person person){
		this.members.add(person);
	}
	
	public void removeMember(Person person){
		this.members.remove(person);
	}
	
	/**
	 * Adds an indivdual member and their ranking to the HashMap. Follows
	 * the rules of put() for a HashMap, so overwrites previous entry if
	 * one existed. This can therefore be used to update a person's ranking.
	 * 
	 * @param person the person to add
	 * @param ranking the ranking to add
	 */
	
	public void addMemberRanking(Person person, double ranking){
		this.memberRankings.put(person,ranking);
	}
	
	/**
	 * Removes an individual member and their ranking from the HashMap.
	 * If the given person was not in the table, nothing happens.
	 * 
	 * @param person the person to remove
	 */
	
	public void removeMemberRanking(Person person){
		this.memberRankings.remove(person);
	}
	
	public void addSubgroup(Group newSubgroup){
		this.subgroups.add(newSubgroup);
	}
	
	public void removeSubgroup(Group subgroup){
		this.subgroups.remove(subgroup);
	}
	
	/**
	 * Adds a list of subgroups to the subgroup field
	 * 
	 * @param subgroups a list of subgroups to add
	 */
	
	public void addListSubgroups(List<Group> subgroups){
		for(Group g : subgroups){
			this.subgroups.add(g);
		}
	}
	
	/**
	 * Removes a list of subgroups from the subgroup field
	 * 
	 * @param subgroups a list of subgroups to remove
	 */
	
	public void removeListSubgroups(List<Group> subgroups){
		for(Group g : subgroups){
			this.subgroups.add(g);
		}
	}
		
	/**
	 * Constructor
	 **/
	
	public Group(String name,List<Person> members,HashMap<Person,Double> memberRankings,
			List<Group> subgroups, Group parentGroup){
		this.name = name;
		this.members = members;
		this.memberRankings = memberRankings;
		this.subgroups = subgroups;
		this.parentGroup = parentGroup;
	}
	
	/**
	 * Returns a list of the names of every person in the group
	 */
	
	public List<String> getPeopleFirstNamesInGroup(){
		List<String> names = new LinkedList<String>();
		for(Person p : this.getMembers()){
			names.add(p.getFirstName());
		}
		return names;
	}
	
	/**
	 * Returns a list of the last names of every person in a group
	 */
	
	public List<String> getPeopleLastNamesInGroup(){
		List<String> names = new LinkedList<String>();
		for(Person p : this.getMembers()){
			names.add(p.getLastName());
		}
		return names;
	}
	
	/**
	 * Return a list of list of full names of every person in a group
	 */
	
	public List<String> getPeopleFullNamesInGroup(){
		List<String> firstNameList = getPeopleFirstNamesInGroup();
		List<String> lastNameList = getPeopleLastNamesInGroup();
		List<String> fullNameList = new LinkedList<String>();
		for(int i=0;i<firstNameList.size();i++){
			fullNameList.add(firstNameList.get(i) + " " + lastNameList.get(i));
		}
		return fullNameList;
	}
	
	/**
	 * Return a members ranking within the group
	 */
	
	public double getMemberRanking(Person p) {
		if(!this.memberRankings.containsKey(p)){
			return 1.0;
		}
		double toRet = this.memberRankings.get(p);
		return toRet;
	}
}
