Feature: Validating Weather API 

@APITest 
Scenario Outline: A happy holidaymaker 
	Given I like to holiday in "<city>" and I only like to holiday on "<day>" 
	When I look up the the weather forecast for the next "<numberof>" days 
	Then The response is valid "<responseType>" 
	And The response contains "<city>" as the destination. 
	And I can see the temperature is between "<minTemp>" to "<maxTemp>" degrees in "<city>" 
	And Check If it has rained previous days 
	And I can see that it won't be snowing for the next "<numberof>" days 
	
	Examples: 
		|city 	 |day 	    |numberof |minTemp	|maxTemp	|responseType					|
		|Sydney  |THURSDAY  |14		  |5		|35			|application/json; charset=utf-8|
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	