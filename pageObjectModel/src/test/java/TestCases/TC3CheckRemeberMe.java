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

public class TC3CheckRemeberMe extends BaseTest {


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
	@Test
	public void rememberme(Method mName) throws IOException, InterruptedException {
		driver = getDriver("Chrome");
		lp=new LogInpage(driver);
		driver.get(oDataUtils.ReadWebElementProperties("App.URL"));
		sa.assertEquals(driver.getTitle(), "Login | Salesforce");
		test.info("Application is launched");

		

		if (oCommonUtilities.waitForElementVisible(lp.userName)) {
			oCommonUtilities.enterText(lp.userName, oDataUtils.ReadAccountProperties("prodaccount.name"), "USERNAME");

		}
		//String userNameToverify=lp.userName.getText()
		sa.assertNotNull(lp.userName.getText(), "krishnaa.mar21@xyz.com");
		test.info("Username is Entered");

		

		if (oCommonUtilities.waitForElementVisible(lp.password)) {
			lp.password.clear();
			lp.password.sendKeys(oDataUtils.ReadAccountProperties("prodaccount.password"));
			System.out.println("check pwd:"+ lp.password.getText());
			test.info("Password is entered");
		}
		System.out.println(oDataUtils.ReadAccountProperties("prodaccount.name") + oDataUtils.ReadAccountProperties("prodaccount.password"));
		
		//rememberme checkbox field
	
		
		if (oCommonUtilities.waitForElementVisible(lp.rememberMe)) {
			lp.rememberMe.click();
		}
		test.info("RememberMe Checked");
		//test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
		//sa.assertEquals(lp.password, "one1two2");
		
		
		//if (oCommonUtilities.waitForElementVisible(lp.Login)) {
			lp.Login.click();
		//}
			//Thread.sleep(3000);
		
		oCommonUtilities.logOut(driver);
		
		Thread.sleep(2000);
		
		sa.assertEquals(lp.remembermeUserName.getText(), "krishnaa.mar21@xyz.com");
		
		test.info("Username displayed is correct");
		

		WebElement sLoginButton1 = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.login.xpath")));
		if (oCommonUtilities.waitForElementVisible(lp.Login))
			sLoginButton1.click();
		test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
		test.info("loginclicked");
		//System.out.println("checkpath"+ driver.getCurrentUrl()+"properitie file "+oDataUtils.ReadPageURLproperties("Salesforce.HomePage"));
		sa.assertTrue(driver.getCurrentUrl().contains(oDataUtils.ReadPageURLproperties("Salesforce.HomePage")));
		if (driver.getCurrentUrl().contains(oDataUtils.ReadPageURLproperties("Salesforce.HomePage"))) {
 
			test.pass((mName.getName())+" PASSED");
			test.info("Login successful");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail((mName.getName())+"TC03 FAILED");
		}
		
		sa.assertAll();
	
	}
		
}
