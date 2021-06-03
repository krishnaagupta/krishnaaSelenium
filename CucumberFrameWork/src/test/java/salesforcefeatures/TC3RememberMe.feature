
Feature: TestCase 3 Check Remember me  
  Checking testcase no 3

Background:
Given As a user, I should be able to launch the application
When As a user, i should be able to enter user credentials
  
  Scenario: login and remember me
	  And user enters  valid "krishnaa.mar21@xyz.com" and valid "one1two2" and checks rememberMe and clicks login 
	 	Then User should be able to see the homepage  of application
	 	Then User logs out
	 	Then As a user, I should be able to launch the application
	 	Then verify username is "krishnaa.mar21@xyz.com"
