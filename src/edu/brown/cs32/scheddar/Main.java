package edu.brown.cs32.scheddar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main {
	
	static File dest = new File("data/group.xml");
	public static void main(String[] args){
		
		//Contents of a sample test XML
		/*
		 <?xml version="1.0" encoding="UTF-8" standalone="no"?><All>
		 <group><group name=""><members>abc@brown.edu,xyz@brown.edu,prateeksthebest@brown.edu
		 </members><memberRankings/><subgroups/><parentGroup>group1</parentGroup></group>
		 <group name="group3"><members>abc@brown.edu,xyz@brown.edu,prateeksthebest@brown.edu
		 </members><memberRankings/><subgroups/><parentGroup>group1</parentGroup></group>
		 <group name="group1"><members>abc@brown.edu,xyz@brown.edu,prateeksthebest@brown.edu
		 </members><memberRankings/><subgroups/><parentGroup>blabla</parentGroup></group>
		 </group><person><person emailId="Arora"><firstName>prateek_arora@brown.edu</firstName>
		 <lastName>Prateek</lastName><phone>401-935-3094</phone><description>THIS DUDE'S AWESOME
		 </description><conflicts>14/0/120/3/0/0/0/true</conflicts></person><person emailId="Tutino">
		 <firstName>alec_tutino@brown.edu</firstName><lastName>Alec</lastName><phone>999-999-9999
		 </phone><description>KINDA COOL</description><conflicts>14/0/120/3/0/0/0/true</conflicts>
		 </person></person><meeting><meeting name="meeting1"><decided>false</decided>
		 <timeForFinalizing>14/30/2/0/12/10/2012/false</timeForFinalizing><finalTime>
		 14/30/2/0/12/10/2012/false</finalTime><proposedTimes>14/30/2/0/12/10/2012/false
		 </proposedTimes><indexToScore>1,1.0</indexToScore><groupsInvolved>noone</groupsInvolved>
		 <description>blabla</description></meeting></meeting></All>
		 */
		
		
		//Scheddar s1 = new Scheddar(dest); //This creates a Scheddar object from all that is in the XML
		Scheddar s1 = getTestData();
		
		//Scheddar s1;
		
		//Application use
		
		
		s1.saveData();// This finally saves all the data for the Scheddar object in the XML
		
		Scheddar testScheddar = new Scheddar (dest);
	}


	public static Scheddar getTestData(){
	
		
		Scheddar s = new Scheddar();
		s.setSaveFile(dest);
		s.setName("Scheddar Corp.");
		s.setAdminName("admin");
		s.setStartHour("09:00");
		s.setEndHour("18:00");
		
		//Generate ScheddarTime for conflicts
		//Wednesday
		ScheddarTime wed10 = new ScheddarTime(3, 10, 60);
		ScheddarTime wed11 = new ScheddarTime(3, 11, 60);
		ScheddarTime wed12 = new ScheddarTime(3, 12, 60);
		ScheddarTime wed1 = new ScheddarTime(3, 13, 60);
		ScheddarTime wed2 = new ScheddarTime(3, 14, 60);
		ScheddarTime wed3 = new ScheddarTime(3, 15, 60);
		ScheddarTime wed4 = new ScheddarTime(3, 16, 60);
		ScheddarTime wed5 = new ScheddarTime(3, 17, 60);
		ScheddarTime wed6 = new ScheddarTime(3, 18, 60);
		ScheddarTime wed7 = new ScheddarTime(3, 19, 60);
		
	    //Thursday
		ScheddarTime thurs10 = new ScheddarTime(4, 10, 60);
		ScheddarTime thurs11 = new ScheddarTime(4, 11, 60);
		ScheddarTime thurs12 = new ScheddarTime(4, 12, 60);
		ScheddarTime thurs1 = new ScheddarTime(4, 13, 60);
		ScheddarTime thurs2 = new ScheddarTime(4, 14, 60);
		ScheddarTime thurs3 = new ScheddarTime(4, 15, 60);
		ScheddarTime thurs4 = new ScheddarTime(4, 16, 60);
		ScheddarTime thurs5 = new ScheddarTime(4, 17, 60);
		ScheddarTime thurs6 = new ScheddarTime(4, 18, 60);
		ScheddarTime thurs7 = new ScheddarTime(4, 19, 60);		
		
		//Friday
		ScheddarTime fri10 = new ScheddarTime(5, 10, 60);
		ScheddarTime fri11 = new ScheddarTime(5, 11, 60);
		ScheddarTime fri12 = new ScheddarTime(5, 12, 60);
		ScheddarTime fri1 = new ScheddarTime(5, 13, 60);
		ScheddarTime fri2 = new ScheddarTime(5, 14, 60);
		ScheddarTime fri3 = new ScheddarTime(5, 15, 60);
		ScheddarTime fri4 = new ScheddarTime(5, 16, 60);
		ScheddarTime fri5 = new ScheddarTime(5, 17, 60);
		ScheddarTime fri6 = new ScheddarTime(5, 18, 60);
		ScheddarTime fri7 = new ScheddarTime(5, 19, 60);	
		
		//Conflicts
		//Wed: 11,1,3-5,7
		//Thurs: 10-12, 1
		//Fri: 3
		ArrayList<ScheddarTime> c1 = new ArrayList<ScheddarTime>();
		c1.add(wed11);
		c1.add(wed1);
		c1.add(wed3);
		c1.add(wed4);
		c1.add(wed7);
		c1.add(thurs10);
		c1.add(thurs11);
		c1.add(thurs12);
		c1.add(thurs1);
		c1.add(fri3);
		
		//Conflicts2
		//Wed:11, 2-5, 7
		//Thurs: 11-3
		//Fri: 3
		ArrayList<ScheddarTime> c2 = new ArrayList<ScheddarTime>();
		c2.add(wed12);
		c2.add(wed2);
		c2.add(wed3);
		c2.add(wed4);
		c2.add(wed7);
		c2.add(thurs11);
		c2.add(thurs12);
		c2.add(thurs1);
		c2.add(thurs2);
		c2.add(fri3);
		
		//Conflicts3
		//Wed:1-5
		//Thurs: 11-3
		//Fri: 4
		ArrayList<ScheddarTime> c3 = new ArrayList<ScheddarTime>();
		c3.add(wed1);
		c3.add(wed2);
		c3.add(wed3);
		c3.add(wed4);
		c3.add(thurs11);
		c3.add(thurs12);
		c3.add(thurs1);
		c3.add(thurs2);
		c3.add(fri4);
		
		
		//Conflicts4
		//Wed: always
		//Thurs: always
		//Fri: always
		ArrayList<ScheddarTime> c4 = new ArrayList<ScheddarTime>();
		c4.add(wed10);
		c4.add(wed11);
		c4.add(wed12);
		c4.add(wed1);
		c4.add(wed2);
		c4.add(wed3);
		c4.add(wed4);
		c4.add(wed5);
		c4.add(wed6);
		c4.add(wed7);
		c4.add(thurs10);
		c4.add(thurs11);
		c4.add(thurs12);
		c4.add(thurs1);
		c4.add(thurs2);
		c4.add(thurs3);
		c4.add(thurs4);
		c4.add(thurs5);
		c4.add(thurs6);
		c4.add(thurs7);
		c4.add(fri10);
		c4.add(fri11);
		c4.add(fri12);
		c4.add(fri1);
		c4.add(fri2);
		c4.add(fri3);
		c4.add(fri4);
		c4.add(fri5);
		c4.add(fri6);
		c4.add(fri7);
		
		//Generate persons
		// String firstName, String lastName, String emailID, String phone, String description, List<ScheddarTime> conflicts
		Person prateek = new Person("Prateek", "Arora", "prateek_arora@brown.edu", "401-935-3094",
				"THIS DUDE'S AWESOME", c1);
		Person alec = new Person("Alec", "Tutino", "alec_tutino@brown.edu", "401-111-7426",
				"THIS DUDE'S SMART", c2);
		Person stephanie = new Person("Stephanie", "Demane", "stephane_demane@brown.edu", "401-862-9312",
				"SHE DOES THE GUIS", c3);
		Person rob = new Person("Rob", "Volgman", "rob_volgman@brown.edu", "401-987-9087",
				"HE DOES PLAYS", c2);
		Person alwaysBusy = new Person("Prateek", "Arora", "prateek_arora@brown.edu", "401-935-3094",
				"THIS DUDE'S AWESOME", c4);
		
		//People with c1 conflicts
		Person p1 = new Person("Tom", "Cruise", "alec_tutino@brown.edu", "401-935-3094",
				"He's okay... Could be a little cuter", c1);
		Person p2 = new Person("Neil", "Patrick Harris", "alec_tutino@brown.edu", "401-935-3094",
				"Top Programmer in Python", c1);
		Person p3 = new Person("Zoey", "Deschenel", "prateek_arora@brown.edu", "401-935-3094",
				"Stereotypically annoying girl", c1);
		Person p4 = new Person("Lebron", "James", "prateek_arora@brown.edu", "401-935-3094",
				"Small guy who's litters everywehre around the trashcan", c1);
		Person p5 = new Person("Rebecca", "Black", "prateek_arora@brown.edu", "401-935-3094",
				"Popular girl in the company", c1);
		Person p6 = new Person("Psy", "NoLastName", "prateek_arora@brown.edu", "401-935-3094",
				"Hey! Sexy lady!", c1);
		Person p7 = new Person("Star", "Wars", "prateek_arora@brown.edu", "401-935-3094",
				"The guy with the coolest names", c1);
		Person p8 = new Person("Arnold", "Sch.", "prateek_arora@brown.edu", "401-935-3094",
				"Small dude who watches chick flicks", c1);
		
		//People with c2 conflicts
		Person p9 = new Person("Brad", "Pitt", "alec_tutino@brown.edu", "401-935-3094",
						"Chill dude. But doesnt work with Angi", c2);
		Person p10 = new Person("Angelina", "Jolie", "stephanie_demane@brown.edu", "401-935-3094",
						"AJ is bleh and doesn't even work with Brad", c2);
		Person p11 = new Person("George", "Clooney", "prateek_arora@brown.edu", "401-935-3094",
						"Looks like the CEO. He also is the CEO.", c2);
		Person p12 = new Person("Tiger", "Woods", "prateek_arora@brown.edu", "401-935-3094",
						"Was considered super nice until...", c2);
		Person p13 = new Person("Water", "Station Guy", "prateek_arora@brown.edu", "401-935-3094",
						"Dude who's always at the water station", c2);
		Person p14 = new Person("John", "Krasinski", "prateek_arora@brown.edu", "401-935-3094",
						"Best friends with Jim", c2);
		Person p15 = new Person("Ted", "fromTheMovie", "prateek_arora@brown.edu", "401-935-3094",
						"Its a teddy bear but dont unserestimate his coding skills", c2);
		Person p16 = new Person("Bathroom", "Guy", "prateek_arora@brown.edu", "401-935-3094",
						"Dude who's always outside the bathroom", c2);
		
		//People with c3 conflicts
		Person p17 = new Person("Jim", "Halpert", "rob_volgman@brown.edu", "401-935-3094",
								"Dude who's flirting with the receptionist", c3);
		Person p18 = new Person("Pam", "Beesly", "prateek_arora@brown.edu", "401-935-3094",
								"The receptionist", c3);
		Person p19 = new Person("Dwight", "Schrute", "prateek_arora@brown.edu", "401-935-3094",
								"Kinda weird", c3);
		Person p20 = new Person("Stanley", "Hudson", "prateek_arora@brown.edu", "401-935-3094",
								"Hardworking, black guy", c3);
		Person p21 = new Person("Phyllis", "Vance", "prateek_arora@brown.edu", "401-935-3094",
								"Just sits and looks miserbale", c3);
		Person p22 = new Person("Michael", "Scott", "prateek_arora@brown.edu", "401-935-3094",
								"THE MOST EFFICIENT WORKER EVER", c3);
		Person p23 = new Person("The", "Plumber", "prateek_arora@brown.edu", "401-935-3094",
								"Hits on all the women. Ugh", c3);
		Person p24 = new Person("Guy", "who snores", "prateek_arora@brown.edu", "401-935-3094",
								"Sleeps in every single meeting", c3);
		
		
		
		
		//Populating allPersons
		s.getPersons().put(prateek.getFullName(), prateek);
		s.getPersons().put(alec.getFullName(), alec);
		s.getPersons().put(rob.getFullName(), rob);
		s.getPersons().put(stephanie.getFullName(), stephanie);
		s.getPersons().put(alwaysBusy.getFullName(), alwaysBusy);
		s.getPersons().put(p1.getFullName(), p1);
		s.getPersons().put(p2.getFullName(), p2);
		s.getPersons().put(p3.getFullName(), p3);
		s.getPersons().put(p4.getFullName(), p4);
		s.getPersons().put(p5.getFullName(), p5);
		s.getPersons().put(p6.getFullName(), p6);
		s.getPersons().put(p7.getFullName(), p7);
		s.getPersons().put(p8.getFullName(), p8);
		s.getPersons().put(p9.getFullName(), p9);
		s.getPersons().put(p10.getFullName(), p10);
		s.getPersons().put(p11.getFullName(), p11);
		s.getPersons().put(p12.getFullName(), p12);
		s.getPersons().put(p13.getFullName(), p13);
		s.getPersons().put(p14.getFullName(), p14);
		s.getPersons().put(p15.getFullName(), p15);
		s.getPersons().put(p16.getFullName(), p16);
		s.getPersons().put(p17.getFullName(), p17);
		s.getPersons().put(p18.getFullName(), p18);
		s.getPersons().put(p19.getFullName(), p19);
		s.getPersons().put(p20.getFullName(), p20);
		s.getPersons().put(p21.getFullName(), p21);
		s.getPersons().put(p22.getFullName(), p22);
		s.getPersons().put(p23.getFullName(), p23);
		s.getPersons().put(p24.getFullName(), p24);
		
		//Group allPeople
		Group allGroups = new Group("Scheddar Corp.");
		
		
		
		//Group engineers
		Group engineers = new Group("engineers");
		engineers.addMember(p1);
		engineers.addMember(p9);
		engineers.addMember(p17);
		engineers.addMember(p2);
		engineers.addMember(p10);
		engineers.addMember(p18);
		engineers.addMember(p3);
		engineers.addMember(p11);
		engineers.addMember(p19);
		engineers.addMember(p20);
		
		//Group marketing
		Group marketing = new Group("marketing");
		marketing.addMember(p4);
		marketing.addMember(p5);
		marketing.addMember(p6);
		marketing.addMember(p12);
		marketing.addMember(p13);
		marketing.addMember(p14);
		marketing.addMember(p20);
		marketing.addMember(p21);
		marketing.addMember(p22);
		marketing.addMember(p23);
		
		//Group managers
		Group managers = new Group("managers");
		managers.addMember(alec);
		managers.addMember(prateek);
		managers.addMember(stephanie);
		managers.addMember(rob);
		managers.addMember(alwaysBusy);
		managers.addMember(p1);
		managers.addMember(p9);
		managers.addMember(p20);
		managers.addMember(p3);
		
		//Setting parent groups
		managers.setParentGroup(allGroups);
		engineers.setParentGroup(allGroups);
		marketing.setParentGroup(allGroups);
		
		ArrayList<Group> listGroups = new ArrayList<Group>();
		listGroups.add(engineers);
		listGroups.add(managers);
		listGroups.add(marketing);
		allGroups.setSubgroups(listGroups);
		
		//Adding things to groups
		s.getGroups().put(allGroups.getName(), allGroups);
		s.getGroups().put(engineers.getName(), engineers);
		s.getGroups().put(marketing.getName(), marketing);
		s.getGroups().put(managers.getName(), managers);
		
		//Groups for Meetings
		ArrayList<Group> justEng = new ArrayList<Group>();
		justEng.add(engineers);
		ArrayList<Group> justMar = new ArrayList<Group>();
		justMar.add(marketing);
		ArrayList<Group> justMan = new ArrayList<Group>();
		justMan.add(managers);
		ArrayList<Group> everyone = new ArrayList<Group>();
		everyone.add(managers);
		everyone.add(engineers);
		everyone.add(marketing);
		
		//Meetings
		ScheddarTime mon11 = new ScheddarTime(1, 11, 60);
		ScheddarTime tue11 = new ScheddarTime(2, 11, 60);
		Meeting m1 = new Meeting("Main Engineeer meeting", thurs1, justEng);
		
		Meeting m4 = new Meeting("Mon Engineeer meeting", mon11, justEng);
		Meeting m5 = new Meeting("Tues Engineeer meeting", tue11, justEng);
		Meeting m6 = new Meeting("Wed Engineeer meeting", wed11, justEng);
		Meeting m7 = new Meeting("Thurs Engineeer meeting", thurs11, justEng);
		Meeting m8 = new Meeting("Fri Engineeer meeting", fri11, justEng);
		
		ScheddarTime tue4 = new ScheddarTime(3, 16, 60);
		
		Meeting m2 = new Meeting("Marketing meeting", tue4, justMar);
		ScheddarTime tue2 = new ScheddarTime(2, 14, 60);
		Meeting m3 = new Meeting("Managers meeting", tue2, justMan);
		
		
		//Adding meetings to meetings HashMap
		s.getMeetings().put(m1.getName(), m1);
		s.getMeetings().put(m2.getName(), m2);
		s.getMeetings().put(m3.getName(), m3);
		s.getMeetings().put(m4.getName(), m4);
		s.getMeetings().put(m5.getName(), m5);
		s.getMeetings().put(m6.getName(), m6);
		s.getMeetings().put(m7.getName(), m7);
		s.getMeetings().put(m8.getName(), m8);
		
		return s;
		
	}

	}