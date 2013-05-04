package edu.brown.cs32.scheddar;


public class Main {

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
		
		String dest = "/Users/Prateek/Desktop/" + "groups.xml"; //Can specify any location of an XML file
		Scheddar s1 = new Scheddar(dest); //This creates a Scheddar object from all that is in the XML
		
		//Application use
		
		s1.saveData();// This finally saves all the data for the Scheddar object in the XML
	}
	
}
