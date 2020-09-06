Feature: Creating Recurring Meeting

@UIMobileTest
Scenario Outline: Create a new recurring(3 days a week) meeting, and make sure it doesn't repeat on successive days 
	Given I have launched the Calendar App
	When It is not a <nonWorkingDay>
	And Meeting is not repeated on successive days
	Then I want to book a meeting with the title <title>
	And Set Meeting duration as <hrsMinutes> in the evening
	And I invite <invitee> of people
	And I save the meeting
	Then I Check if the meeting is created as expected
	
	Examples: 
		|title                   |nonWorkingDay	 |hrsMinutes |invitee 				    |
		|Recurring Team Catch Up |SUNDAY	  	 |0:20		 |kg@gmail.com,tg@gmail.com |
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	