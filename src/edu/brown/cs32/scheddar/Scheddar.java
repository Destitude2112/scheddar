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
	private DummyGroup _parentGroup;
	
	public Scheddar(String name) {
		_scheddarName = name;
		_parentGroup = new DummyGroup(_scheddarName);
	}
	
	public String getName() {
		return _scheddarName;
	}
	
	public DummyGroup getTopGroup() {
		return _parentGroup;
	}
	
	public void setTopGroup(DummyGroup g) {
		_parentGroup = g;
	}
	
}
