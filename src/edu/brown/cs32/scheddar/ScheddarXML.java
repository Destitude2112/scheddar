package edu.brown.cs32.scheddar;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ScheddarXML{
	
	Scheddar myScheddar;
	DocumentBuilderFactory dbf; //structures we just need to write an XML file and read from it
	DocumentBuilder db;
	Document doc;
	
	ScheddarXML(Scheddar myScheddar){
		try {
			this.myScheddar = myScheddar;
			this.dbf = DocumentBuilderFactory.newInstance();
			this.db = dbf.newDocumentBuilder();
			doc = db.newDocument();
		} catch (ParserConfigurationException e) {
			System.out.println("ERROR: Error generating the XML file");
		}
	}
	
	public void makeDBFromXML(String dest) {
		makeDBFromXML(new File(dest));
	}
	
	/**
	 * 
	 * When the application loads up, this function reads the XML and populates the groups 
	 * HashMap in the Main
	 * 
	 * @param localXMLFile the destination of the XML file to read from
	 */
	public void makeDBFromXML(File localXMLFile){
		try {
			
			myScheddar.setGroups(new HashMap<String, Group>());
			
				//File localXMLFile = new File (dest);
			
				doc = db.parse(localXMLFile);
				
				doc.getDocumentElement().normalize();
				
				System.out.println(" The first element is: "+ doc.getDocumentElement().getNodeName());
				
				NodeList nl = doc.getElementsByTagName("group");
				NodeList n2 = doc.getElementsByTagName("person");
				NodeList n3 = doc.getElementsByTagName("meeting");
				
				System.out.println("----------------");
				
				for(int i = 0; i<nl.getLength(); i++){
					
					Node currNode = nl.item(i);
					
					System.out.println("Current element is: "+ currNode.getNodeName()); //just prints "group"
					
					String currName, currMembers, currMemberRankings, currSubgroups, currParentGroup;
					
					if(currNode.getNodeType() == Node.ELEMENT_NODE && currNode.getNodeName().equals("group")){
						Element e = (Element)currNode;
						
						currName = e.getAttribute("name");
						currMembers = e.getElementsByTagName("members").item(0).getTextContent();
						currMemberRankings = e.getElementsByTagName("memberRankings").item(0).getTextContent();
						currSubgroups = e.getElementsByTagName("subgroups").item(0).getTextContent();
						currParentGroup = e.getElementsByTagName("parentGroup").item(0).getTextContent();
						
						
						System.out.println("Name: "+ currName);
						System.out.println("Members: "+ currMembers);
						System.out.println("Member Rankings: "+ currMemberRankings);
						System.out.println("Subgroups: "+ currSubgroups);
						System.out.println("Parent Group: "+ currParentGroup);
					    
						Group g = new Group(currName, getMembersFromString(currMembers), getHMFromString(currMemberRankings), getGroupsFromString(currSubgroups), currParentGroup);
						myScheddar.getGroups().put(g.getName(), g);
						
					    System.out.println("One group added to the allGroups");
					    
					 } 
				}
					
				
				for(int i = 0; i<n2.getLength(); i++){
					
					Node currNode = n2.item(i);
					
					String currEmailID, currFirstName, currLastName, currPhone, currDescription, currConflicts;
					
					if (currNode.getNodeType() == Node.ELEMENT_NODE && currNode.getNodeName().equals("person")){
                        Element e = (Element)currNode;
						
						currEmailID =e.getAttribute("emailId");
						currFirstName = e.getElementsByTagName("firstName").item(0).getTextContent();
						currLastName = e.getElementsByTagName("lastName").item(0).getTextContent();
						currPhone = e.getElementsByTagName("phone").item(0).getTextContent();
						currDescription = e.getElementsByTagName("description").item(0).getTextContent();
						currConflicts = e.getElementsByTagName("conflicts").item(0).getTextContent();
						//currGroupMembership = e.getElementsByTagName("group_membership").item(0).getTextContent();
						
						System.out.println("Email id: "+ currEmailID);
						System.out.println("First Name: "+ currFirstName);
						System.out.println("Last Name: "+ currLastName);
						System.out.println("Phone: "+ currPhone);
						System.out.println("Description: "+ currDescription);
						System.out.println("Conflicts: "+ currConflicts);
						
					    //System.out.println("Group membership: " + currGroupMembership);
					    
						
						ArrayList<ScheddarTime> conflicts = getConflictsFromString(currConflicts);
					    Person p = new Person(currEmailID, currFirstName, currLastName, currPhone, currDescription, conflicts);
					    myScheddar.getPersons().put(currFirstName+" " + currLastName, p);
					    
					    System.out.println("");
					    
					}
					
				}
				
				for(int i = 0; i<n3.getLength(); i++){
					
					Node currNode = n3.item(i);
					String name;
					Boolean decided;
					ScheddarTime timeForFinalizing;
				    ScheddarTime finalTime;
				    List<ScheddarTime> proposedTimes;
				    HashMap<Integer, Double> indexToScore;
				    List<String> groupsInvolved;
					String description;
					
					String currName, currDecided, currTimeForFinalizing, currFinalTime,
					currProposedTimes, currIndexToScore, currGroupsInvolved, currDescription,
					currExtraPeopleToImportance; 
					
					if (currNode.getNodeType() == Node.ELEMENT_NODE && currNode.getNodeName().equals("meeting")){
                        Element e = (Element)currNode;
						
                        System.out.println("MEETING WAS READ");
                        
						currName =e.getAttribute("name");
						currDecided = e.getElementsByTagName("decided").item(0).getTextContent();
						currTimeForFinalizing = e.getElementsByTagName("timeForFinalizing").item(0).getTextContent();
						currFinalTime = e.getElementsByTagName("finalTime").item(0).getTextContent();
						currProposedTimes = e.getElementsByTagName("proposedTimes").item(0).getTextContent();
						currIndexToScore = e.getElementsByTagName("indexToScore").item(0).getTextContent();
						currGroupsInvolved = e.getElementsByTagName("groupsInvolved").item(0).getTextContent();
						currDescription = e.getElementsByTagName("description").item(0).getTextContent();
						currExtraPeopleToImportance = e.getElementsByTagName("extraPeopleToImportance").item(0).getTextContent();
						
						//currGroupMembership = e.getElementsByTagName("group_membership").item(0).getTextContent();
						
						System.out.println("Name: "+ currName);
						System.out.println("Decided: "+ currDecided);
						System.out.println("Time for Finalizing: "+ currTimeForFinalizing);
						System.out.println("Final Time: "+ currFinalTime);
						System.out.println("Proposed Times: "+ currProposedTimes);
						System.out.println("Index to Score: "+ currIndexToScore);
						System.out.println("Dummy Groups Involved: "+ currGroupsInvolved);
						System.out.println("Description: "+ currDescription);
						System.out.println("Extra People to Importance: "+ currExtraPeopleToImportance);
						
					    //System.out.println("Group membership: " + currGroupMembership);
					    
						
						//ArrayList<ScheddarTime> conflicts = getConflictsFromString(currConflicts);
					    Meeting m = new Meeting(currName, Boolean.parseBoolean(currDecided), 
					    		STFromString(currTimeForFinalizing), STFromString(currFinalTime),
					    		getConflictsFromString(currProposedTimes), 
					    		getHM2FromString(currIndexToScore), 
					    		getGroupsFromString(currGroupsInvolved),
					    		currDescription,
					    		getHMFromString(currExtraPeopleToImportance));
					    System.out.println("ADDING MEETING TO MEETINGS " + m.getName());
					    System.out.println(" CURRENT SIZE: " + myScheddar.getMeetings().size());
					    myScheddar.getMeetings().put(m.getName(), m);
					    
					    System.out.println("");
					    
					}
					
				}
				
				dummyToReal();
				
				
				
			} catch (Exception e) {
				System.out.println("Problem reading the XML File");
				e.printStackTrace();
			}
			
			
	}
	
	
	/**
	 * 
	 * When reading in the XML files, some of the data structures to be added require looking up hashmaps
	 * that are in turn being created
	 * To deal with the problem, the loading is done in two steps:
	 * Step 1: Loading all XML files to get stuff in terms of strings
	 * Step 2: Converting this text to actual objects by looking up hashmaps just created
	 * This function does the 2nd step
	 * 
	 */
	public void dummyToReal(){
		
		//Getting from dummy to real classes
		
		//Groups
		Iterator it = myScheddar.getGroups().entrySet().iterator();
	    while (it.hasNext()) {
	    	
	    	Map.Entry pairs = (Map.Entry)it.next();
	        Group currGroup = (Group) pairs.getValue();
	        
	       //Go for dummyMemberRankings to member rankings
	        /*HashMap<Person, Double> memberRankings = new HashMap<Person, Double>();
	        Iterator it2 = currGroup.dummyMemberRankings.entrySet().iterator();
	        while(it2.hasNext()){
	        	
	        	Map.Entry p = (Map.Entry)it2.next();
	        	String name = (String) p.getKey();
		        Double g = (Double) p.getValue();
		        
		        if(myScheddar.allPersons.containsKey(name)){
		        memberRankings.put(myScheddar.allPersons.get(name), g);
		        }
		        
	        	it2.remove();
	        }*/
	        
	        //Change dummySubgroups to subgroups
	        ArrayList<Group> subgroups = new ArrayList<Group>();
	        for(String dummySubgroup: currGroup.getDummySubgroups()){
	        	
	        	if(myScheddar.getGroups().containsKey(dummySubgroup)){
	        	subgroups.add(myScheddar.getGroups().get(dummySubgroup));
	        	}
	        	
	        }
	        
	        //Change dummyParentGroup to parentGroup
	        Group parentGroup;
	        if(myScheddar.getGroups().containsKey(currGroup.dummyParentGroup)){
	        	parentGroup = myScheddar.getGroups().get(currGroup.dummyParentGroup);
	        } else{
	            parentGroup = null;
	        }
	        
	        
	        //Change dummyMembers to members
	        ArrayList<Person> members = new ArrayList<Person>();
	        for(String dummyMember: currGroup.dummyMembers){
	    	   
	    	   if(myScheddar.getPersons().containsKey(dummyMember)){
	    		   members.add(myScheddar.getPersons().get(dummyMember));
	    	   }
	        }  
	        
	    	currGroup.setMembers(members);
	    	if(parentGroup!=null && !parentGroup.getName().equals("")){
	        currGroup.setParentGroup(parentGroup);
	    	}
	        currGroup.setSubgroups(subgroups);
	        
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
	    
	    
	    //Meetings
	    Iterator it4 =  myScheddar.getMeetings().entrySet().iterator();
	    while(it4.hasNext()){
	    	
	    	Map.Entry pairs = (Map.Entry)it4.next();
	        Meeting currMeeting = (Meeting) pairs.getValue();
	    	
	        ArrayList<Group> groupsInvolved = new ArrayList<Group>();
	        
	        //Change dummyGroupsInvolved to groupsInvolved
	        for(String currDummyGroup: currMeeting.dummyGroupsInvolved){
	        	
	        	if(myScheddar.getGroups().containsKey(currDummyGroup)){
	        		groupsInvolved.add(myScheddar.getGroups().get(currDummyGroup));
	        	}
	        }
	        
	        //Change dummyExtraPeopleToImportance to extraPeopleToImportance
	        HashMap<Person, Double> memberRankings = new HashMap<Person, Double>();
	        Iterator it2 = currMeeting.dummyExtraPeopleToImportance.entrySet().iterator();
	        while(it2.hasNext()){
	        	
	        	Map.Entry p = (Map.Entry)it2.next();
	        	String name = (String) p.getKey();
		        Double g = (Double) p.getValue();
		        
		        if(myScheddar.getPersons().containsKey(name)){
		        memberRankings.put(myScheddar.getPersons().get(name), g);
		        }
		        
	        	//it2.remove();
	        }
	        
	        currMeeting.setGroupsInvolved(groupsInvolved);
	    	//it4.remove();
	    }
	    
	}
	
	
	/**
	 * 
	 * Gets conflicts from a string representation
	 * 
	 * @param conflicts String representation of conflicts
	 * @return an ArrayList of conflicts
	 */
	public ArrayList<ScheddarTime> getConflictsFromString(String conflicts){
		
		ArrayList<ScheddarTime> stList = new ArrayList<ScheddarTime>();
		
		String[] conflictArr = conflicts.split(",");
		
		for(String conflict: conflictArr){
			
			String[] parts = conflict.split("/");
			
			
			if(parts.length<7) return new ArrayList<ScheddarTime>();
			
			ScheddarTime currST = new ScheddarTime(
			getInt(parts[0]), getInt(parts[1]), 
			getInt(parts[2]), getInt(parts[3]),
			getInt(parts[4]), getInt(parts[4]),
			getInt(parts[6]), Boolean.parseBoolean(parts[7]));
			stList.add(currST);
			
		}
		
		
		return stList;
	}
	
	
	int getInt(String s){
		
		if(s.equals("")) return 0;
		else return Integer.parseInt(s);
	}
	
	/**
	 * 
	 * A function to get ScheddarTime from its string representation
	 * 
	 * @param st The string representation of the Scheddar Time
	 * @return The ScheddarTime parsed from the string
	 */
	public ScheddarTime STFromString(String st){
		
		String[] parts = st.split("/");
		
		ScheddarTime currST = new ScheddarTime(
		Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), 
		Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
		Integer.parseInt(parts[4]), Integer.parseInt(parts[4]),
		Integer.parseInt(parts[6]), Boolean.parseBoolean(parts[7]));
		
		return currST;
		
	}
	
	
	/**
	 * 
	 * A function used to get the members from a string
	 * 
	 * @param src The source in string
	 * @return The members split by commas
	 */
	public String[] getMembersFromString(String src){
		
		return src.split(",");
		
	}
	
	
	/**
	 * 
	 * Helper function to get the actual members from a string separated by commas
	 * 
	 * @param src the string
	 * @return an array of members
	 */
	public ArrayList<String> getGroupsFromString(String src){
		
		ArrayList<String> toReturn = new ArrayList<String>();
		
		String[] arr = src.split(",");
		for(int i = 0; i<arr.length; i++){
			toReturn.add(arr[i]);
		}
		
		return toReturn;
	}
	
	
	/**
	 * 
	 * Helper function used to build a HashMap from a string
	 * 
	 * @param src the string
	 * @return the HashMap
	 */
	public HashMap<String, Double> getHMFromString(String src){
		
		String[] KVPairs = src.split(";");
		
		HashMap<String, Double> hm = new HashMap<String, Double>();
		
		for(String KVPair: KVPairs){
			String[] currKVArr = KVPair.split(",");
			if(currKVArr.length==2){
			hm.put(currKVArr[0], Double.parseDouble(currKVArr[1]));
			}
		}
		
		return hm;
	}
	
	
	/**
	 * 
	 * A function used to get a HashMap<Integer,Double> from its string representation
	 * 
	 * @param src The string representation
	 * @return The HashMap<Integer, Double>
	 */
	public HashMap<Integer, Double> getHM2FromString(String src){
		
		String[] KVPairs = src.split(";");
		
		HashMap<Integer, Double> hm = new HashMap<Integer, Double>();
		
		for(String KVPair: KVPairs){
			String[] currKVArr = KVPair.split(",");
			if(currKVArr.length==2){
			hm.put(Integer.parseInt(currKVArr[0]), Double.parseDouble(currKVArr[1]));
			}
		}
		
		return hm;
	}
	
	

	
	
	/**
	 * Helper function used to generate a string from a string array
	 * 
	 * @param members the actual string array members
	 * @return the string with all the members separated by commas
	 */
	public String generateMembersString(String[] members){
		
		String toReturn= members[0];
		
	    
		for(int i=1; i<members.length; i++){
			toReturn += "," + members[i];
		}
		
		return toReturn;
		
	}
	
	/** 
	 * Overloaded function
	 * Helper function used to generate a string from a string array
	 * 
	 * @param members the actual string array members
	 * @return the string with all the members separated by commas
	 */
	public String generateMembersString(List<String> members){
		
		if(members.size() == 0) return "";
		else{
			
		String toReturn= members.get(0);
		
	    
		for(int i=1; i<members.size(); i++){
			toReturn += "," + members.get(i);
		}
		
		return toReturn;
		}
		
	}
	
	
	/**
	 * 
	 * Helper function used to generate a string from a HashMap mapping a String to a Double
	 * 
	 * @param hm the HashMap
	 * @return the parsed string
	 */
	public String generateMemberRankingsString(HashMap<String,Double> hm){
		
		String toReturn = "";
		
		
		if(hm.size() == 0){
			return "";
		} else{
		
		    Iterator it = hm.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        toReturn+= pairs.getKey() + ","+pairs.getValue()+";";
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		    
		    //Remove the last ";"
		    toReturn = toReturn.substring(0, toReturn.length()-1);
		    
		    
		    return toReturn;
		}
		
	}
	
	
	/**
	 * 
	 * Helper function to generate a string from the list of subgroups
	 * 
	 * @param subgroups the String array of subgroups
	 * @return the string with a comma-separated list of subgroups
	 */
	public String generateSubgroupString(ArrayList<String> subgroups){
		
		if(subgroups.size()==0){
			return "";
		}
		else{
			String toReturn= subgroups.get(0);
		
	    
			for(int i=1; i<subgroups.size(); i++){
				toReturn += "," + subgroups.get(i);
			}
		
			return toReturn;
		}
		
	}
	
	
	/*
	 * String name;
	Boolean decided;
	ScheddarTime timeForFinalizing;
    ScheddarTime finalTime;
    List<ScheddarTime> proposedTimes;
    HashMap<Integer, Double> indexToScore;
    List<String> groupsInvolved;
	String description;
	 */
	
	/**
	 * 
	 * Helper function to add a meeting element to an XML file
	 * 
	 * @param currElement The current XMl element all this has to be added to
	 * @param currName Current Name
	 * @param currDecided Current Decided
	 * @param currTimeForFinalizing Current Time for Finalizing
	 * @param currFinalTime Current Final Time
	 * @param currProposedTimes Current Proposed Times (List of Proposed Times)
	 * @param currIndexToScore Current Index to Score HashMap
	 * @param currGroupsInvolved Current Groups Involved ArrayList
	 * @param currDescription Current Description
	 */
	public void addMeetingElement(Element currElement, String currName, Boolean currDecided, ScheddarTime currTimeForFinalizing,
			ScheddarTime currFinalTime, List<ScheddarTime> currProposedTimes, HashMap<Integer,Double> currIndexToScore,
			List<String> currGroupsInvolved, String currDescription, HashMap<Person, Double> extraPeopleToImportance){
		
       System.out.println("Entered add Meeting element");
		
		Element staff = doc.createElement("meeting");
		currElement.appendChild(staff);
		
		//name element
		Attr attr = doc.createAttribute("name");
		attr.setValue(currName);
		staff.setAttributeNode(attr);
		
		//decided elements
		Element decided = doc.createElement("decided");
		decided.appendChild(doc.createTextNode(currDecided.toString()));
		staff.appendChild(decided);
		
		//timeForFinalizing elements
		Element timeForFinalizing = doc.createElement("timeForFinalizing");
		timeForFinalizing.appendChild(doc.createTextNode(STToString(currTimeForFinalizing)));
		staff.appendChild(timeForFinalizing);
		
		//finalTime elements
		Element finalTime = doc.createElement("finalTime");
		finalTime.appendChild(doc.createTextNode(STToString(currFinalTime)));
		staff.appendChild(finalTime);
		
		//proposedTimes elements
		Element proposedTimes = doc.createElement("proposedTimes");
		proposedTimes.appendChild(doc.createTextNode(STsToString((ArrayList<ScheddarTime>) currProposedTimes)));
		staff.appendChild(proposedTimes);
		
		//indexToScore elements
		Element indexToScore = doc.createElement("indexToScore");
		indexToScore.appendChild(doc.createTextNode(generateMeetingHMToString(currIndexToScore)));
		staff.appendChild(indexToScore);
		
		//groupsInvolved elements
		Element groupsInvolved = doc.createElement("groupsInvolved");
		groupsInvolved.appendChild(doc.createTextNode(subgroupToString((ArrayList<String>) currGroupsInvolved)));
		staff.appendChild(groupsInvolved);
		
		//description group element
		Element description = doc.createElement("description");
		description.appendChild(doc.createTextNode(currDescription));
		staff.appendChild(description);
		
		//dummyExtraPeopleToImportance element
		Element epti = doc.createElement("extraPeopleToImportance");
		epti.appendChild(doc.createTextNode(generateMeetingHMToString2(extraPeopleToImportance)));
		staff.appendChild(epti);
		
	}
	
	/**
	 * 
	 * A function used to convert the subgroups to string for XML storage
	 * 
	 * @param str The ArratList<String> to be converted to string
	 * @return The string representation
	 */
	public String subgroupToString(ArrayList<String> str){
		
		String toReturn = "";
		
		if(str.size()==0) return "";
		
		for(String s:str){
			toReturn = s+",";
		}
		
		toReturn = toReturn.substring(0, toReturn.length()-1);
		return toReturn;
	}
	
	
	/**
	 * 
	 * This function is used to convert a HashMap<Integer, Double> to String for
	 * XML storage
	 * 
	 * @param hm the HashMap<Integer, Double> to be converted to String
	 * @return the String conversion
	 */
	public String generateMeetingHMToString(HashMap<Integer, Double> hm){
		
		String toReturn = "";
		
		if(hm.size() == 0){
			return "";
		} else{
		
		    Iterator it = hm.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        toReturn+= pairs.getKey() + ","+pairs.getValue()+";";
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		    
		    //Remove the last ";"
		    toReturn = toReturn.substring(0, toReturn.length()-1);
		   
		    return toReturn;
		}
		
	}		
	
	/**
	 * 
	 * This function is used to convert a HashMap<Integer, Double> to String for
	 * XML storage
	 * 
	 * @param extraPeopleToImportance the HashMap<Integer, Double> to be converted to String
	 * @return the String conversion
	 */
	public String generateMeetingHMToString2(HashMap<Person, Double> extraPeopleToImportance){
		
		String toReturn = "";
		
		if(extraPeopleToImportance.size() == 0){
			return "";
		} else{
		
		    Iterator it = extraPeopleToImportance.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        Person p = (Person)pairs.getKey();
		        toReturn+=  p.getFullName()+ ","+pairs.getValue()+";";
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		    
		    //Remove the last ";"
		    toReturn = toReturn.substring(0, toReturn.length()-1);
		   
		    return toReturn;
		}
		
	}
	
	/**
	 * 
	 * Helper function used to add write the current group element to the XML
	 * 
	 * @param currElement An element object to which all the data is added
	 * @param currName Name of the current group
	 * @param currMembers Members of the current group
	 * @param currMemberRankings MemberRankings of the current group
	 * @param currSubgroups subgroups of the current group
	 * @param currParentGroup parentGroup of the current group
	 */
	public void addGroupElement(Element currElement, String currName, List<String> currMembers, HashMap<String,Double> currMemberRankings, ArrayList<String> currSubgroups, String currParentGroup){
		
		
		System.out.println("Entered add Group element");
		
		Element staff = doc.createElement("group");
		currElement.appendChild(staff);
		
		Attr attr = doc.createAttribute("name");
		attr.setValue(currName);
		staff.setAttributeNode(attr);
		
		System.out.println("Got the name");
		
		//members elements
		Element members = doc.createElement("members");
		members.appendChild(doc.createTextNode(generateMembersString(currMembers)));
		staff.appendChild(members);
		
		System.out.println("Got the members");
		
		//memberRankings elements
		Element memberRankings = doc.createElement("memberRankings");
		memberRankings.appendChild(doc.createTextNode(generateMemberRankingsString(currMemberRankings)));
		staff.appendChild(memberRankings);
		
		//subgroups elements
		Element subgroups = doc.createElement("subgroups");
		subgroups.appendChild(doc.createTextNode(generateSubgroupString(currSubgroups)));
		staff.appendChild(subgroups);
		
		//parent group element
		//System.out.println("curr parent group: "+ currParentGroup);
		if (currParentGroup==null) currParentGroup = "";
		Element parentGroup = doc.createElement("parentGroup");
		parentGroup.appendChild(doc.createTextNode(currParentGroup));
		staff.appendChild(parentGroup);
		
		//group_membership elements
		/*Element group_membership = doc.createElement("group_membership");
		
		String allGroups= "";
		for(Group currGroup: curr_group_membership){
			allGroups.concat(currGroup.name+ ",");
		}
		
		group_membership.appendChild(doc.createTextNode(allGroups));
		staff.appendChild(group_membership);*/
		
		
	}
	
	
	/**
	 * This is the main function that is called to go through the groups HashMap in the main
	 * and add everything to the XML
	 */
	public void saveLocalDataToXML(){
		
		try{			
			this.dbf = DocumentBuilderFactory.newInstance();
			this.db = dbf.newDocumentBuilder();
			this.doc = db.newDocument();
			Element firstElement = doc.createElement("All");
			doc.appendChild(firstElement);
			
			
			Element groupElement = doc.createElement("group");
			firstElement.appendChild(groupElement);
			
			//Add all groups to the XML
			 Iterator it = myScheddar.getGroups().entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pairs = (Map.Entry)it.next();
			        Group currGroup = (Group) pairs.getValue();
			        addGroupElement(groupElement, currGroup.getName(), currGroup.dummyMembers, currGroup.getMemberRankings(), 
			        		(ArrayList<String>)currGroup.getDummySubgroups(), currGroup.dummyParentGroup);
			        it.remove(); // avoids a ConcurrentModificationException
			    }
			
			//Add all persons to the XML    
			Element personElement = doc.createElement("person");    
			firstElement.appendChild(personElement);
			
			System.out.println("all persons has "+ myScheddar.getPersons().size());
			
			Iterator it2 = myScheddar.getPersons().entrySet().iterator();
			    while (it2.hasNext()) {
			        Map.Entry pairs = (Map.Entry)it2.next();
			        Person currPerson = (Person) pairs.getValue();
			        System.out.println("adding a person element");
			        addPersonElement(personElement, currPerson.getEmail(), currPerson.getFirstName(),
			        		currPerson.getLastName(), currPerson.getPhoneNum(),
			        		currPerson.getDescription(), (ArrayList<ScheddarTime>)currPerson.getConflicts());
			        it2.remove(); // avoids a ConcurrentModificationException
			    }
			
			//Add all meetings to the XML
			Element meetingElement = doc.createElement("meeting");
			firstElement.appendChild(meetingElement);
			
			System.out.println(" all meetings has "+ myScheddar.getMeetings().size()+ " members");
			
			Iterator it3 = myScheddar.getMeetings().entrySet().iterator();
			while(it3.hasNext()){
				Map.Entry pairs = (Map.Entry)it3.next();
				Meeting currMeeting = (Meeting) pairs.getValue();
				System.out.println("adding a meeting element");
				
				addMeetingElement(meetingElement, currMeeting.getName(), currMeeting.getDecided(), 
				currMeeting.getTimeForFinalizing(),
			    currMeeting.getFinalTime(), currMeeting.getProposedTimes(), currMeeting.getIndexToScore(),
			    currMeeting.dummyGroupsInvolved, currMeeting.getDescription(),
			    currMeeting.getExtraPeopleToImportance());
				
				
				it3.remove();
			}
			    
			//Write this into an XML file now and save the file locally
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource src = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(myScheddar.getDest()));
			
			t.transform(src, result);
			
			System.out.println("File saved");
			
		}catch(Exception e){
			System.out.println("Error writing the local data");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * Helper function used to add the current person element to the XML
	 * 
	 * @param currElement The element object everything is being added to
	 * @param currEmail The email of the current person
	 * @param currFirstName 
	 * @param currLastName
	 * @param currPhone
	 * @param currDescription
	 * @param curr_group_membership
	 */
	public void addPersonElement(Element currElement, String currEmail, String currFirstName, String currLastName, String currPhone, String currDescription, ArrayList<ScheddarTime> currConflicts){
		
		Element staff = doc.createElement("person");
		currElement.appendChild(staff);
		
		Attr attr = doc.createAttribute("emailId");
		attr.setValue(currEmail);
		staff.setAttributeNode(attr);
		
		//first name elements
		Element firstName = doc.createElement("firstName");
		firstName.appendChild(doc.createTextNode(currFirstName));
		staff.appendChild(firstName);
		
		//nickname elements
		Element lastname = doc.createElement("lastName");
		lastname.appendChild(doc.createTextNode(currLastName));
		staff.appendChild(lastname);
		
		//nickname elements
		Element phone = doc.createElement("phone");
		phone.appendChild(doc.createTextNode(currPhone));
		staff.appendChild(phone);
		
		//description elements
		Element description = doc.createElement("description");
		description.appendChild(doc.createTextNode(currDescription));
		staff.appendChild(description);
		
		//conflicts elements
		Element conflicts = doc.createElement("conflicts");
		conflicts.appendChild(doc.createTextNode(STsToString(currConflicts)));
		staff.appendChild(conflicts);
		
		//group_membership elements
		/*Element group_membership = doc.createElement("group_membership");
		
		String allGroups= "";
		for(String currGroup: curr_group_membership){
			allGroups.concat(currGroup+ ",");
		}
		
		group_membership.appendChild(doc.createTextNode(allGroups));
		staff.appendChild(group_membership);*/
		
	}
	
	
	/**
	 * A function used to convert ScheddarTime to String for XMl storage purposes
	 * 
	 * @param conflicts ArrayList<ScheddarTime> to be converted to String
	 * @return the String conversion so that it can be used for XML storage
	 */
	public String STsToString(ArrayList<ScheddarTime> conflicts){
		
		
		if(conflicts.size()==0) return "";
		
		String toReturn = "";
		
		for(ScheddarTime conflict: conflicts){
			toReturn += STToString(conflict) + ",";		
		}
		
		toReturn = toReturn.substring(0, toReturn.length()-1);
		
		return toReturn;
	}
	
	
	/**
	 * 
	 * A function used to convert ScheddarTime to its string representation for
	 * XML storage purposes
	 * 
	 * @param conflict The ScheddarTime to be converted to String
	 * @return Its String representation for XMl storage purposes
	 */
	public String STToString(ScheddarTime conflict){
		String toReturn = "";
		
		toReturn+= conflict.getStartHour() + "/";
		toReturn+= conflict.getStartMinutes() + "/";
		toReturn+= conflict.getDuration() + "/";
		toReturn+= conflict.getDayOfWeek() + "/";
		toReturn+= conflict.getDay() + "/";
		toReturn+= conflict.getMonth() + "/";
		toReturn+= conflict.getYear() + "/";
		toReturn+= conflict.isRecurring();
		
		return toReturn;
	}
	
	
}


