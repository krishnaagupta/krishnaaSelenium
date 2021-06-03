package SalesforceStepDefination;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import BaseTest.CommonUtils;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC3RememberMe {
	
	CommonUtils commonUtils = CommonUtils.getInstance();	
	
	@Given("user enters  valid {string} and valid {string} and checks rememberMe and clicks login")
	public void user_enters_valid_and_valid_and_checks_remember_me_and_clicks_login(String username, String password) {
	    // Write code here that turns the phrase above into concrete actions
		commonUtils.login_with_username_password(username, password);
		checkRememberMe();
		commonUtils.lp.Login.click();
 
	}

	@Then("User logs out")
	public void user_logs_out() {
	    // Write code here that turns the phrase above into concrete actions
		commonUtils.logout();
	}

	@Then("verify username is {string}")
	public void verify_username_is(String username) {
	   Assert.assertEquals((commonUtils.lp.remembermeUserName).getText(), username);
	}
 
	public void checkRememberMe() {
	

		if (commonUtils.waitForElementVisible(commonUtils.lp.rememberMe)) {
			(commonUtils.lp.rememberMe).click();	
		}
	}
}
