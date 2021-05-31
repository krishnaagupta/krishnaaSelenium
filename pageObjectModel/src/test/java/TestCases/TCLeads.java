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
import org.testng.annotations.Parameters;
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
	@Parameters({"BrowserName"})
	@Test	
	public  void  TC20CheckLeads(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";

		Thread.sleep (1000);
		if (oCommonUtilities.waitForElementVisible(le.leadsClick)) {
			Assert.assertEquals(true, le.leadsClick.isDisplayed());

			le.leadsClick.click();
			test.info("leadsClicked");
		}
		Assert.assertEquals(driver.getCurrentUrl(), oDataUtils.ReadPageURLproperties("tc21url.text"));

		if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("tc21url.text"))) {
			test.info("Leads Page Displayed, test case 21 passed");
		}
		else {
			test.info("Leads Page  not Displayed, test case 21 failed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase Failed");
		}
		oCommonUtilities.logOut(driver);
	}

	//tc-21	leads
	@Parameters({"BrowserName"})
	@Test	
	public  void  TC21SelectView(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";

		Thread.sleep (1000);
		if (oCommonUtilities.waitForElementVisible(le.leadsClick)) {
			le.leadsClick.click();
		}
		Assert.assertEquals(driver.getCurrentUrl(), oDataUtils.ReadPageURLproperties("tc21url.text"));

		if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("tc21url.text"))) {
			test.info("Leads Page Displayed ");
		}
		else {
			test.info("Leads Page  not Displayed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase Failed");
		}
		//WebElement leadsDropDown= driver1.findElement(By.xpath("//*[@id=\"fcf\"]"));
		Select select=new Select(le.leadsDropDown);
		List <WebElement> op = select.getOptions();
		int size = op.size();
		for(int i =0; i<size ; i++){
			String options = op.get(i).getText();
			System.out.println(options);
		}
		String Arr[]= {"All Open Leads",
				"My Unread Leads",
				"Recently Viewed Leads",
				"Today's Leads","View - Custom 1","View - Custom 2" };

		boolean flag =true;
		for(int j=0;j<size;j++) {
			Assert.assertEquals(Arr[j],(op.get(j).getText()));
			if (Arr[j].equals(op.get(j).getText())) {
				flag=true;

				System.out.println("matchging--"+op.get(j).getText());
			}else
				flag=false;

		}

		if (flag==true) {
			test.info("Drop Down Elements verifies ,test case 21 passed");
		}
		else {

			test.info("Drop Down Elements checked,found mismatch,test case 21 failed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase Failed");

		}
		System.out.println("flah--"+flag);
		oCommonUtilities.logOut(driver);
	}

	private void TC22Helper() throws InterruptedException, IOException {
		String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";

		Thread.sleep (1000);
		if (oCommonUtilities.waitForElementVisible(le.leadsClick)) {
			le.leadsClick.click();

		}
		Assert.assertEquals(driver.getCurrentUrl(), oDataUtils.ReadPageURLproperties("tc21url.text"));

		if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("tc21url.text"))) {
			test.info("Leads Page Displayed ");
		}
		else {
			test.info("Leads Page  not Displayed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase Failed");
		}

	}

	//tc-22	leads
	@Parameters({"BrowserName"})
	@Test	
	public  void  TC22DefaultView(Method mName,String BrowserName)throws IOException, InterruptedException {	 


		TC22Helper();

		Select select=new Select(le.leadsDropDown);
		select.selectByVisibleText("Today's Leads"); 


		oCommonUtilities.logOut(driver);


		TC22Helper();
		Select select1=new Select(le.leadsDropDown);
		select1.selectByVisibleText("Today's Leads"); 

		String myselection= select1.getFirstSelectedOption().getText();
		Assert.assertEquals(myselection,("Today's Leads"));
		if (myselection.equals(("Today's Leads"))) {
			test.info("today`s leads displayed");
		}
		else {

			test.info("today`s leads not displayed and testcase22 failed");
		}

		if (oCommonUtilities.waitForElementVisible(le.goClick)) {
			Assert.assertEquals(true, le.leadsClick.isDisplayed());
			le.goClick.click();
			test.info("Go CLicked");
		}
		System.out.println("asjaasd"+driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), oDataUtils.ReadPageURLproperties("tc22gourl.text"));
		if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("tc22gourl.text"))) {
			test.info(" Today`s leads displayed ,testcase22 passed");
		}
		else {
			test.info("Today`s leads  not displayed ,testcase22 passed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("Today`s leads  not displayed ,testcase22 passed");
		}


		oCommonUtilities.logOut(driver);
	}


	//tc-23	leads
	@Parameters({"BrowserName"})
	@Test	
	public  void  TC23TodayLeads(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
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
		System.out.println("asjaasd"+driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), oDataUtils.ReadPageURLproperties("tc22gourl.text"));
		if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("tc22gourl.text"))) {
			test.info(" Today`s leads displayed ,testcase23 passed");
		}
		else {
			test.info("Today`s leads  not displayed ,testcase23 passed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("Today`s leads  not displayed ,testcase23 passed");
		}

		oCommonUtilities.logOut(driver);
	}

	//tc-24	leads
	@Parameters({"BrowserName"})
	@Test	
	public  void  TC24NewLeads(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
		if (oCommonUtilities.waitForElementVisible(le.leadsClick)) {
			le.leadsClick.click();
			test.info(" leads cliched");
		}

		if (oCommonUtilities.waitForElementVisible(le.newBtn)) {
			Assert.assertEquals(true, le.leadsClick.isDisplayed());
			le.newBtn.click();
			test.info(" newbtn cliched");
		}
		if (oCommonUtilities.waitForElementVisible(le.lastName)) {

			le.lastName.clear();
			le.lastName.sendKeys(oDataUtils.ReadPageURLproperties("LastName1.Text"));
			//Assert.assertEquals((oDataUtils.ReadPageURLproperties("LastName1.Text")), le.lastName.getText());
			test.info("lastanme entered");
		}
		if (oCommonUtilities.waitForElementVisible(le.company)) {
			le.company.clear();
			le.company.sendKeys(oDataUtils.ReadPageURLproperties("company1.Text"));
			test.info("company entered");
		}
		if (oCommonUtilities.waitForElementVisible(le.save1)) {
			Assert.assertEquals(true, le.save1.isDisplayed());
			le.save1.click();
			test.info("save clicked");
		}
		/*	System.out.println((driver.getCurrentUrl()));
			Assert.assertEquals(driver.getCurrentUrl(), oDataUtils.ReadPageURLproperties("tc24url.text"));
			if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("tc24url.text"))) {
				test.info(" Today`s leads displayed ,testcase24 passed");
			}
				else {
					test.info("Today`s leads  not displayed ,testcase24 passed");
					test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
					Assert.fail("Today`s leads  not displayed ,testcase24 passed");
			}*/

		oCommonUtilities.logOut(driver);
	}

}



