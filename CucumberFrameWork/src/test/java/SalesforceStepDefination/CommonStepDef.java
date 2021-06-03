package SalesforceStepDefination;

import BaseTest.CommonUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CommonStepDef {
	
	CommonUtils commonUtils = CommonUtils.getInstance();

	@Before
	public void setup() {
		System.out.println("Before hyruytc");
		commonUtils.setup();
	}

	@After
	public void cleanup() {
		commonUtils.driver.close();
	}
	
	@Given("As a user, I should be able to launch the application")
	public void as_a_user_i_should_be_able_to_launch_the_application() {
		System.out.println("inside: as_a_user_i_should_be_able_to_launch_the_application");
		commonUtils.as_a_user_i_should_be_able_to_launch_the_application();
	}

	@When("As a user, i should be able to enter user credentials")
	public void as_a_user_i_should_be_able_to_enter_user_credentials() {
		System.out.println("inside: as_a_user_i_should_be_able_to_enter_user_credentials");
	    commonUtils.as_a_user_i_should_be_able_to_enter_user_credentials();
	}
}
