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
public class DummyGroup {
	private String _groupName;
	private ArrayList<DummyGroup> _subgroups;
	private ArrayList<DummyPerson> _members;
	
	public DummyGroup(String name) {
		_groupName = name;
		_subgroups = new ArrayList<DummyGroup>();
		_members = new ArrayList<DummyPerson>();
	}
	
	public String toString() {
		return _groupName;
	}
	
	public void add(DummyGroup g) {
		_subgroups.add(g);
	}
	
	public void add(DummyPerson p) {
		_members.add(p);
	}
	
	public void addMember(String n) {
		_members.add(new DummyPerson(n));
	}
	
	public Collection<DummyGroup> getSubgroups() {
		return _subgroups;
	}
	
	public Collection<DummyPerson> getMembers() {
		return _members;
	}
}
