#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
#@tag+

Feature: Reset functionality on login page of Application

Background:
Given As a user, I should be able to launch the application
When As a user, i should be able to enter user credentials

  
Scenario: validate login with error message
    And user enters "krishnaa.mar21@xyz.com" and blank "" and clicks login 
    Then User should be able to see the error message for blank password
 
Scenario: validate login with wrong username 
   And user enters  wrong "krishnaa.mar21@abc.com" and valid "one1two2" and clicks login 
   Then User should be able to see the error message for wrong credentials entered
    
Scenario: validate login with correct details	
   And user enters  valid "krishnaa.mar21@xyz.com" and valid "one1two2" and clicks login 
   Then User should be able to see the homepage  of application
    

    
    