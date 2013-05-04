scheddar
========

How Our Program Works
======================

The main method of our project is in ScheddarGui, which opens a blank window with tabs.
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
description, and a list of the groups that the person is in.

When a new person is created and added to groups, the EmailParser method sendAddedPersonEmail() should be called, which
sends an email notifying the person that they have been added to groups, and requests that they send an email containing
recurring conflicts.
