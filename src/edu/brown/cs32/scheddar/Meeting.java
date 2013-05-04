package edu.brown.cs32.scheddar;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Meeting {
	private String name;  // a name for the event
	private boolean decided; // true if the meeting time is finalized
	private ScheddarTime timeForFinalizing; // the time at which the meeting time must be finalized
	
	private ScheddarTime finalTime; // the block of time when the meeting will occur
	private List<ScheddarTime> proposedTimes; // proposed times for the meeting to occur
	private HashMap<Integer,Double> indexToScore; // maps proposed time indices to the weighted score for the meeting
	
	private List<Group> groupsInvolved; // a list of the names of groups involved in this meeting
	private String description; // a description of this event
	
	private UsefulMethods methods = new UsefulMethods();
	
	// The number of days in advance that a meeting will be finalized
	
	private int daysBeforeMeetingFinalized = 3;
	
	/**
	 * Getter Functions
	 */
	
	public String getName(){
		return this.name;
	}
	
	public boolean isDecided(){
		return this.decided;
	}
	
	public ScheddarTime getTimeForFinalizing(){
		return this.getTimeForFinalizing();
	}
	
	public List<ScheddarTime> getProposedTimes(){
		return this.proposedTimes;
	}
	
	public HashMap<Integer,Double> getIndexToScore(){
		return this.indexToScore;
	}
	
	// This will be null if a final time has yet to be decided
	
	public ScheddarTime getFinalTime(){
		return this.finalTime;
	}
	
	public List<Group> getGroupsInvolved(){
		return this.groupsInvolved;
	}
	
	public String getDescription(){
		return this.description;
	}

	
	/**
	 * Setter Functions (to completely reset values)
	 */
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setGroupsInvolved(List<Group> newGroupList){
		this.groupsInvolved = newGroupList;
	}
	
	public void setDescription(String newDescription){
		this.description = newDescription;
	}
	
	public void setProposedTimes(List<ScheddarTime> timeList){
		this.proposedTimes = timeList;
	}
	
	// THIS IS FOR TESTING PURPOSES ONLY. FINALTIME SHOULD
	// NOT BE SET USING THIS METHOD!
	
	public void setFinalTime(ScheddarTime time){
		this.finalTime = time;
	}
	
	/**
	 * More convenient setters
	 */
	
	public void addGroupInvolved(Group group){
		this.groupsInvolved.add(group);
	}
	
	public void removeGroupInvolved(Group group){
		this.groupsInvolved.remove(group);
	}
	
	public void addProposedTime(ScheddarTime time){
		this.proposedTimes.add(time);
	}
	
	public void removeProposedTime(ScheddarTime time){
		this.proposedTimes.remove(time);
	}
	
	/**
	 * Constructor
	 **/
	
	public Meeting(String name, List<ScheddarTime> times,List<Group> groupsInvolved,String description,List<Person> peopleAttending){
		this.name = name;
		this.proposedTimes = times;
		this.groupsInvolved = groupsInvolved;
		this.description = description;
		this.decided = false;
		this.indexToScore = new HashMap<Integer,Double>();
		
		
		// If there is only one proposed time, then the meeting will definitely occur then,
		// so we set decided to true and the final time to that time
		
		if(this.proposedTimes.size()==1){
			this.decided = true;
			this.finalTime = this.proposedTimes.get(0);
		}
		
		// Else we calculate the time by which we must decide on a final meeting time
		
		else{
			ScheddarTime earliestTime = Collections.min(this.proposedTimes);
			int newDay = earliestTime.getDay() - this.daysBeforeMeetingFinalized;
			int newMonth = earliestTime.getMonth();
			int newYear = earliestTime.getYear();
			int newHour = earliestTime.getStartHour();
			int newMinutes = earliestTime.getStartMinutes();
			
			if(newDay <= 0){
				newMonth--;
				if(newMonth==0){ 
					newMonth = 12; // if was january and backed up, go to december
					newYear = newYear--;
				}
				newDay = methods.daysInMonth(newMonth,newYear) + newDay;
			}
			
			ScheddarTime timeToFinalize = new ScheddarTime(newHour,newMinutes,0,0,newDay,newMonth,newYear,false);
			this.timeForFinalizing = timeToFinalize;
		}
	}
	
	
	/**
	 * Returns a Set containing the emails of each person in a Group involved
	 * in this meeting (Set used to prevent emailing a person multiple times
	 * if they belong to more than one of the groups involved)
	 */
	
	public Set<String> getAllEmails(){
		Set<String> allEmails = new TreeSet<String>();
		for(Group g : groupsInvolved){
			for(Person p : g.getMembers()){
				allEmails.add(p.getEmail());
			}
		}
		return allEmails;
	}
	
	/**
	 * Finds the best time to hold the meeting out of the proposed times.
	 * To be called at a given period of time before the first proposed meeting
	 * time as described elsewhere
	 */
	
	public ScheddarTime getBestTime(){
		double bestScore = Collections.max(indexToScore.values());
		for(int i : indexToScore.keySet()){
			if(indexToScore.get(i)==bestScore){
				return proposedTimes.get(i);
			}
		}
		return null; // shouldnt happen
	}
	
	/**
	 * Get a person's importance to a meeting based on their importance in the groups
	 * of the meeting
	 */
	
	public double getPersonImportance(Person p){
		double maxImportance = 1.0;
		for(Group g : this.getGroupsInvolved()){
			if(g.getMemberRanking(p)>maxImportance){
				maxImportance = g.getMemberRanking(p);
			}
		}
		return maxImportance;
	}
	
	/**
	 * Update a score in the Hashmap of proposed time indices to scores
	 */
	
	public void updateScore(int index, double score){
		double prevScore = this.indexToScore.get(index);
		this.indexToScore.put(index,prevScore + score);
	}
}
