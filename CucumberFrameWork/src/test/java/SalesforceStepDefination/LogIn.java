package SalesforceStepDefination;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import BaseTest.CommonUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LogIn  {

	CommonUtils commonUtils = CommonUtils.getInstance();
	
	@When("user enters  valid {string} and valid {string} and clicks login")
	public void user_enters_valid_username_and_valid_password_and_clicks_login(String username,String password) {
		commonUtils.user_enters_valid_username_and_valid_password_and_clicks_login(username, password);
	}

	@Then("User should be able to see the homepage  of application")
	public void user_should_be_able_to_see_the_homepage_of_application() {
		System.out.println("correct details");
	}
	
	@When("user enters {string} and blank {string} and clicks login")
	public void user_enters_username_and_blank_password_and_clicks_login(String username,String password) {
		System.out.println("inside: user_enters_username_and_blank_password_and_clicks_login");

		commonUtils.login_with_username_password(username, password);
		commonUtils.lp.Login.click();

	}

	@Then("User should be able to see the error message for blank password")
	public void user_should_be_able_to_see_the_error_message_for_blank_password() {
		Assert.assertEquals(commonUtils.lp.errorMsg.getText(), "Please enter your password.");
	}

	@When("user enters  wrong {string} and valid {string} and clicks login")
	public void user_enters_wrong_username_and_valid_password_and_clicks_login(String username,String password) {
		commonUtils.login_with_username_password(username, password);
		
	}

	@Then("User should be able to see the error message for wrong credentials entered")
	public void user_should_be_able_to_see_the_error_message_for_wrong_credentials_entered() {
		System.out.println("Wrong details");
	}	
}
