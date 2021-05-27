package TestCases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.CreateAccount;
import Pages.CreateOpty;
import Pages.LogInpage;
import Pages.UserPage;
import Utilities.CommonUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;





public class TCCreateOppoturnity  extends Base.BaseTest{

	public TCCreateOppoturnity() {
		// TODO Auto-generated constructor stub
	}
	SoftAssert sa = new SoftAssert();
	public LogInpage  lp;
	public UserPage up;
	public CreateAccount cp;
	public CreateOpty op;
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
//TC-15OppoturnityCheck
//@Test	
	public  void  TC15SelectOppoturnity(Method mName)throws IOException, InterruptedException {	 
		
		String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
	
		Thread.sleep (1000);
		if (oCommonUtilities.waitForElementVisible(op.OpportunityTab)) {
			op.OpportunityTab.click();
		}
		test.info("Oppoturnity clicked");
		sa.assertEquals(driver.getCurrentUrl(),(oDataUtils.ReadPageURLproperties("checkurloppoturnityTC15.Text")));
		System.out.println(driver.getCurrentUrl());
		if (driver.getCurrentUrl().equals(oDataUtils.ReadPageURLproperties("checkurloppoturnityTC15.Text"))) {

			test.pass("Oppotunity page visible");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("Oppotunity page not visible");
		}
		Select select= new Select(op.oppoturnityDropDown);
		String oppoturnityValue=op.oppoturnityDropDown.getText();
		select.selectByIndex(0);
	
		boolean flag=false;
		String[] expected = {
				"All Opportunities",
				"Closing Next Month",
				"Closing This Month",
				"My Opportunities",
				"New Last Week",
				"New This Week",
				"Opportunity Pipeline",
				"Private",
				"Recently Viewed Opportunities",
				"Won"};
		

		List<WebElement> list =select.getOptions();
		
		//System.out.println("list.size="+list.size());
		for (int i=0;i<list.size();i++) {
			System.out.println("dddddlist=("+ list.get(i).getText()+")");
			if (!expected[i].equals(list.get(i).getText())) {
				System.out.println("ex"+expected[i]+",found"+list.get(i).getText());
				flag=true;
			}
	
		}
	
		 if(flag==false) {
			 test.pass("Oppotunity obtions  visible test case 15 passes");
			 test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
		 }
		 else {
			 test.fail("Oppotunity options not visible test case 15 fail ");
			 test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("Oppotunity options not visible test case 15 fail ");
		
		 }
	//sa.assertAll();
	oCommonUtilities.logOut(driver);
	sa.assertAll();	
	}


//@Parameters({"BrowserName"})
//TC-16OppoturnityCheck
//@Test	
	public  void  TC16CreateNewOpty(Method mName)throws IOException, InterruptedException {	 
		String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
	
		Thread.sleep (1000);
		if (oCommonUtilities.waitForElementVisible(op.OpportunityTab)) {
			op.OpportunityTab.click();
		}
		test.info("Oppoturnity clicked");
		sa.assertEquals(driver.getCurrentUrl(),(oDataUtils.ReadPageURLproperties("checkurloppoturnityTC15.Text")));
		System.out.println(driver.getCurrentUrl());
		if (driver.getCurrentUrl().equals(oDataUtils.ReadPageURLproperties("checkurloppoturnityTC15.Text"))) {

			test.pass("Oppotunity page visible");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("Oppotunity page not visible");
		}
		
		
		//WebElement oppoturnityDropDown= driver1.findElement(By.xpath("//*[@id=\"fcf\"]"));
		Select select= new Select(op.oppoturnityDropDown);
		String oppoturnityValue=op.oppoturnityDropDown.getText();
		select.selectByIndex(0);	
		
		Thread.sleep(3000);
		if (oCommonUtilities.waitForElementVisible(op.oppoturnityNewClick)) {
			op.oppoturnityNewClick.click();
		}

		if (oCommonUtilities.waitForElementVisible(op.oppoturnityName)) {
			op.oppoturnityName.clear();
			op.oppoturnityName.sendKeys(oDataUtils.ReadPageURLproperties("oppoturnityName16.Text"));
		}
		if (oCommonUtilities.waitForElementVisible(op.accountName)) {
			op.accountName.click();
		}

		
		String parentWindowHandle = driver.getWindowHandle();
		String popupWindowHandler = null;
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
			popupWindowHandler = iterator.next();
		}
		driver.switchTo().window(popupWindowHandler);

		driver.switchTo().frame(op.framePath);
		boolean found=false;
		
		
		String mainXPath="//table[@class=\"list\"]";
		//WebElement table= driver.findElement(By.xpath(mainXPath));
		
		List<WebElement>rows=op.table.findElements(By.tagName("tr"));
		String mySelection="hello123";
		for (int i=2; i<=rows.size(); i++) {
			
			WebElement header=rows.get(i).findElement(By.tagName("th"));
			
				String text=header.getText();
				//Sys	tem.out.println(text);
				if(text.equals(mySelection) && !found) {
					found=true;
//					header.click();
					//get the xpath of the matched 
					WebElement elementToClick=driver.findElement(By.xpath(mainXPath+"/tbody/tr["+i+"]/th/a"));
					elementToClick.click();
					//System.out.println("break noe");
					break;	
				}	
		}
		
		driver.switchTo().window(parentWindowHandle);

		
		
	
		Select select1= new Select(op.stageDropdown);
		String stageDrotxtpdown="Needs Analysis";
		select1.selectByValue(stageDrotxtpdown);
		
	//lead source
	
		//WebElement leadSource= driver1.findElement(By.xpath("//*[@id=\"opp6\"]"));
		Select select2= new Select(op.leadSource);
		String leadSourceTxt="Web";
		select2.selectByValue(leadSourceTxt);
		
		//closing date
		
		String date1="05/10/2021";
		//WebElement closingDate= driver1.findElement(By.xpath("//*[@id=\"opp9\"]"));
		CommonUtilities.selectdateByJs(driver, op.closingDate, date1);

		if (oCommonUtilities.waitForElementVisible(op.saveButton)) {
			op.saveButton.click();
		}
}

//@Parameters({"BrowserName"})
//TC-18OppoturnityReport
//@Test	
	public  void  TC17TestOpportunityPipeline(Method mName)throws IOException, InterruptedException {	 
		String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
	
		Thread.sleep (1000);
		if (oCommonUtilities.waitForElementVisible(op.OpportunityTab)) {
			op.OpportunityTab.click();
		}
		test.info("Oppoturnity clicked");
		sa.assertEquals(driver.getCurrentUrl(),(oDataUtils.ReadPageURLproperties("checkurloppoturnityTC15.Text")));
		System.out.println(driver.getCurrentUrl());
		if (driver.getCurrentUrl().equals(oDataUtils.ReadPageURLproperties("checkurloppoturnityTC15.Text"))) {

			test.pass("Oppotunity page visible");
		} 
		else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("Oppotunity page not visible");
		}
		if (oCommonUtilities.waitForElementVisible(op.oppoturnityPipeline)) {
			op.oppoturnityPipeline.click();
		}
	}
//@Parameters({"BrowserName"})
//TC-18Oppoturnitystuck
	//@Test	
	public void TC18StuckOpportunities (Method mName)throws IOException, InterruptedException {	 
		String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
	
		Thread.sleep (1000);
		if (oCommonUtilities.waitForElementVisible(op.OpportunityTab)) {
			op.OpportunityTab.click();
		}
		test.info("Oppoturnity clicked");
		sa.assertEquals(driver.getCurrentUrl(),(oDataUtils.ReadPageURLproperties("checkurloppoturnityTC15.Text")));
		System.out.println(driver.getCurrentUrl());
		if (driver.getCurrentUrl().equals(oDataUtils.ReadPageURLproperties("checkurloppoturnityTC15.Text"))) {

			test.pass("Oppotunity page visible");
		} 
		else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("Oppotunity page not visible");
		}	
		if (oCommonUtilities.waitForElementVisible(op.oppoturnitystuck)) {
			op.oppoturnitystuck.click();
		}
	}

		//@Parameters({"BrowserName"})
		//TC-19Oppoturnitystuck
		@Test	
		public void TC19QuarterlySummaryReport(Method mName)throws IOException, InterruptedException {	 
			String BrowserName="Chrome";
			Login (BrowserName);
			String sentText="";
		
			Thread.sleep (1000);
			if (oCommonUtilities.waitForElementVisible(op.OpportunityTab)) {
				op.OpportunityTab.click();
			}
			test.info("Oppoturnity clicked");
			sa.assertEquals(driver.getCurrentUrl(),(oDataUtils.ReadPageURLproperties("checkurloppoturnityTC15.Text")));
			//System.out.println(driver.getCurrentUrl());
			if (driver.getCurrentUrl().equals(oDataUtils.ReadPageURLproperties("checkurloppoturnityTC15.Text"))) {

				test.pass("Oppotunity page visible");
			} 
			else {
				test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
				Assert.fail("Oppotunity page not visible");
			}	
			if (oCommonUtilities.waitForElementVisible(op.oppoturnitystuck)) {
				op.oppoturnitystuck.click();
			}
			
			
			Select select=new Select(op.quaterlySummeryInterval);
			String text="Next FQ";
			select.selectByVisibleText(text);
			String test1=select.getFirstSelectedOption().getText();
			System.out.println("sd1--"+test1);
			Thread.sleep(5000);
			Select select1=new Select(op.quaterlySummeryInclude);
			
			String test2=select1.getFirstSelectedOption().getText();
			System.out.println("sd--"+test2);
			String text1="Open Opporturnities";
			select1.selectByVisibleText(test2);
			
		
			if (oCommonUtilities.waitForElementVisible(op.runOn)) {
				op.runOn.click();
			}
			
			
		}	
}