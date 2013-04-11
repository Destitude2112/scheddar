package edu.brown.cs32.scheddar;


/**
 * @author sdemane
 * 
 * This is a dummy Scheddar class for use while developing
 * GUIScheddar
 * 
 */
public class Scheddar {
	
	private String _scheddarName;
	private Group _parentGroup;
	
	public Scheddar(String name) {
		_scheddarName = name;
		_parentGroup = new Group(_scheddarName);
	}
	
	public String getName() {
		return _scheddarName;
	}
	
	public Group getTopGroup() {
		return _parentGroup;
	}
	
	public void setTopGroup(Group g) {
		_parentGroup = g;
	}
	
}
