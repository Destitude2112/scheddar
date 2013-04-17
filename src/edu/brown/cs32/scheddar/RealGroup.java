package edu.brown.cs32.scheddar;
import RealPerson;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class RealGroup {
	
	//TODO: Note to self : might want to use Sets instead of the Lists if we want to prevent
	// adding multiple copies of the same person/group to these fields
	
	private String name; // the name of the group
	private List<RealPerson> members; // the members of the group
	private HashMap<RealPerson,Integer> memberRankings; // maps person names to importance rank in this group
	private List<RealGroup> subgroups; // a list of the subgroups of this group
	private RealGroup parentGroup; // the parent of this group if one exists
	
	/**
	 * Getter Functions
	 */
	
	public String getName(){
		return this.name;
	}
	
	public List<RealPerson> getMembers(){
		return this.members;
	}
	
	public HashMap<RealPerson,Integer> getMemberRankings(){
		return this.memberRankings;
	}
	
	public List<RealGroup> getSubgroups(){
		return this.subgroups;
	}
	
	public RealGroup getParentGroup(){
		return this.parentGroup;
	}
	
	/**
	 * Setter functions (complete reset of values)
	 */
	
	public void setName(String newName){
		this.name = newName;
	}
	
	public void setMembers(List<RealPerson> newMemberList){
		this.members = newMemberList;
	}
	
	public void setMemberRankings(HashMap<RealPerson,Integer> newMemberRankings){
		this.memberRankings = newMemberRankings;
	}
	
	public void setSubgroups(List<RealGroup> newSubgroups){
		this.subgroups = newSubgroups;
	}
	
	public void setParentGroup(RealGroup newParent){
		this.parentGroup = newParent;
	}
	
	/**
	 * More convenient setters 
	 */
	
	public void addMember(RealPerson person){
		this.members.add(person);
	}
	
	public void removeMember(RealPerson person){
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
	
	public void addMemberRanking(RealPerson person, int ranking){
		this.memberRankings.put(person,ranking);
	}
	
	/**
	 * Removes an individual member and their ranking from the HashMap.
	 * If the given person was not in the table, nothing happens.
	 * 
	 * @param person the person to remove
	 */
	
	public void removeMemberRanking(RealPerson person){
		this.memberRankings.remove(person);
	}
	
	public void addSubgroup(RealGroup newSubgroup){
		this.subgroups.add(newSubgroup);
	}
	
	public void removeSubgroup(RealGroup subgroup){
		this.subgroups.remove(subgroup);
	}
	
	/**
	 * Adds a list of subgroups to the subgroup field
	 * 
	 * @param subgroups a list of subgroups to add
	 */
	
	public void addListSubgroups(List<RealGroup> subgroups){
		for(RealGroup g : subgroups){
			this.subgroups.add(g);
		}
	}
	
	/**
	 * Removes a list of subgroups from the subgroup field
	 * 
	 * @param subgroups a list of subgroups to remove
	 */
	
	public void removeListSubgroups(List<RealGroup> subgroups){
		for(RealGroup g : subgroups){
			this.subgroups.add(g);
		}
	}
		
	/**
	 * Constructor
	 **/
	
	public RealGroup(String name,List<RealPerson> members,HashMap<RealPerson,Integer> memberRankings,
			List<RealGroup> subgroups, RealGroup parentGroup){
		this.name = name;
		this.members = members;
		this.memberRankings = memberRankings;
		this.subgroups = subgroups;
		this.parentGroup = parentGroup;
	}
	
	/**
	 * Returns a list of the names of every person in the group
	 */
	
	public List<String> getPeopleNamesInGroup(){
		List<String> names = new LinkedList<String>();
		for(RealPerson p : this.getMembers()){
			names.add(p.getName());
		}
		return names;
	}
	
}
