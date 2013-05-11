package edu.brown.cs32.scheddar;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLtoScheddar {

	Document doc;
	Scheddar s;


	public XMLtoScheddar(File file) {

		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException e) {
			System.err.println("Couldn't write xml document");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Scheddar parseScheddar() {
		Element n = doc.getDocumentElement();


		s = new Scheddar();

		s.setName(n.getElementsByTagName("organization").item(0).getTextContent());
		s.setAdminName(n.getElementsByTagName("admin").item(0).getTextContent());
		s.setStartHour(n.getElementsByTagName("dayStart").item(0).getTextContent());
		s.setEndHour(n.getElementsByTagName("dayEnd").item(0).getTextContent());
		s.setUsername(n.getElementsByTagName("email").item(0).getTextContent());


		NodeList personList = n.getElementsByTagName("personList").item(0).getChildNodes();
		NodeList groupList = n.getElementsByTagName("groupList").item(0).getChildNodes();
		NodeList meetingList = n.getElementsByTagName("meetingList").item(0).getChildNodes();


		for (int i = 0; i < personList.getLength(); i++) {
			if (personList.item(i).getNodeType() == Node.ELEMENT_NODE)
				s.addPerson(parsePerson(personList.item(i)));


		}

		for (int i = 0; i < groupList.getLength(); i++) {
			if (groupList.item(i).getNodeType() == Node.ELEMENT_NODE)
				s.addGroup(parseGroup(groupList.item(i)));


		}

		for (int i = 0; i < groupList.getLength(); i++) {
			if (groupList.item(i).getNodeType() == Node.ELEMENT_NODE)
				s.addGroup(parseGroupSecond(groupList.item(i)));


		}


		for (int i = 0; i < meetingList.getLength(); i++) {
			if (meetingList.item(i).getNodeType() == Node.ELEMENT_NODE)
				s.addMeeting(parseMeeting(meetingList.item(i)));


		}



		return s;
	}


	private Meeting parseMeeting(Node n) {
		if (n.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) n;

			Meeting m = new Meeting();

			m.setName(e.getElementsByTagName("name").item(0).getTextContent());
			m.setDescription(e.getElementsByTagName("description").item(0).getTextContent());
			m.setFinalTime(parseScheddarTime(e.getElementsByTagName("finalTime").item(0)));

			NodeList propTimes = e.getElementsByTagName("proposedTimes").item(0).getChildNodes();

			for (int i = 0; i < propTimes.getLength(); i++) {
				if (propTimes.item(i).getNodeType() == Node.ELEMENT_NODE)
					m.addProposedTime(parseScheddarTime(propTimes.item(i)));
			}

			NodeList groups = e.getElementsByTagName("groupsInvolved").item(0).getChildNodes();

			for (int i = 0; i < groups.getLength(); i++) {
				m.addGroupInvolved(s.getGroup(groups.item(i).getTextContent()));
			}

			return m;
		}
		return null;
	}


	private Group parseGroup(Node n) {
		if (n.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) n;

			String name = e.getElementsByTagName("groupName").item(0).getTextContent();
			Group g = new Group(name);

			NodeList memberList = e.getElementsByTagName("members").item(0).getChildNodes();

			for (int i = 0; i < memberList.getLength(); i++) {
				g.addMember(s.getPerson(memberList.item(i).getTextContent()));
			}
			return g;
		}
		return null;
	}


	private Group parseGroupSecond(Node n) {
		if (n.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) n;

			String name = e.getElementsByTagName("groupName").item(0).getTextContent();
			Group g = s.getGroup(name);

			NodeList subgroups = e.getElementsByTagName("subgroups").item(0).getChildNodes();

			for (int i = 0; i < subgroups.getLength(); i++) {
				g.addSubgroup(s.getGroup(subgroups.item(i).getTextContent()));
			}

			g.setParentGroup(s.getGroup(e.getElementsByTagName("parentGroup").item(0).getTextContent()));









			return g;
		}
		return null;
	}



	private Person parsePerson(Node n) {
		if (n.getNodeType() == Node.ELEMENT_NODE) {
			Person p = new Person("", "", "", "", "", new LinkedList<ScheddarTime>());
			Element e = (Element) n;

			p.setFirstName(e.getElementsByTagName("firstName").item(0).getTextContent());
			p.setLastName(e.getElementsByTagName("lastName").item(0).getTextContent());
			p.setEmail(e.getElementsByTagName("email").item(0).getTextContent());
			p.setPhoneNum(e.getElementsByTagName("phone").item(0).getTextContent());
			p.setDescription(e.getElementsByTagName("description").item(0).getTextContent());

			NodeList conflictList = e.getElementsByTagName("conflicts").item(0).getChildNodes();

			for (int i = 0; i < conflictList.getLength(); i++) {
				p.addConflict(parseScheddarTime(conflictList.item(i)));
			}

			return p;
		}
		return null;
	}


	private ScheddarTime parseScheddarTime(Node n) {
		if (n.getNodeType() == Node.ELEMENT_NODE) {
			Element ele = (Element) n;

			int year, month, day, dayOfWeek, duration, startHour, startMinutes;
			boolean isRecurring;


			try {
				year = Integer.parseInt(ele.getAttribute("year"));
			} catch (NumberFormatException e) {
				year = 0;
			}
			try {
				month = Integer.parseInt(ele.getAttribute("month"));
			} catch (NumberFormatException e) {
				month = 0;
			}
			try {
				day = Integer.parseInt(ele.getAttribute("day"));
			} catch (NumberFormatException e) {
				day = 0;
			}
			try {
				dayOfWeek = Integer.parseInt(ele.getAttribute("dayOfWeek"));
			} catch (NumberFormatException e) {
				dayOfWeek = 0;
			}
			try {
				duration = Integer.parseInt(ele.getAttribute("duration"));
			} catch (NumberFormatException e) {
				duration = 0;
			}
			try {
				startHour = Integer.parseInt(ele.getAttribute("startHour"));
			} catch (NumberFormatException e) {
				startHour = 0;
			}
			try {
				startMinutes = Integer.parseInt(ele.getAttribute("startMinutes"));
			} catch (NumberFormatException e) {
				startMinutes = 0;
			}
			isRecurring = Boolean.parseBoolean(ele.getAttribute("isRecurring"));

			return new ScheddarTime(startHour, startMinutes, duration, dayOfWeek, dayOfWeek, month, year, isRecurring);
		}
		return null;
	}
}
