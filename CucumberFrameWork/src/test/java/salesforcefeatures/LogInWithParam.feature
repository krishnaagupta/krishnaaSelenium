Feature: welcome

Background:
Given As a user, I should be able to launch the application
When As a user, i should be able to enter user credentials

  
Scenario: 1
    And user put "krishnaa.mar21@xyz.com" and blank "" and clicks login 
    Then User should be able to see the error message for blank password
 
Scenario: 2
   And user put  wrong "krishnaa.mar21@abc.com" and valid "one1two2" and clicks login 
   Then User should be able to see the error message for wrong credentials entered
    
Scenario: 3
    And user put  valid "krishnaa.mar21@xyz.com" and valid "one1two2" and clicks login 
   Then User should be able to see the homepage  of application
    
