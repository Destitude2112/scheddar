scheddar
========

End-User Documentation
=============================

Start - up
===========

On start-up, a box will appear that allows you to either start a new project or load in an existing one.
If you choose to make a new project, a form will appear to take in relevant information.

Month and Week Views
=======================

You will then be taken to the Month View of our application. At the top of the screen, there are buttons
that allow you to navigate to different months, and a button that takes you to the week view. You can also
enter the week view for a particular day by clicking on it in the month view. Clicking on a day in the week
view takes you to the Meeting view for that day, meaning that time slots you choose for that meeting on that
day will show up on the left side of the screen. All views have a group tree on the left side which displays
all of the people and groups in this schedule.


The Tabs and Their Features
===============================
- File
  -New : Start a new Scheddar
  -Open : Open an existing Scheddar
  -Save : Save the current Scheddar as an XML file
  -Exit : Close the application
  -Options : Open the Options pane
- Create
  - New person : Add a person to the schedule
  - New group : Add a new group to the schedule
  - New meeting : Add a new meeting to the schedule
- Edit
  - Edit group : edit an existing group
  - Delete group : delete an existing group
  - Edit person : edit an existing person
  - Delete person : delete an exisiting person
- View
  - Week View : go to week view
  - Month View : go to month view
  - Meeting : go to meeting view

Creating A Person
==============================
Creating a person is done by using Create -> New Person. The user must fill in the first and last names,
email, phone number, and description of the person, and can select groups to add the person to. People added
to a subgroup are also added to the parent groups, but in the group tree they will appear only in the lowest
subgroup. People that are created can be editted using Edit -> Edit Person, and deleted by using Edit -> Delete Person.

Creating A Group
==============================
Creating a Group is done by using Create -> New Group. The user must fill in the group's name and its parent. The group
will then appear in the group tree. A group can be editted by using Edit -> Edit Group and deleting by using Edit -> Delete group.
In editting a group, a user can set custom importance rankings for the members of the group.

Creating a Meeting
==============================
Creating a Meeting is done by using Create -> New Meeting. The user must first name the meeting and give it a short description.
Then, the user must add people to the meeting. People can either be added as entire groups, or as individuals. For individuals,
importance rankings can be given. The user then must set the duration of the meeting, and select a potential time range for the
meeting by setting the day and the end time. By pressing 'Calculate Meeting Times', you get a list of potential meeting times,
as well as their rankings based on recurring conflicts of people that would be part of the meeting. Times can then be proposed by
clicking 'Select Time Slot'. Time ranges on different days can then be selected by changing the fields and hitting 'Calculate meeting
times' again. Once some proposed times have been chosen, the user can hit 'Request Availabilities' to send out an automated email to
everyone involved in the meeting that asks them about any conflicts they may have The best meeting will be determined at a specific
amount of time before the first proposed meeting, and a final meeting time will be set and sent to all relevant people. The admin can
also simply decide right away on a meeting time using the 'Schedule Now' button.


How Our Program Works
======================

The main method of our project is in ScheddarGui, which opens a simple pop-up window.
From here, the user can either load in an old scheduling project (saved as an XML file) or
start a new one. If a new one is started, a name for the main group (generally the business name)
must be given. In both cases, the program takes in the admin name, the admin's email address, and
the admin's email password.

If a new project is created, then nothing special needs to happen on startup and we go to the current
month view, where no meetings will be scheduled. 

If an old project is loaded, however, we use the XML parsing capabilities in ScheddarXML to repopulate 
the Scheddar data structures. Also, when a file is loaded, we check all unread emails in the admin's 
email account, parse them, and apply the  appropriate actions. To do this, we instantiate the Scheddar's 
EmailParser using the username and password, and then call readAndParseEmails(), which updates data structures
with information received from emails since the last run of the program. 
After that, the program must check all Meetings to see if it is time to algorithmically determine the best time 
for their occurrence. To do this, methods.getCurrentTime() is compared to the timeForFinalizing for each Meeting 
in the Scheddar's HashMap if the meeting's time is not decided (indicated by the Meeting's isDecided field). If
the current time is after the time for finalization, we call the Meeting's getBestTime() method, which decides
on a meeting time. Also, the EmailParser method sendFinalizedMeetingEmail should be used to notify all relevant
people that the meeting time has been finalized. Finally, we check if the end time of a Meeting (using the SceddarTime 
method getEndTime()) is before the current time, in which case the meeting has already occurred and we can remove it from 
the hashmap.

At this point, the data structures in the Scheddar are updated to the most current values, so the GUI can use
them. Using the methods in Scheddar dayMeetings() and monthMeetings(), the frontend can access all of the Meetings
scheduled for those given time periods. Meetings that have a decided time (again, indicated by the decided field)
should have a time block corresponding to its finalTime colored in blue on the GUI. Those that do not should
have each of their proposed times in blocks colored in red on the GUI.

From the GUI, a user can add new people and groups. When a new group is added, the Group hashmap must be updated, as
well as any Groups that the new Group refers to (i.e. a group that is a child of an existing group should update
the child values of that group).

When adding a new person, the admin must fill out a form with fields for the person's name, email, phone number, a short
description, and a list of the groups that the person is in, with the importance of that person in each of the groups.

When a new person is created and added to groups, the EmailParser method sendAddedPersonEmail() should be called, which
sends an email notifying the person that they have been added to groups, and requests that they send an email containing
recurring conflicts.

When a new meeting is created, the admin must enter the name of the meeting, a list of the groups involved, a decription of
the meeting, and a list of proposed date/times for the meeting to occur at. This should instantiate a new Meeting object. However,
before this Meeting is added to the main HashMap of meetings, the program runs getRecurringRankings on it to get estimates
of attendance for each proposed time, and displays them to the admin. The admin can then choose to submit the Meeting, or
change the times if he/she is unhappy with the predicted weighted attendance of the meeting.
