package TestCases;



import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.TestListener;

import Base.BaseTest;
import Pages.LogInpage;
//import listeners.TestListeners;


//@Listeners(TestListeners.class)

public class VerifyLoginFunctionality extends BaseTest {


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
	@Parameters({"BrowserName"})
	@Test(priority=1)
	public void VerifyLoginErrorMessage01(Method mName,String BrowserName) throws IOException {

		driver = getDriver(BrowserName);
		lp=new LogInpage(driver);
		driver.get(oDataUtils.ReadWebElementProperties("App.URL"));

		sa.assertEquals(driver.getTitle(), "Login | Salesforce");

		test.info("Application is launched");

		//WebElement sUserName = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.username.xpath")));

		if (oCommonUtilities.waitForElementVisible(lp.userName))
			lp.userName.sendKeys(oDataUtils.ReadAccountProperties("prodaccount.name"));
		System.out.println("username" + lp.userName);
		sa.assertNotNull(lp.userName.getText(), "Username null");

		test.info("Username is Entered");

		//WebElement sPassword = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.password.xpath")));

		if (oCommonUtilities.waitForElementVisible(lp.password)) {
			lp.password.clear();
			test.info("Password is Empty");
		}

		lp.password.sendKeys("");

		sa.assertEquals(lp.password.getText(), "");

		//WebElement sLoginButton = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.login.xpath")));

		if (oCommonUtilities.waitForElementVisible(lp.Login))
			lp.Login.click();

		//WebElement sErrorMsg = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.errormsg.xpath")));

		sa.assertEquals(lp.errorMsg.getText(), oDataUtils.ReadWebElementProperties("login.errormsg"));

		sa.assertAll();
		if (lp.errorMsg.getText().equals(oDataUtils.ReadWebElementProperties("login.errormsg"))) {

			test.pass("TC01 Passed");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TC01 FAILED");
		}

	}

	@Test(priority=2)
	public void VerifyLogin(Method mName,String BrowserName) throws IOException {

		driver = getDriver(BrowserName);
		driver.get(oDataUtils.ReadWebElementProperties("App.URL"));

		sa.assertEquals(driver.getTitle(), "Login | Salesforce");

		test.info("Application is launched");

		WebElement sUserName = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.username.xpath")));

		if (oCommonUtilities.waitForElementVisible(sUserName)) {
			oCommonUtilities.enterText(sUserName, oDataUtils.ReadAccountProperties("prodaccount.name"), "USERNAME");
			//				sUserName.sendKeys(oDataUtils.ReadAccountProperties("prodaccount.name"));
		}
		sa.assertNotNull(sUserName.getText(), "krishnaa.mar21@xyz.com");
		test.info("Username is Entered");

		WebElement sPassword = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.password.xpath")));

		if (oCommonUtilities.waitForElementVisible(sPassword)) {
			sPassword.clear();
			sPassword.sendKeys(oDataUtils.ReadAccountProperties("prodaccount.password"));
			System.out.println("check pwd:"+ sPassword.getText());
			test.info("Password is entered");
		}
		//sPassword.sendKeys(oDataUtils.ReadAccountProperties("prodaccount.Wrong.password"));
		//sa.assertEquals(sPassword.getText(), "one1two2");
		sa.assertEquals(oDataUtils.ReadAccountProperties("prodaccount.password"), "one1two2");
		WebElement sLoginButton = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.login.xpath")));

		if (oCommonUtilities.waitForElementVisible(sLoginButton))
			sLoginButton.click();

		sa.assertTrue(driver.getCurrentUrl().contains(oDataUtils.ReadPageURLproperties("Salesforce.HomePage")));
		if (driver.getCurrentUrl().contains(oDataUtils.ReadPageURLproperties("Salesforce.HomePage"))) {

			test.pass(mName.getName()+" PASSED");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail(mName.getName()+"TC02 FAILED");
		}
		sa.assertAll();

	}

}
