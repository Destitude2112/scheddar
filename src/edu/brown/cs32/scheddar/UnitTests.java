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
		Scheddar newScheddar = new Scheddar("Test Name","admin","username","password");
		assertTrue(newScheddar.getRootGroup().getName().equals("Test Name"));
		assertTrue(newScheddar.getAdminName().equals("admin"));
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
		Scheddar test = new Scheddar("Test","admin","user","pass");
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
	
	// Test that the getEndTime() method works correctly
	
	@Test
	public void getEndTimeTest(){
		ScheddarTime t1 = new ScheddarTime(12,0,320,0,3,10,2000,false);  // one that stays in the same day, overflows minutes
		ScheddarTime t1End = t1.getEndTime();
		assertTrue(t1End.getDay()==3);
		assertTrue(t1End.getMonth()==10);
		assertTrue(t1End.getYear()==2000);
		assertTrue(t1End.getStartHour()==17);
		assertTrue(t1End.getStartMinutes()==20);
		
		ScheddarTime t2 = new ScheddarTime(23,0,340,0,3,10,2000,false); // one that overflows days
		ScheddarTime t2End = t2.getEndTime();
		assertTrue(t2End.getDay()==4);
		assertTrue(t2End.getMonth()==10);
		assertTrue(t2End.getYear()==2000);
		assertTrue(t2End.getDayOfWeek()==1);
		assertTrue(t2End.getStartHour()==4);
		assertTrue(t2End.getStartMinutes()==40);
		
		ScheddarTime t3 = new ScheddarTime(23,30,30,6,31,12,2112,false); // one that goes into a new year. Happy New Years!
		ScheddarTime t3End = t3.getEndTime();
		assertTrue(t3End.getDay()==1);
		assertTrue(t3End.getMonth()==1);
		assertTrue(t3End.getYear()==2113);
		assertTrue(t3End.getDayOfWeek()==0);
		assertTrue(t3End.getStartHour()==0);
		assertTrue(t3End.getStartMinutes()==0);
	}
	
	// Test that daysInMonth method works
	@Test
	public void testDaysInMonth(){
		UsefulMethods methods = new UsefulMethods();
		assertTrue(methods.daysInMonth(1,2112)==31);
		assertTrue(methods.daysInMonth(2,2113)==28);
		assertTrue(methods.daysInMonth(2,2112)==29);
		assertTrue(methods.daysInMonth(9,1337)==30);
	}
	
	// Test dayOfWeek method
	@Test
	public void testDayOfTheWeek(){
		UsefulMethods methods = new UsefulMethods();
		System.out.println(methods.dayOfTheWeek(4,5,2013));
		assertTrue(methods.dayOfTheWeek(4,5,2013)==6);
		assertTrue(methods.dayOfTheWeek(30,9,2014)==2);
	}
}
