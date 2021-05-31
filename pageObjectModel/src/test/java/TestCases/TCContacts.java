package TestCases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.Contacts;
import Pages.CreateAccount;
import Pages.CreateOpty;
import Pages.LogInpage;
import Pages.UserPage;
import Utilities.CommonUtilities;

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


	@Parameters({"BrowserName"})
	//TC-25 CreateNewContact
	@Test	
	public  void  TC25CreateNewContact(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
		if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {

			Assert.assertEquals(true, co.contactsClick.isDisplayed());
			co.contactsClick.click();

			test.info("Contacts Clicked");



		}
		if (oCommonUtilities.waitForElementVisible(co.newClick)) {
			Assert.assertEquals(true, co.newClick.isDisplayed());
			co.newClick.click();

			test.info("New is Clicked");


		}
		String text12="";
		if (oCommonUtilities.waitForElementVisible(co.firstName)) {
			co.firstName.clear();
			co.firstName.sendKeys(oDataUtils.ReadPageURLproperties("TC25FirstName.text"));
			text12=co.firstName.getText();
			Assert.assertNotNull(test);

			if (text12!="") {
				test.info("FirstName entered");
			}
			else {
				test.fail("FirstName not entered");
			}


		}
		//if (oCommonUtilities.waitForElementVisible(co.accountDropDown)) {

		WebElement ele1=driver.findElement(By.xpath("//*[@id=\"con4_lkwgt\"]/img"));
		ele1.click();


		//Thread.sleep(1000);
		String parentWindowHandle = driver.getWindowHandle();
		String popupWindowHandler = null;
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
			popupWindowHandler = iterator.next();
		}

		driver.switchTo().window(popupWindowHandler);
		WebElement framepath=driver.findElement(By.xpath("//*[@id=\"resultsFrame\"]"));
		driver.switchTo().frame(framepath);
		boolean found=false;
		String mainXPath="//table[@class=\"list\"]";

		List<WebElement>rows=op.table.findElements(By.tagName("tr"));
		String mySelection="hello123";
		for (int i=2; i<=rows.size(); i++) {
			WebElement header=rows.get(i).findElement(By.tagName("th"));
			String text=header.getText();	
			if(text.equals(mySelection) && !found) {
				found=true;
				WebElement elementToClick=driver.findElement(By.xpath(mainXPath+"/tbody/tr["+i+"]/th/a"));
				elementToClick.click();
				break;	
			}	
		}
		driver.switchTo().window(parentWindowHandle);
		if (oCommonUtilities.waitForElementVisible(co.Save)) {
			Assert.assertEquals(true, co.Save.isDisplayed());
			co.Save.click();
			test.info("save is Clicked");
		}
		WebElement name=driver.findElement(By.xpath("//*[@id=\"contactHeaderRow\"]/div[2]/h2"));
		String nametext=(name.getText());
		String lastname =oDataUtils.ReadPageURLproperties("TC25FirstName.text");
		System.out.println(nametext+"---"+lastname);
		Assert.assertEquals(nametext, lastname);
		if (nametext.equals(lastname)) {
			test.info("new comtact saved, test case 25 passed");
		}

		else {
			test.fail("new comtact not saved,test case failed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
		}

		oCommonUtilities.logOut(driver);
	}



	@Parameters({"BrowserName"})
	//TC-28 TC28CheckMyContactView
	@Test	
	public  void  TC28CheckMyContactView(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
		if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {
			Assert.assertEquals(true, co.contactsClick.isDisplayed());
			co.contactsClick.click();
			test.info("ContactClicked");
		}
		//Thread.sleep(1000);
		if (oCommonUtilities.waitForElementVisible(co.dropDownContactsnew)) {
			Select select=new Select(co.dropDownContactsnew);
			select.selectByVisibleText("My Contacts");
		}
		System.out.println("pppp"+driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), oDataUtils.ReadPageURLproperties("mycontacturl"));

		if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("mycontacturl"))) {
			test.info("My contact View Displayed, test case 28 passed");
		}
		else {
			test.info("My contact View  not Displayed, test case 28 failed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase Failed");
		}
		oCommonUtilities.logOut(driver);
	}

	@Parameters({"BrowserName"})
	//TC-26 T
	@Test	
	public  void  TC26newView(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
		if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {
			Assert.assertEquals(true, co.contactsClick.isDisplayed());
			co.contactsClick.click();
			test.info("ContactClicked");
		}
		if (oCommonUtilities.waitForElementVisible(co.createNew)) {
			Assert.assertEquals(true, co.createNew.isDisplayed());
			co.createNew.click();
			test.info("CreateNew Clicked");
		}
		String ViewName="kk3";
		if (oCommonUtilities.waitForElementVisible(co.viewName)) {
			co.viewName.clear();
			co.viewName.sendKeys(ViewName);
			//	if (co.viewName.isDisplayed()) {
			//Thread.sleep(1000);
			//System.out.println("sd00-"+co.viewName.getText());
			//Assert.assertEquals(co.viewName.getText(), "Kk1");
			test.info("ViewName Entered");
			//}
		}
		if (oCommonUtilities.waitForElementVisible(co.viewUniName)) {

			String test1=co.viewUniName.getText();
			co.viewUniName.clear();
			co.viewUniName.sendKeys("sun");
			//Assert.assertEquals(co.viewUniName.getText(), "sun");
			test.info("viewUniName Entered");
		}
		if (oCommonUtilities.waitForElementVisible(co.saveSubmit)) {
			Assert.assertEquals(true, co.contactsClick.isDisplayed());
			co.saveSubmit.click();
			test.info("save Clicked");
		}
		/* check this validation later	
				//WebElement ele=driver.findElement(By.xpath("//*[@id=\"00B5e000009Lto4_listSelect\"]/option[5]"));
				Select select=new Select(co.viewselection);
				String selection1=select.getFirstSelectedOption().getText();
				Assert.assertEquals(ViewName, selection1);	*/
		Assert.assertEquals(driver.getCurrentUrl(), oDataUtils.ReadPageURLproperties("tc26url"));

		if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("tc26url"))) {
			test.info("My contact View Displayed, test case 28 passed");
		}
		else {
			test.info("My contact View  not Displayed, test case 28 failed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase Failed");
		}
		oCommonUtilities.logOut(driver);

	}

	@Parameters({"BrowserName"})
	//TC-27 TC27
	@Test	
	public  void  TC27RecentlyCreatedContact(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
		if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {
			Assert.assertEquals(true, co.contactsClick.isDisplayed());
			co.contactsClick.click();
			test.info("contacts clicked");
		}
		if (oCommonUtilities.waitForElementVisible(co.recentlyViewed)) {
			Select select=new Select(co.recentlyViewed);
			select.selectByVisibleText("Recently Created");
			Assert.assertEquals(select.getFirstSelectedOption().getText(),"Recently Created");
			test.info("Recently created option selected");
		}
		System.out.println("url"+(driver.getCurrentUrl()));
		Assert.assertEquals(driver.getCurrentUrl(), oDataUtils.ReadPageURLproperties("recentlycreated"));

		if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("recentlycreated"))) {
			test.info("Recently created contacts displayed , test case 27 passed");
		}
		else {
			test.info("Recently created contacts not displayed, test case 27 failed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase Failed");
		}


		oCommonUtilities.logOut(driver);
	}


	@Parameters({"BrowserName"})
	//TC-29 TC29ViewContactInContactPage
	@Test	
	public  void  TC29ViewContactInContactPage(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
		if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {
			Assert.assertEquals(true, co.contactsClick.isDisplayed());
			co.contactsClick.click();
			test.info("contacts clicked");
		}
		if (oCommonUtilities.waitForElementVisible(co.contactName)) {
			Assert.assertEquals(true, co.contactName.isDisplayed());
			co.contactName.click();
			test.info("contacts clicked");
			//System.out.println("clicked");	
			//Thread.sleep(2000);
			//String beforexPath=
			//String afterxpath=
		}

		System.out.println("url"+(driver.getCurrentUrl()));
		Assert.assertEquals(driver.getCurrentUrl(), oDataUtils.ReadPageURLproperties("tc29"));

		if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("tc29"))) {
			test.info("Recently created contacts displayed , test case 29 passed");
		}
		else {
			test.info("Recently created contacts not displayed, test case 29 failed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase Failed");
		}


		oCommonUtilities.logOut(driver);
	}
	@Parameters({"BrowserName"})
	//TC-30 TC30ContactserrorMessage
	@Test	
	public  void  TC30ContactserrorMessage(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
		if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {
			Assert.assertEquals(true, co.contactsClick.isDisplayed());
			co.contactsClick.click();
			test.info("contacts clicked");
		}
		if (oCommonUtilities.waitForElementVisible(co.createNew)) {
			Assert.assertEquals(true, co.createNew.isDisplayed());
			co.createNew.click();
			test.info("createnew clicked");
		}
		//if (oCommonUtilities.waitForElementVisible(co.viewName)) {
		//	co.viewName.clear();
		//	co.viewName.sendKeys("Kk1");
		//}
		if (oCommonUtilities.waitForElementVisible(co.viewUniName)) {

			String test1=co.viewUniName.getText();
			co.viewUniName.clear();
			co.viewUniName.sendKeys("EFGH");
			Assert.assertNotNull(co.viewUniName, "Value entered");
		}
		if (oCommonUtilities.waitForElementVisible(co.saveSubmit)) {
			Assert.assertEquals(true, co.saveSubmit.isDisplayed());
			co.saveSubmit.click();
			test.info("save clicked");
		}
		Assert.assertEquals((oDataUtils.ReadPageURLproperties("tc30errormsg.text")), co.errormessage.getText());
		if ((oDataUtils.ReadPageURLproperties("tc30errormsg.text")).equals( co.errormessage.getText())) {
			test.info("Erropr message displayed , test case 30 passed");
		}
		else {
			test.info("Erropr message not displayed , test case 30 failed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase Failed");
		}


		oCommonUtilities.logOut(driver);
	}



	@Parameters({"BrowserName"})
	//TC-31 TC31CheckCancelButton
	@Test	
	public  void  TC31CheckCancelButton(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
		if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {
			Assert.assertEquals(true, co.contactsClick.isDisplayed());
			co.contactsClick.click();
			test.info("contacts clicked");
		}
		if (oCommonUtilities.waitForElementVisible(co.createNew)) {
			Assert.assertEquals(true, co.createNew.isDisplayed());
			co.createNew.click();
			test.info("createNew clicked");
		}
		if (oCommonUtilities.waitForElementVisible(co.viewName)) {
			co.viewName.clear();
			co.viewName.sendKeys((oDataUtils.ReadPageURLproperties("viewname31.txt")));
			Assert.assertNotNull(co.viewName, "Value entered");
		}
		if (oCommonUtilities.waitForElementVisible(co.viewUniName)) {

			String test1=co.viewUniName.getText();
			co.viewUniName.clear();
			co.viewUniName.sendKeys(oDataUtils.ReadPageURLproperties("viewuniname31.txt"));
			Assert.assertNotNull(co.viewUniName, "Value entered");
		}
		if (oCommonUtilities.waitForElementVisible(co.cancelSubmit)) {
			Assert.assertEquals(true, co.cancelSubmit.isDisplayed());
			co.cancelSubmit.click();
			test.info("cancelSubmit clicked");
		}
		boolean flag=false;
		Select select=new Select(co.viewselection);
		List<WebElement>list1=select.getOptions();
		for  (int i=0;i<list1.size();i++) {
			if (list1.get(i).getText().equals( oDataUtils.ReadPageURLproperties("viewname31.txt"))) {
				flag=true;
				break;

			}
		}
		if (flag==true){
			test.info("ViewName visible in dropdown and testcase31 is fail");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("ViewName visible in dropdown and testcase31 is fail");
		}
		else {
			test.info("ViewName visible in dropdown");
		}




		System.out.println("url"+(driver.getCurrentUrl()));



		Assert.assertEquals(driver.getCurrentUrl(), oDataUtils.ReadPageURLproperties("tc31url"));

		if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("tc31url"))) {
			test.info("Recently created contacts displayed , test case 31 passed");
		}
		else {
			test.info("Recently created contacts not displayed, test case 31 failed");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase Failed");
		}
		oCommonUtilities.logOut(driver);
	}


	@Parameters({"BrowserName"})
	//TC-32 T
	@Test	
	public  void  TC32CheckSave(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
		if (oCommonUtilities.waitForElementVisible(co.contactsClick)) {
			Assert.assertEquals(true, co.contactsClick.isDisplayed());
			co.contactsClick.click();
			test.info("contacts clicked");
		}
		if (oCommonUtilities.waitForElementVisible(co.newClick)) {
			Assert.assertEquals(true, co.newClick.isDisplayed());
			co.newClick.click();
			test.info("newClick clicked");
		}
		if (oCommonUtilities.waitForElementVisible(co.lastName)) {
			co.lastName.clear();
			co.lastName.sendKeys("kk");
			Assert.assertNotNull(co.lastName, "Value entered");
			test.info("LastName entered");
		}
		if (oCommonUtilities.waitForElementVisible(co.accountName)) {


			co.accountName.clear();
			co.accountName.sendKeys("hello123");
			Assert.assertNotNull(co.accountName, "Value entered");
			test.info("AccountName entered");
		}
		if (oCommonUtilities.waitForElementVisible(co.SaveNew)) {
			Assert.assertEquals(true, co.SaveNew.isDisplayed());
			co.SaveNew.click();
			test.info("SaveNew clicked");
		}
		oCommonUtilities.logOut(driver);

	}



}
