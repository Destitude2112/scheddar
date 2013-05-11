package edu.brown.cs32.scheddar;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;




public class ScheddarToXML {
	
	private Document doc;
	private Scheddar s;
	
	
	public static void main(String[] args) {
		
		Scheddar test = Main.getTestData();
		ScheddarToXML sxml = new ScheddarToXML(test);
		sxml.saveFile(new File("data/steph_test.xml"));
		
	}
	
	
	
	
	
	public ScheddarToXML(Scheddar s) {
		this.s = s;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			encodeScheddar();
			
		} catch (ParserConfigurationException e) {
			System.err.println("Couldn't write xml document");
		}
	}
	
	
	public void saveFile(File saveFile) {
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			
			StreamResult result = new StreamResult(saveFile);
			DOMSource source = new DOMSource(doc);
			
			t.transform(source, result);
		} catch (TransformerConfigurationException
				| TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void encodeScheddar() {
		
		Element scheddar = doc.createElement("scheddar");
		doc.appendChild(scheddar);
		
		Element orgName = doc.createElement("organization");
		orgName.appendChild(doc.createTextNode(s.getName()));
		scheddar.appendChild(orgName);
		
		Element admin = doc.createElement("admin");
		admin.appendChild(doc.createTextNode(s.getAdminName()));
		scheddar.appendChild(admin);
		
		Element email = doc.createElement("email");
		email.appendChild(doc.createTextNode(s.getUsername()));
		scheddar.appendChild(email);
		
		
		Element startTime = doc.createElement("dayStart");
		startTime.appendChild(doc.createTextNode(s.getStartHour()));
		scheddar.appendChild(startTime);
		
		Element endTime = doc.createElement("dayEnd");
		endTime.appendChild(doc.createTextNode(s.getEndHour()));
		scheddar.appendChild(endTime);
		
		Element personList = doc.createElement("personList");
		for (Person p : s.getAllPeople())
			personList.appendChild(encodePerson(p));
		scheddar.appendChild(personList);
		
		Element groupList = doc.createElement("groupList");
		for (Group g : s.getAllGroups())
			groupList.appendChild(encodeGroup(g));
		scheddar.appendChild(groupList);
		
		Element meetingList = doc.createElement("meetingList");
		for (Meeting m : s.getAllMeetings())
			meetingList.appendChild(encodeMeeting(m));
		scheddar.appendChild(meetingList);
		
	
	
	}
	
	
	private Element encodeGroup(Group g) {
		
		Element group = doc.createElement("group");
		
		Element name = doc.createElement("groupName");
		name.appendChild(doc.createTextNode(g.getName()));
		group.appendChild(name);
		
		Element parentGroup = doc.createElement("parentGroup");
		if (g.getParentGroup() != null)
			parentGroup.appendChild(doc.createTextNode(g.getParentGroup().getName()));
		group.appendChild(parentGroup);
		
		Element members = doc.createElement("members");
		for (Person p : g.getMembers()) {
			Element member = doc.createElement("member");
			member.appendChild(doc.createTextNode(p.getFullName()));
			members.appendChild(member);
		}
		group.appendChild(members);
		
		Element subgroups = doc.createElement("subgroups");
		for (Group sub : g.getSubgroups()) {
			Element subgroup = doc.createElement("subgroup");
			subgroup.appendChild(doc.createTextNode(sub.getName()));
			subgroups.appendChild(subgroup);
		}
		group.appendChild(subgroups);
		
		Element rankings = doc.createElement("memberRankings");
		for (String n : g.getMemberRankings().keySet()) {
			Element rank = doc.createElement("ranking");
			rank.setAttribute("name", n);
			rank.setAttribute("score", g.getMemberRankings().get(n).toString());
			rankings.appendChild(rank);
		}
		group.appendChild(rankings);
		
		return group;
	}
	
	
	private Element encodePerson(Person p) {
		
		Element person = doc.createElement("person");
		
		Element firstName = doc.createElement("firstName");
		firstName.appendChild(doc.createTextNode(p.getFirstName()));
		person.appendChild(firstName);
		
		Element lastName = doc.createElement("lastName");
		lastName.appendChild(doc.createTextNode(p.getLastName()));
		person.appendChild(lastName);
		
		Element email = doc.createElement("email");
		email.appendChild(doc.createTextNode(p.getEmail()));
		person.appendChild(email);
		
		Element phone = doc.createElement("phone");
		phone.appendChild(doc.createTextNode(p.getPhoneNum()));
		person.appendChild(phone);
		
		Element description = doc.createElement("description");
		description.appendChild(doc.createTextNode(p.getDescription()));
		person.appendChild(description);
		
		Element conflicts = doc.createElement("conflicts");
		for (ScheddarTime t : p.getConflicts()) {
			conflicts.appendChild(encodeScheddarTime(t));
		}
		person.appendChild(conflicts);
		
		
		return person;
	}
	
	
	private Element encodeMeeting(Meeting m) {
		
		Element meeting = doc.createElement("meeting");
		
		meeting.setAttribute("decided", Boolean.toString(m.isDecided()));
		
		Element name = doc.createElement("name");
		name.appendChild(doc.createTextNode(m.getName()));
		meeting.appendChild(name);
		
		Element description = doc.createElement("description");
		description.appendChild(doc.createTextNode(m.getDescription()));
		meeting.appendChild(description);
		
		Element finalTime = doc.createElement("finalTime");
		if (m.isDecided())
			finalTime.appendChild(encodeScheddarTime(m.getFinalTime()));
		meeting.appendChild(finalTime);
		
		Element proposedTimes = doc.createElement("proposedTimes");
		for (ScheddarTime t : m.getProposedTimes())
			proposedTimes.appendChild(encodeScheddarTime(t));
		meeting.appendChild(proposedTimes);
		
		Element groupsInvolved = doc.createElement("groupsInvolved");
		for (Group g : m.getGroupsInvolved()) {
			Element groupName = doc.createElement("groupName");
			groupName.appendChild(doc.createTextNode(g.getName()));
			groupsInvolved.appendChild(groupName);
		}
		meeting.appendChild(groupsInvolved);
		
		Element peopleInvolved = doc.createElement("peopleInvolved");
		for (Map.Entry<Person, Double> p : m.getExtraPeopleToImportance().entrySet()) {
			Element extraPerson = doc.createElement("extraPerson");
			extraPerson.setAttribute("name", p.getKey().getFullName());
			extraPerson.setAttribute("score", Double.toString(p.getValue()));
		}
		meeting.appendChild(peopleInvolved);
		
		return meeting;
		
		
	}
	
	
	private Element encodeScheddarTime(ScheddarTime t) {
		
		Element scheddarTime = doc.createElement("scheddarTime");
		scheddarTime.setAttribute("startHour", Integer.toString(t.getStartHour()));
		scheddarTime.setAttribute("startMinutes", Integer.toString(t.getStartMinutes()));
		scheddarTime.setAttribute("duration", Integer.toString(t.getDuration()));
		scheddarTime.setAttribute("dayOfWeek", Integer.toString(t.getDayOfWeek()));
		scheddarTime.setAttribute("day", Integer.toString(t.getDay()));
		scheddarTime.setAttribute("month", Integer.toString(t.getMonth()));
		scheddarTime.setAttribute("year", Integer.toString(t.getYear()));
		scheddarTime.setAttribute("isRecurring", Boolean.toString(t.isRecurring()));
		return scheddarTime;
		
	}
	

}
