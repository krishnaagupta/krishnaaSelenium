package TestCases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

import Base.BaseTest;

import org.testng.asserts.Assertion;

import Pages.CreateAccount;
import Pages.CreateOpty;
import Pages.Leads;
import Pages.LogInpage;
import Pages.Ramdom;
import Pages.UserPage;

public class TCRamdomScenerio extends Base.BaseTest {

	public LogInpage  lp;
	public UserPage up;
	public CreateAccount cp;
	public CreateOpty op;
	public Leads le;
	public Ramdom rp;
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
		rp=new Ramdom(driver);
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

	//tc-33	TC33HomeTab
	@Parameters({"BrowserName"})
	@Test	
	public  void  TC33HomeTab(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";

		Thread.sleep (1000);
		if (oCommonUtilities.waitForElementVisible(rp.homeTab)) {

			Assert.assertEquals(true, rp.homeTab.isDisplayed());
			rp.homeTab.click();
			test.info("homeTab Clicked");
		}

		Assert.assertEquals(driver.getCurrentUrl(),(oDataUtils.ReadPageURLproperties("hopepage.text")));
		if (driver.getCurrentUrl().equals(oDataUtils.ReadPageURLproperties("hopepage.text"))) {

			test.pass(" home page displayed");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			sa.fail("home page not displayed");
		}
		String text1="";
		if (oCommonUtilities.waitForElementVisible(rp.firstLastName)) {
			//WebElement ele=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1"));
			//String rr=ele.getText();
			//ele.click();                               
			driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
			text1=(rp.firstLastName.getText());
			//System.out.println("URl"+text1);
			Assert.assertEquals(true, rp.firstLastName.isDisplayed());
			rp.firstLastName.click();
			test.info("homeTab Clicked");
		}
		System.out.println("textout"+text1);
		//text1=rp.firstLastName.getText();
		up.usernavi.click();
		rp.myProfile.click();
		String text=rp.CheckFirstLastName.getText();
		//System.out.println("textouprofilt"+text);
		Assert.assertEquals((text.trim()),(text1.trim()));	
		if (text.trim().equals (text1.trim())) {
			test.info("First name in Home and MyProfile page Match");
			//System.out.println("tpass");
		}
		else {
			test.info("First name in Home and MyProfile page do not match  Match");
			//System.out.println("fail");
		}

		oCommonUtilities.logOut(driver);


		sa.assertAll();
	}

	//tc-34	TC33HomeTab
	@Parameters({"BrowserName"})
	@Test	
	public  void  TC34verifyedit(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";

		Thread.sleep (1000);
		if (oCommonUtilities.waitForElementVisible(rp.homeTab)) {
			Assert.assertEquals(true, rp.homeTab.isDisplayed());
			rp.homeTab.click();
			test.info("+Home Tab Clicked");
		}
		if (oCommonUtilities.waitForElementVisible(rp.firstLastName)) {
			Assert.assertEquals(true, rp.firstLastName.isDisplayed());
			rp.firstLastName.click();

			test.info("+firstLastName Tab Displayed and Clicked");
		}

		if (oCommonUtilities.waitForElementVisible(up.editbutton)) {
			Assert.assertEquals(true, up.editbutton.isDisplayed());
			up.editbutton.click();
			test.info("+Edit is Clicked");	

		}
		driver.switchTo().frame("contactInfoContentId");

		Assert.assertEquals(true, rp.contactTab.isEnabled());
		if ( rp.contactTab.isEnabled()) {
			test.info("ContactTab Selected");
		}

		else{
			test.info("ContactTab  not Selected");

		}


		if (oCommonUtilities.waitForElementVisible(up.aboouttab)) {
			up.aboouttab.click();
			Assert.assertEquals(true, up.aboouttab.isEnabled());
			if ( up.aboouttab.isEnabled()) {
				test.info("About Selected");
			}

			else{
				test.info("About  not Selected");

			}

		}


		if (oCommonUtilities.waitForElementVisible(up.lastname1)) {
			up.lastname1.clear();
			up.lastname1.sendKeys("GUPTA");
			Assert.assertNotNull(up.lastname1);
			if (up.lastname1.getText()!="") {
				test.info("LastName entered");
				System.out.println("lastanme entered");
			}
			else {
				test.info("LastName notEntered");
			}
		}	
		if (oCommonUtilities.waitForElementVisible(up.saveclick)) {
			Assert.assertEquals(true, up.saveclick.isEnabled());
			up.saveclick.click();

			Assert.assertEquals(driver.getCurrentUrl(),oDataUtils.ReadPageURLproperties("profilepage.text"));
			if ((driver.getCurrentUrl()).equals(oDataUtils.ReadPageURLproperties("profilepage.text"))) {
				//System.out.println("pass");
				BaseTest.test.pass("TestCase 34 Pass");
			} else {
				//System.out.println("fail");
				BaseTest.test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
				Assert.fail("TestCase 34 fail");
			}


		}	


		oCommonUtilities.logOut(driver);

	}





	//tc-35	TC33HomeTab// hard asserts used
	@Parameters({"BrowserName"})
	@Test	
	public  void  TC35VerifyTab(Method mName,String BrowserName)throws IOException, InterruptedException {	 

		//String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
		String textSelectiontabs="Dashboards";
		if (oCommonUtilities.waitForElementVisible(rp.plusMark)) {
			Assert.assertEquals(true, rp.plusMark.isDisplayed());
			rp.plusMark.click();
			test.info("+Mark Clicked");
		}

		if (oCommonUtilities.waitForElementVisible(rp.customizeTab)) {
			//sa.assertEquals(true,rp.customizeTab.isDisplayed());
			rp.customizeTab.click();
			test.info("+Customized Clicked");
		}
		Select select1;
		if (oCommonUtilities.waitForElementVisible(rp.selection)) {
			select1= new Select(rp.selection);



			List<WebElement> list1 = select1.getOptions();
			boolean flag=false;

			for (int i=0;i<list1.size();i++)
			{
				if (list1.get(i).getText().equals(textSelectiontabs))
				{		
					flag=true;
					break;
				}
			}				
			if (flag==true)	 {
				Thread.sleep(3000);

				if (oCommonUtilities.waitForElementVisible(rp.selection)) {

					Select select=new Select (rp.selection);
					select.selectByVisibleText(textSelectiontabs);
					System.out.println("selected");
				}
				test.info("Element present in selection");
				Assert.assertTrue(rp.selection.isEnabled());

				//	select.selectByValue("Orders");
				if (oCommonUtilities.waitForElementVisible(rp.remove)) {
					Assert.assertEquals(true,rp.remove.isDisplayed());
					rp.remove.click();
					test.info("Remove Button Clicked");
				}


			}
			else {
				test.fail("Element not present in selection taband hence cannot be removed");
				Assert.fail("Element not present in selection taband hence cannot be removed");
				oCommonUtilities.logOut(driver);
			}

		}
		if (oCommonUtilities.waitForElementVisible(rp.save)) {
			rp.save.click();
		}
		oCommonUtilities.logOut(driver);
		String BrowserName1="Chrome";
		Login (BrowserName1);
		String arr[];
		//if (oCommonUtilities.waitForElementVisible(rp.tabMenu)) {
		boolean flag1=false;
		List<WebElement> menu= driver.findElements(By.xpath("//*[@id=\"tabBar\"]"));
		//Select select2= new Select(menu);
		//List<WebElement>list2=select2.getOptions();
		for (int j=0;j<menu.size();j++) {
			//WebElement menu= driver.findElement(By.xpath("//*[@id=\"tabBar\"]/li["+j+"]"));
			if(menu.get(j).getText().equals(textSelectiontabs)) {
				flag1=true;
				break;
			}	
		}	
		if (flag1==true) {	
			test.fail("Element present in Menu TabTestCase 35 Passed");
			Assert.fail("Element present in Menu Tab");
			oCommonUtilities.logOut(driver);

		}
		else {
			test.info(textSelectiontabs+"not present in Menutab ,TestCase 35 Passed");

		}


		oCommonUtilities.logOut(driver);

	}

}



