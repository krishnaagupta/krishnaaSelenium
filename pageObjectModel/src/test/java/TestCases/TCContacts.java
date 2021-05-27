package TestCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.Contacts;
import Pages.CreateAccount;
import Pages.CreateOpty;
import Pages.LogInpage;
import Pages.UserPage;

public class TCContacts extends Base.BaseTest{

	SoftAssert sa = new SoftAssert();
	public LogInpage  lp;
	public UserPage up;
	public CreateAccount cp;
	public CreateOpty op;
	public Contacts co;
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

		lp=new LogInpage(driver);// Important step initilaize both the lp and up ojects 
		up=new UserPage(driver);// Important step initilaize both the lp and up ojects
		cp=new CreateAccount(driver);// Important step initilaize both the lp and up ojects
		op=new CreateOpty(driver);// Important step initilaize both the op from create op page
		co=new Contacts(driver);
		sa.assertEquals(driver.getTitle(), "Login | Salesforce");

		test.info("Application is launched");

		WebElement sUserName = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.username.xpath")));

		if (oCommonUtilities.waitForElementVisible(sUserName)) {
			oCommonUtilities.enterText(sUserName, oDataUtils.ReadAccountProperties("prodaccount.name"), "USERNAME");

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

		if (oCommonUtilities.waitForElementVisible(sLoginButton)) {
			sLoginButton.click();
		}

		sa.assertTrue(driver.getCurrentUrl().contains(oDataUtils.ReadAccountProperties("Salesforce.HomePage")));
		if (driver.getCurrentUrl().contains(oDataUtils.ReadAccountProperties("Salesforce.HomePage"))) {

			test.pass("login success");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("login fail");
		}
	}
	
	
	//@Parameters({"BrowserName"})
	//TC-25 CreateNewContact
	//@Test	
		public  void  TC25CreateNewContact(Method mName)throws IOException, InterruptedException {	 
			
			String BrowserName="Chrome";
			Login (BrowserName);
			String sentText="";
			if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {
				co.contactsClick.click();
			}
			if (oCommonUtilities.waitForElementVisible(co.newClick)) {
				co.newClick.click();
			}
			if (oCommonUtilities.waitForElementVisible(co.firstName)) {
				co.firstName.clear();
				co.firstName.sendKeys(oDataUtils.ReadPageURLproperties("TC25FirstName.text"));
			}
			if (oCommonUtilities.waitForElementVisible(co.accountName)) {
				co.accountName.clear();
				co.accountName.sendKeys(oDataUtils.ReadPageURLproperties("TC25Account.text"));
			}
			oCommonUtilities.logOut(driver);
		}
		//@Parameters({"BrowserName"})
		//TC-28 TC28CheckMyContactView
			//@Test	
			public  void  TC28CheckMyContactView(Method mName)throws IOException, InterruptedException {	 
				
				String BrowserName="Chrome";
				Login (BrowserName);
				String sentText="";
				if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {
					co.contactsClick.click();
				}
				//Thread.sleep(1000);
				if (oCommonUtilities.waitForElementVisible(co.dropDownContactsnew)) {
					Select select=new Select(co.dropDownContactsnew);
					select.selectByVisibleText("My Contacts");
				}
				
				
				oCommonUtilities.logOut(driver);
			}

			//@Parameters({"BrowserName"})
			//TC-27 TC28CheckMyContactView
				//@Test	
				public  void  TC27RecentlyCreatedContact(Method mName)throws IOException, InterruptedException {	 
					
					String BrowserName="Chrome";
					Login (BrowserName);
					String sentText="";
					if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {
						co.contactsClick.click();
					}
					if (oCommonUtilities.waitForElementVisible(co.recentlyViewed)) {
						Select select=new Select(co.recentlyViewed);
						select.selectByVisibleText("Recently Created");
					}
					
					oCommonUtilities.logOut(driver);
				}


				//@Parameters({"BrowserName"})
				//TC-29 TC29ViewContactInContactPage
					@Test	
					public  void  TC29ViewContactInContactPage(Method mName)throws IOException, InterruptedException {	 
						
						String BrowserName="Chrome";
						Login (BrowserName);
						String sentText="";
						if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {
							co.contactsClick.click();
						}
						if (oCommonUtilities.waitForElementVisible(co.contactName)) {
							co.contactName.click();
						}
Thread.sleep(2000);
						//oCommonUtilities.logOut(driver);
					}
}
