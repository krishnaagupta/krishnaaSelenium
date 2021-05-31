package TestCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseTest;
import Pages.LogInpage;


public class TC4ForgotPasswordAandB extends BaseTest{

	SoftAssert sa = new SoftAssert();
	public LogInpage  lp;
	@BeforeMethod
	public void CreateReport(Method sTestMethod) {
		test = extent.createTest(sTestMethod.getName());
	}

	@AfterMethod
	public void CloseReport() throws InterruptedException {
		Thread.sleep(4000);
		driver.close();
	}
	@Test(priority=1)
	public void forgotPasswordA() throws IOException, InterruptedException {
		driver = getDriver("Chrome");
		lp=new LogInpage(driver);
		driver.get(oDataUtils.ReadWebElementProperties("App.URL"));
		sa.assertEquals(driver.getTitle(), "Login | Salesforce");
		test.info("Application is launched");


		if (oCommonUtilities.waitForElementVisible(lp.userName)) {
			oCommonUtilities.enterText(lp.userName, oDataUtils.ReadAccountProperties("prodaccount.name"), "USERNAME");

		}

		sa.assertNotNull(lp.userName.getText(), "krishnaa.mar21@xyz.com");
		test.info("Username is Entered");
		//forgot password clicked
		if (oCommonUtilities.waitForElementVisible(lp.forgotPassword)) {
			lp.forgotPassword.click();
		}
		//krishnaa.mar21@xyz.com
		test.info("Forgot password clicked");
		if (oCommonUtilities.waitForElementVisible(lp.email)) {
			oCommonUtilities.enterText(lp.email, oDataUtils.ReadAccountProperties("prodaccount.name"), "USERNAME");
		}
		test.info("Username Entered");
		if (oCommonUtilities.waitForElementVisible(lp.continueButton)) {
			lp.continueButton.click();
		}


		if (driver.getTitle().equals("Check Your Email | Salesforce")) {

			test.pass("TestCase forgot PasswordA "+" PASSED"+ "Email Sent");
			String path = oCommonUtilities.takeScreenshot();
			System.out.println("output path of png file="+path);

			test.addScreenCaptureFromPath(path);
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase forgot PasswordAFAILED");
		}
		sa.assertAll();

	}
	@Test(priority=2)
	// test 4 forgot password 4b
	public void forgotPasswordB() throws IOException, InterruptedException {
		driver = getDriver("Chrome");
		lp=new LogInpage(driver);
		driver.get(oDataUtils.ReadWebElementProperties("App.URL"));
		sa.assertEquals(driver.getTitle(), "Login | Salesforce");
		test.info("Application is launched");


		if (oCommonUtilities.waitForElementVisible(lp.userName)) {
			oCommonUtilities.enterText(lp.userName, oDataUtils.ReadAccountProperties("prodaccount.Wrong.name"), "USERNAME");
		}
		test.info("User name Entered");

		if (oCommonUtilities.waitForElementVisible(lp.password)) {
			oCommonUtilities.enterText(lp.password, oDataUtils.ReadAccountProperties("prodaccount.Wrong.password"), "Password");
		}
		test.info("password  Entered");

		if (oCommonUtilities.waitForElementVisible(lp.Login))
			lp.Login.click();
		test.info("Login clicked");
		String errorMessage="";
		if (oCommonUtilities.waitForElementVisible(lp.errorMsg)) {
			errorMessage=lp.errorMsg.getText();
		}
		String errorVerication="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		sa.assertEquals(errorMessage,errorVerication);
		if (errorMessage.equals (errorVerication)) {
			test.pass("TestCase forgot Password4B "+" PASSED"+ "Error message verrified");

		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase forgot Password4B,incorrect error message");
			String path = oCommonUtilities.takeScreenshot();
			test.addScreenCaptureFromPath(path);
		}
	}
}
