This BDD framework contains the solution for both the questions in the Tech Challange. RecurringMeeting.feature file contains the solution for UI Automation. LetsHoliday.feature file contains the solution for API Automation. Both the feature files are Data Table driven and inputs can be modified in the feature files. This framework can be integrated with Jenkins.

Major components of this framework are :
 - Maven
 - Appium
 - JUnit
 - Cucumber
 - Pojo Clases for API
 - Page Object for UI 
 - Html Reporting by damianszczepanik

Please install below softwares before running the project:
 - Download Java and set Java_Home in environmental variables
 - Node.js from Download Node.js                                                                                                                        
 - https://nodejs.org/en/download/
 - Set Node_Home Environmental variables path
 - Set npm Environmental variables path
 - Download Appium Server from Node (npm install -g appium)
 - Install Eclipse and import this project
 
To execute this project please navigate to the project location in command prompt and run, "mvn test verify" command. 
Report will be generated @ "{ProjectLocation}\target\cucumber-html-reports\overview-features.html". 
In case of failure screenshot will be generated @ "{ProjectLocation}\screenshot.png"
 

