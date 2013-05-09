package edu.brown.cs32.scheddar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Group {
	
	private String name; // the name of the group
	
	public List<String> dummyMembers; // used for XML loading
	private List<Person> members; // the full names of the members of the group
	
	private HashMap<String,Double> memberRankings; // maps person's name to importance rank in this group
	
	public List<String> dummySubgroups; // used for XML parsing
	private List<Group> subgroups; // a list of the names of subgroups of this group
	
	public String dummyParentGroup; // used for XML parsing
	private Group parentGroup; // the name of the parent of this group if one exists
	
	private UsefulMethods methods = new UsefulMethods();
	
	/**
	 * Getter Functions
	 */
	
	public String getName(){
		return this.name;
	}
	
	public List<Person> getMembers(){
		return this.members;
	}
	
	public HashMap<String,Double> getMemberRankings(){
		return this.memberRankings;
	}
	
	public List<Group> getSubgroups(){
		return this.subgroups;
	}
	
	public Group getParentGroup(){
		return this.parentGroup;
	}
	
	@Override
	public String toString() {
		return getName();
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
	
	public void setMemberRankings(HashMap<String,Double> newMemberRankings){
		this.memberRankings = newMemberRankings;
	}
	
	public void setSubgroups(List<Group> newSubgroups){
		ArrayList<String> dummySubGroups = new ArrayList<String>();
		for(Group group: newSubgroups){
			dummySubGroups.add(group.getName());
		}
		this.dummySubgroups = dummySubGroups;
		this.subgroups = newSubgroups;
	}
	
	public void setParentGroup(Group newParent){
		this.dummyParentGroup = newParent.name;
		this.parentGroup = newParent;
		newParent.addSubgroup(this);
	}
	
	/**
	 * More convenient setters 
	 */
	
	public void addMember(Person person){
		this.members.add(person);
		this.dummyMembers.add(person.getFullName());
		if (this.getParentGroup()!=null){
			this.getParentGroup().addMember(person);
		}
	}
	
	public boolean hasMember(String name) {
		for(Person p :this.members) {
			if(p.getFullName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void removeMember(Person person){
		this.members.remove(person);
	}
	
	public void removeMember(String name){
		for(Person p :this.members){
			if(p.getFullName().equals(name)){
				this.members.remove(p);
			}
		}
	}
	
	/**
	 * Adds an individual member and their ranking to the HashMap. Follows
	 * the rules of put() for a HashMap, so overwrites previous entry if
	 * one existed. This can therefore be used to update a person's ranking.
	 * 
	 * @param name the name of the person to add
	 * @param ranking the ranking to add
	 */
	
	public void addMemberRanking(String name, double ranking){
		this.memberRankings.put(name,ranking);
	}
	
	/**
	 * Removes an individual member and their ranking from the HashMap.
	 * Use the email of the person
	 * If the given person was not in the table, nothing happens.
	 * 
	 * @param person the person to remove
	 */
	
	public void removeMemberRanking(String person){
		this.memberRankings.remove(person);
	}
	
	public void addSubgroup(Group newSubgroup){
		if(this.subgroups==null){
			this.subgroups = new ArrayList<Group>();
		}
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
	
	public Group(String name,List<Person> members,HashMap<String,Double> memberRankings,
			List<Group> subgroups, Group parentGroup){
		this.name = name;
		this.members = members;
		this.memberRankings = memberRankings;
		this.subgroups = subgroups;
		this.parentGroup = parentGroup;
		
		parentGroup.addSubgroup(this);
	}
	
	/**
	 * Constructor with just the name
	 */
	
	public Group(String name){
		this.name = name;
		this.members = new LinkedList<Person>();
		this.memberRankings = new HashMap<String,Double>();
		this.subgroups = new LinkedList<Group>();
		this.parentGroup = null;
		this.dummyMembers = new ArrayList<String>();
		this.subgroups = new ArrayList<Group>();
		this.dummySubgroups = new ArrayList<String>();
	}
	
	public Group(String currName, String[] members,
			HashMap<String, Double> hm,
			ArrayList<String> groups, String currParentGroup) {
		
		this.name = currName;
		
		ArrayList<String> dummyMembers = new ArrayList<String>();
		for(String member: members){
			dummyMembers.add(member);
		}
		
		this.dummyMembers = dummyMembers;
		this.memberRankings = hm;
		this.setDummySubgroups(groups);
		this.dummyParentGroup = currParentGroup;
		
		// TODO Auto-generated constructor stub
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
		List<String> names = new LinkedList<String>();
		for(Person p : this.getMembers()){
			names.add(p.getFullName());
		}
		return names;
	}
	
	/**
	 * Return a members ranking within the group
	 */
	
	public double getMemberRanking(Person p) {
		if(!this.memberRankings.containsKey(p.getFullName())){
			return 1.0;
		}
		double toRet = this.memberRankings.get(p.getFullName());
		return toRet;		
	}

	public List<String> getDummySubgroups() {
		return dummySubgroups;
	}

	public void setDummySubgroups(List<String> dummySubgroups) {
		this.dummySubgroups = dummySubgroups;
	}
}
