package TestCases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.CreateAccount;
import Pages.CreateOpty;
import Pages.Leads;
import Pages.LogInpage;
import Pages.UserPage;


public class TCLeads extends Base.BaseTest{

	
	SoftAssert sa = new SoftAssert();
	public LogInpage  lp;
	public UserPage up;
	public CreateAccount cp;
	public CreateOpty op;
	public Leads le;
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
		le=new Leads(driver);
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
	//tc-20	leads
	//@Parameters({"BrowserName"})
	//@Test	
		public  void  TC20CheckLeads(Method mName)throws IOException, InterruptedException {	 
			
			String BrowserName="Chrome";
			Login (BrowserName);
			String sentText="";
		
			Thread.sleep (1000);
			if (oCommonUtilities.waitForElementVisible(le.leadsClick)) {
				le.leadsClick.click();
			}
			oCommonUtilities.logOut(driver);
		}
	
	//tc-21	leads
		//@Parameters({"BrowserName"})
	//	@Test	
			public  void  TC21SelectView(Method mName)throws IOException, InterruptedException {	 
				
				String BrowserName="Chrome";
				Login (BrowserName);
				String sentText="";
			
				Thread.sleep (1000);
				if (oCommonUtilities.waitForElementVisible(le.leadsClick)) {
					le.leadsClick.click();
				}
				
				//WebElement leadsDropDown= driver1.findElement(By.xpath("//*[@id=\"fcf\"]"));
				Select select=new Select(le.leadsDropDown);
				 List <WebElement> op = select.getOptions();
			      int size = op.size();
			      for(int i =0; i<size ; i++){
			         String options = op.get(i).getText();
			         System.out.println(options);
			      }
				oCommonUtilities.logOut(driver);
			}

			//tc-22	leads
			//@Parameters({"BrowserName"})
			//@Test	
		public  void  TC22DefaultView(Method mName)throws IOException, InterruptedException {	 
			
			String BrowserName="Chrome";
			Login (BrowserName);
			String sentText="";
			

			Thread.sleep (1000);
			if (oCommonUtilities.waitForElementVisible(le.leadsClick)) {
				le.leadsClick.click();
			}
			
			Select select=new Select(le.leadsDropDown);
			select.selectByVisibleText("Today's Leads"); 
			
			oCommonUtilities.logOut(driver);
			
			String BrowserName1="Chrome";
			Login (BrowserName1);
			if (oCommonUtilities.waitForElementVisible(le.leadsClick)) {
				le.leadsClick.click();
			}
			if (oCommonUtilities.waitForElementVisible(le.goClick)) {
				le.goClick.click();
			}
			oCommonUtilities.logOut(driver);
		}
			
			
		//tc-23	leads
				//@Parameters({"BrowserName"})
			//	@Test	
			public  void  TC22TodayLeads(Method mName)throws IOException, InterruptedException {	 
				
				String BrowserName="Chrome";
				Login (BrowserName);
				String sentText="";
				

				Thread.sleep (1000);
				if (oCommonUtilities.waitForElementVisible(le.leadsClick)) {
					le.leadsClick.click();
				}
				
				
				Select select=new Select(le.leadsDropDown);
				select.selectByVisibleText("Today's Leads"); 
			
				
				if (oCommonUtilities.waitForElementVisible(le.goClick)) {
					le.goClick.click();
				}
				oCommonUtilities.logOut(driver);
				}
	
			//tc-24	leads
			//@Parameters({"BrowserName"})
		@Test	
		public  void  TC24NewLeads(Method mName)throws IOException, InterruptedException {	 
			
			String BrowserName="Chrome";
			Login (BrowserName);
			String sentText="";
			if (oCommonUtilities.waitForElementVisible(le.leadsClick)) {
				le.leadsClick.click();
			}
			
			if (oCommonUtilities.waitForElementVisible(le.newBtn)) {
				le.newBtn.click();
			}
			if (oCommonUtilities.waitForElementVisible(le.lastName)) {
				le.lastName.clear();
				le.lastName.sendKeys(oDataUtils.ReadPageURLproperties("LastName1.Text"));
			}
			if (oCommonUtilities.waitForElementVisible(le.company)) {
				le.company.clear();
				le.company.sendKeys(oDataUtils.ReadPageURLproperties("company1.Text"));
			}
			if (oCommonUtilities.waitForElementVisible(le.save1)) {
				le.save1.click();
			}
			
			oCommonUtilities.logOut(driver);
		}

}
	 


