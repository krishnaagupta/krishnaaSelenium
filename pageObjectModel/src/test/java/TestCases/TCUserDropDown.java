package TestCases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



import Pages.LogInpage;
import Pages.UserPage;
import Base.BaseTest;


public class TCUserDropDown extends BaseTest{

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

		lp=new LogInpage(driver);// Important step initilaize both the lp and up ojects 
		up=new UserPage(driver);// Important step initilaize both the lp and up ojects

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
			System.out.println("login");	
		}
	}

	//@Parameters({"BrowserName"})
	//@Test


	@Parameters({"BrowserName"})
	@Test(priority=1)
	//public  void  Tc9Logout(String BrowserName,Method mName)throws IOException, InterruptedException {
	public  void  TC9Logout(Method mName,String BrowserName)throws IOException, InterruptedException {	
		//String BrowserName="Chrome";
		Login (BrowserName);
		oCommonUtilities.logOut(driver);

		test.info("LoggedoutSuccessfully");
		System.out.println("tillte "+driver.getCurrentUrl());
		sa.assertEquals(driver.getCurrentUrl(), "https://xyz-72f-dev-ed.my.salesforce.com/secur/logout.jsp");
		if (driver.getCurrentUrl().equalsIgnoreCase("https://xyz-72f-dev-ed.my.salesforce.com/secur/logout.jsp")) {
			test.pass(mName.getName()+" Tc09 PASSED");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail(mName.getName()+"TC09 FAILED");
		}
		sa.assertAll();

	}

	@Test(priority=2)
	//testCase 08-Select "Developers Console" option from user menu for <username> drop down
	public  void  TC8DeveloperConsole(Method mName,String BrowserName)throws IOException, InterruptedException {	

		//String BrowserName="Chrome";
		Login (BrowserName);

		if (oCommonUtilities.waitForElementVisible(up.usernavi)) {
			up.usernavi.click();
		}


		if (oCommonUtilities.waitForElementVisible(up.developerconsole)) {
			up.developerconsole.click();
		}

		System.out.println("tillte "+driver.getCurrentUrl());
		sa.assertEquals(driver.getCurrentUrl(), "https://xyz-72f-dev-ed.my.salesforce.com/setup/forcecomHomepage.apexp?setupid=ForceCom");
		if (driver.getCurrentUrl().equalsIgnoreCase("https://xyz-72f-dev-ed.my.salesforce.com/setup/forcecomHomepage.apexp?setupid=ForceCom")) {
			test.pass(mName.getName()+" Tc08 PASSED");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail(mName.getName()+"TC08 FAILED");
		}


		String parentWindowHandle = driver.getWindowHandle();
		String popupWindowHandler = null;
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
			popupWindowHandler = iterator.next();
		}
		System.out.println("opo"+popupWindowHandler);
		driver.switchTo().window(popupWindowHandler);
		driver.close();
		driver.switchTo().window(parentWindowHandle);


		oCommonUtilities.logOut(driver);

		test.info("LoggedoutSuccessfully");
		//System.out.println("tillte "+driver.getCurrentUrl());
		sa.assertEquals(driver.getCurrentUrl(), "https://xyz-72f-dev-ed.my.salesforce.com/secur/logout.jsp");
		if (driver.getCurrentUrl().equalsIgnoreCase("https://xyz-72f-dev-ed.my.salesforce.com/secur/logout.jsp")) {
			test.pass(mName.getName()+" Tc09 PASSED");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail(mName.getName()+"TC09 FAILED");
		}
		sa.assertAll();

	}
	@Test(priority=3)
	//testCase 05-userDropDown Menu
	public  void  TC5UserDropDown(Method mName,String BrowserName)throws IOException, InterruptedException {
		//String BrowserName="Chrome";
		Login (BrowserName);

		if (oCommonUtilities.waitForElementVisible(up.usernavi)) {
			up.usernavi.click();
		}

		String TextToAdd="";
		String dropDownarr[] = new String[100];	
		for(int i=1;i<=5;i++) {
			WebElement element= driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a["+i+"]"));
			dropDownarr[i]=element.getText();		
		}
		oCommonUtilities.logOut(driver);		
		String testCaseArr[] = {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};
		int len=5;
		//improve
		//for (int i=1;i<=len;i++) {
		//sa.assertEquals(dropDownarr[1],"My Profile");
		//sa.assertEquals(dropDownarr[1],"My Profile");	
		//sa.assertEquals(dropDownarr[1],);
		//System.out.println("tillte "+testCaseArr[0]);
		//sa.assertEquals(dropDownarr[2],"My Settings");
		//System.out.println("tillte "+testCaseArr[1]);
		//sa.assertEquals(dropDownarr[3],"Developer Console");
		//System.out.println("tillte "+testCaseArr[2]);
		//sa.assertEquals(dropDownarr[4],"Switch to Lightning Experience");
		//System.out.println("tillte "+testCaseArr[3]);
		//sa.assertEquals(dropDownarr[5],"Logout");
		//System.out.println("tillte "+testCaseArr[4]);*/
		//}
		for (int i=1;i<=len;i++) {
			if (testCaseArr[i-1].equals(dropDownarr[i])) {
				test.pass(mName.getName()+" Tc05 PASSED -User drop Down verified");

			}
			else {
				test.pass(mName.getName()+" Tc05 Failed");
			}

		}
		oCommonUtilities.logOut(driver);
		sa.assertAll();	
	}
	@Test(priority=4)
	// test case7 Select "My settings" option from user menu for <username> drop down
	public  void  TC7MySetting(Method mName,String BrowserName)throws IOException, InterruptedException {	

		//String BrowserName="Chrome";
		Login (BrowserName);

		if (oCommonUtilities.waitForElementVisible(up.usernavi)) {
			up.usernavi.click();
		}
		if (oCommonUtilities.waitForElementVisible(up.setting)) {
			up.setting.click();
		}
		if (oCommonUtilities.waitForElementVisible(up.personalSetting)) {
			up.personalSetting.click();
		}
		test.info("personal setting  displayed");
		if (oCommonUtilities.waitForElementVisible(up.logInHistroy)) {
			up.logInHistroy.click();
		}
		test.info("loginHistroy  clicked");
		if (oCommonUtilities.waitForElementVisible(up.downloadLoginlink)) {
			up.downloadLoginlink.click();
		}
		test.info("downloadLoginHistroy displayed");
		//System.out.println("Tittle"+driver.getCurrentUrl());
		//improve
		//*sa.assertEquals(driver.getCurrentUrl(), "//xyz-72f-dev-ed.my.salesforce.com/ui/setup/personal/LoginHistorySetupPage?setupid=LoginHistory&retURL=%2Fui%2Fsetup%2FSetup%3Fsetupid%3DPersonalInfo");

		if (driver.getCurrentUrl().contains("LoginHistorySetupPage")) {
			test.info("Login histroy page  displayed");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail(mName.getName()+"TC07 FAILED");
		}

		if (oCommonUtilities.waitForElementVisible(up.downloadLoginlink)) {
			up.downloadLoginlink.click();
		}

		driver.switchTo().parentFrame();
		//display layout
		if (oCommonUtilities.waitForElementVisible(up.displaylayout)) {
			up.displaylayout.click();
		}
		if (oCommonUtilities.waitForElementVisible(up.customizeTab)) {
			up.customizeTab.click();
		}

		if (oCommonUtilities.waitForElementVisible(up.customizeoption)) {
			up.customizeoption.click();
		}

		if (oCommonUtilities.waitForElementVisible(up.selectReports)) {
			up.selectReports.click();
		}

		if (oCommonUtilities.waitForElementVisible(up.addReport)) {
			up.addReport.click();
		}

		if (oCommonUtilities.waitForElementVisible(up.HitSave)) {
			up.HitSave.click();
		}

		//improve	
		/*WebElement selectTabs= driver.findElement(By.xpath("//*[@id=\"tabBar\"]"));
		String selection[] = new String[100];	
		for(int i=1;i<=5;i++) {
			WebElement element= driver.findElement(By.xpath("//*[@id=\"duel_select_1\"]/option["+i+"]"));	
			selection[i]=element.getText();		
		}
		boolean flag=false; 
		for(int i=1;i<=selection.length;i++) {
		if (selection[i]=="Reports") {
			flag=true;
		}
		}*/
		//if (flag==true) {
		//if(oCommonUtilities.waitForElementVisible(up.reportsTab)) {
		if ((up.reportsTab).isDisplayed()) {
			sa.assertEquals("Repoprt  visible in tab","Repoprt  visible in tab");
			test.info("Repoprt  visible in tab");
		}
		else {
			sa.fail("Repoprt not visible in tab");
			test.info("Repoprt not visible in tab");
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
		}



		//Email set up
		if (oCommonUtilities.waitForElementVisible(up.email)) {
			up.email.click();
		}
		test.info("Email clicked");
		if (oCommonUtilities.waitForElementVisible(up.myEmail)) {
			up.myEmail.click();
		}
		test.info("Email setting clicked");


		if (oCommonUtilities.waitForElementVisible(up.emailName)) {
			up.emailName.clear();
			//oCommonUtilities.enterText(up.emailName, "krishnaa", "Name");
			up.emailName.sendKeys("krishnaa");
		}

		if (oCommonUtilities.waitForElementVisible(up.senderEmail)) {
			up.senderEmail.clear();
			//oCommonUtilities.enterText(up.senderEmail, "krishnaa.gupta@yahoo.com", "emailid");
			up.senderEmail.sendKeys("krishnaa.gupta@yahoo.com");
		}
		System.out.println("email"+up.emailName.getText());
		sa.assertNotNull(up.emailName.getText(),"krishnaa");
		sa.assertNotNull(up.senderEmail.getText(),"krishnaa.gupta@yahoo.com");

		if (oCommonUtilities.waitForElementVisible(up.radioButton)) {
			up.radioButton.click();
		}
		test.info("Radio button for  automatic BCC selected");

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		if (oCommonUtilities.waitForElementVisible(up.saveButton)) {
			up.saveButton.submit();
			System.out.println("save visible");
		}
		test.info("Name and Email id provided ans save button clicked");


		//calender
		if (oCommonUtilities.waitForElementVisible(up.calender)) {
			up.calender.click();
		}
		if (oCommonUtilities.waitForElementVisible(up.reminder)) {
			up.reminder.click();
		}
		if (driver.getCurrentUrl().contains("ReminderSettingsPage")) {
			test.info("ReminderSettingsPage displayed");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail(mName.getName()+"ReminderSettingsPage not displayed");	
		}
		if (oCommonUtilities.waitForElementVisible(up.openTest)) {
			up.openTest.click();
		}

		oCommonUtilities.logOut(driver);
		sa.assertAll();	
	}

	@Test(priority=5)
	//test case6 Select "My Profile" option from user menu for <username> drop down
	public  void  TC6MyProfile(Method mName,String BrowserName)throws IOException, InterruptedException {	
		//String BrowserName="Chrome";
		Login (BrowserName);
		//Select usernavigation
		if (oCommonUtilities.waitForElementVisible(up.usernavi)) {
			up.usernavi.click();
		}


		if (oCommonUtilities.waitForElementVisible(up.myprofile)) {
			up.myprofile.click();
		}

		if (oCommonUtilities.waitForElementVisible(up.editbutton)) {
			up.editbutton.click();
		}	
		driver.switchTo().frame("contactInfoContentId");
		if (oCommonUtilities.waitForElementVisible(up.aboouttab)) {
			up.aboouttab.click();
		}			
		if (oCommonUtilities.waitForElementVisible(up.lastname1)) {
			up.lastname1.clear();
			up.lastname1.sendKeys("GUPTA");
		}	
		if (oCommonUtilities.waitForElementVisible(up.saveclick)) {
			up.saveclick.click();
		}			

		if (oCommonUtilities.waitForElementVisible(up.feed1)) {
			up.feed1.click();
		}			
		if (oCommonUtilities.waitForElementVisible(up.post1)) {
			up.post1.click();
		}	

		Thread.sleep(1000);
		driver.switchTo().frame(up.postXPath);	

		Thread.sleep(2000);	
		if (oCommonUtilities.waitForElementVisible(up.posttext)) {
			up.posttext.sendKeys("hihow are you");
		}	
		driver.switchTo().parentFrame();		
		if (oCommonUtilities.waitForElementVisible(up.shareButton)) {
			up.shareButton.click();
		}			

		//add a photo

		Thread.sleep(2000);
		//if (oCommonUtilities.waitForElementVisible(up.photoHavour)) {
		up.photoHavour.click();
		Actions action=new Actions(driver);
		action.moveToElement(up.photoHavour).build().perform();

		if (oCommonUtilities.waitForElementVisible(up.photoUpload)) {
			up.photoUpload.click();
		}	

		//driver1.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.switchTo().frame("uploadPhotoContentId");




		WebElement PhotoChooseFile=driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));

		PhotoChooseFile.sendKeys("/Users/krishnaagupta/Documents/reference.png");


		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		if (oCommonUtilities.waitForElementVisible(up.photoSave)) {
			up.photoSave.click();
		}	
		if (oCommonUtilities.waitForElementVisible(up.photoCancel)) {
			up.photoSave.click();
		}
		if (oCommonUtilities.waitForElementVisible(up.photoSaveFinal)) {
			up.photoSaveFinal.click();
		}
		oCommonUtilities.logOut(driver);
		sa.assertAll();	
	}
}

