package TestCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.LogInpage;
import Pages.UserPage;
import Base.BaseTest;

public class TcUserDropDown extends BaseTest{

	SoftAssert sa = new SoftAssert();
	public LogInpage  lp;
	public UserPage up;
	@BeforeMethod
	public void CreateReport(Method sTestMethod) {
		test = extent.createTest(sTestMethod.getName());
	}

	@AfterMethod
	public void CloseReport() throws InterruptedException {
		Thread.sleep(4000);
		driver.close();
	}

public void Login(String BrowserName) throws IOException ,InterruptedException{
	driver = getDriver("Chrome");
	driver.get(oDataUtils.ReadWebElementProperties("App.URL"));

	sa.assertEquals(driver.getTitle(), "Login | Salesforce");

	test.info("Application is launched");

	WebElement sUserName = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.username.xpath")));

	if (oCommonUtilities.waitForElementVisible(sUserName)) {
		oCommonUtilities.enterText(sUserName, oDataUtils.ReadAccountProperties("prodaccount.name"), "USERNAME");
//		sUserName.sendKeys(oDataUtils.ReadAccountProperties("prodaccount.name"));
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
	sa.assertEquals(oDataUtils.ReadAccountProperties("prodaccount.password"), "one1two2");
	WebElement sLoginButton = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.login.xpath")));

	if (oCommonUtilities.waitForElementVisible(sLoginButton))
		sLoginButton.click();
}

@Parameters({"BrowserName"})
@Test
public  void  Tc5UserDropdown(String BrowserName)throws IOException, InterruptedException {
	Login (BrowserName);
}
}