package edu.brown.cs32.scheddar;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author sdemane
 * 
 * This is a dummy Group class for use while developing
 * GUIScheddar
 * 
 */
public class Group {
	private String _groupName;
	private ArrayList<Group> _subgroups;
	private ArrayList<Person> _members;
	
	public Group(String name) {
		_groupName = name;
		_subgroups = new ArrayList<Group>();
		_members = new ArrayList<Person>();
	}
	
	public String toString() {
		return _groupName;
	}
	
	public void add(Group g) {
		_subgroups.add(g);
	}
	
	public void add(Person p) {
		_members.add(p);
	}
	
	public void addMember(String n) {
		_members.add(new Person(n));
	}
	
	public Collection<Group> getSubgroups() {
		return _subgroups;
	}
	
	public Collection<Person> getMembers() {
		return _members;
	}
}
