package edu.brown.cs32.scheddar;

/**
 * This is our unit test suite for Scheddar
 * 
 * @author atutino
 */

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class UnitTests {

	// Makes sure initializing an new Scheddar object gives the expected results
	
	@Test
	public void newScheddarTest() {
		Scheddar newScheddar = new Scheddar("Test Name");
		assertTrue(newScheddar.getRootGroup().getName().equals("Test Name"));
		assertTrue(newScheddar.getAllPeople().isEmpty());
		assertTrue(newScheddar.getAllMeetings().isEmpty());
	}
	
	// Test that doTimesConflict works
	
	@Test
	public void timeConflictTest(){
		UsefulMethods testScheddar = new UsefulMethods();
		ScheddarTime t1 = new ScheddarTime(0,0,30,0,1,0,2013,false);
		ScheddarTime t2 = new ScheddarTime(1,0,60,0,1,0,2013,false);
		ScheddarTime t3 = new ScheddarTime(1,0,30,0,1,0,2013,false);
		assertTrue(!testScheddar.doTimesConflict(t1, t2));
		assertTrue(testScheddar.doTimesConflict(t2, t3));
	}
	
	// Test getting emails from a group, as well as adding and removing members from a group
	
	@Test
	public void getEmailTest(){
		Scheddar test = new Scheddar("Test");
		test.addGroup(new Group("New Group"));
		assertTrue(test.getGroupEmails("New Group").isEmpty());
		test.getGroup("New Group").addMember(new Person("Bob","Johnson","testEmail","",""));
		test.getGroup("New Group").addMember(new Person("Teemo","Teemo","Teemo","Teemo","Teemo"));
		test.getGroup("New Group").addMember(new Person("Captain","Falcon","showmeyourmoves","asdf","asdf"));
		List<String> emailList = test.getGroupEmails("New Group");
		assertTrue(emailList.contains("Teemo"));
		assertTrue(emailList.contains("showmeyourmoves"));
		assertTrue(emailList.contains("testEmail"));
		test.getGroup("New Group").removeMember("Teemo Teemo");
		emailList = test.getGroupEmails("New Group");
		assertTrue(!emailList.contains("Teemo"));
	}
	
	// Test that the isLeapYear function works correctly
	
	@Test
	public void testLeapYear(){
		UsefulMethods test = new UsefulMethods();
		assertTrue(!test.isLeapYear(2013));
		assertTrue(test.isLeapYear(2112));
		assertTrue(test.isLeapYear(2016));
		assertTrue(!test.isLeapYear(2014));
	}
	
	// Test that the comparator for ScheddarTimes works, meaning that the earlier a ScheddarTime is,
	// the lower its value comparison-wise is
	
	@Test
	public void testScheddarTimeComparable(){
		// hour, minutes, duration, dayOfWeek, day, month, year
		ScheddarTime t1 = new ScheddarTime(12,0,300,0,3,10,2000,false);
		ScheddarTime t2 = new ScheddarTime(12,30,300,0,3,10,2000,false);
		ScheddarTime t3 = new ScheddarTime(24,0,300,0,3,10,2112,false);
		ScheddarTime t4 = new ScheddarTime(24,0,300,0,3,10,2112,false);
		
		assertTrue(t1.compareTo(t2)<0);
		assertTrue(t2.compareTo(t3)<0);
		assertTrue(t3.compareTo(t4)==0);
		assertTrue(t4.compareTo(t1)>0);
	}
}
