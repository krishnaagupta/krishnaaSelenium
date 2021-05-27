package TestCases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseTest;
import Pages.CreateAccount;
import Pages.LogInpage;
import Pages.UserPage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TCCreateAccount extends BaseTest {

	SoftAssert sa = new SoftAssert();
	public LogInpage  lp;
	public UserPage up;
	public CreateAccount cp;
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
	//@Test

	//TC-10Create an Account
	public  void  TC10CreateAccount(Method mName)throws IOException, InterruptedException {			
		String BrowserName="Chrome";
		Login (BrowserName);
		String sentText="";
		String check="";
		if (oCommonUtilities.waitForElementVisible(cp.accountClick)) {
			cp.accountClick.click();	
		}
		test.info("Account Clicked");
		if (oCommonUtilities.waitForElementVisible(cp.newClick)) {
			cp.newClick.click();	
		}
		test.info("New Account  Clicked");
		if (oCommonUtilities.waitForElementVisible(cp.accountName)) {
			cp.accountName.clear();
			cp.accountName.sendKeys(oDataUtils.ReadAccountProperties("account.Text"));
			sentText="hello123";
		}
		test.info("New account name sent  Clicked");


		if (oCommonUtilities.waitForElementVisible(cp.saveClick)) {
			cp.saveClick.click();	
		}
		test.info("Save  Clicked");
		if (oCommonUtilities.waitForElementVisible(cp.checkAccountName)) {
			check=cp.checkAccountName.getText();

		}
		oCommonUtilities.logOut(driver);
		sa.assertEquals(sentText,check);
		if ((sentText).equals (check)) {
			test.pass("TestCase 10 "+" PASSED"+ "Account name checked");

		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase 10 "+" failed");
			String path = oCommonUtilities.takeScreenshot();
			test.addScreenCaptureFromPath(path);
		}
		
		oCommonUtilities.logOut(driver);
		sa.assertAll();	
	}
	//@Test
	//TC-11Create new view
	public  void  TC11CreateNewView(Method mName)throws IOException, InterruptedException {			
		String BrowserName="Chrome";
		Login (BrowserName);
		String viewtext="",viewUniqueText="",displayName="";
		if (oCommonUtilities.waitForElementVisible(cp.accountClick)) {
			cp.accountClick.click();	
		}
		test.info("Account clicked");
		if (oCommonUtilities.waitForElementVisible(cp.createNewView)) {
			cp.createNewView.click();	
		}
		
		//displayName =up.usernavi.getText();
		//System.out.println("displayName--"+ displayName);
		//System.out.println("url--"+driver.getCurrentUrl());
		
		sa.assertTrue(driver.getCurrentUrl().contains("FilterEditPage"));
		
		if (driver.getCurrentUrl().contains("FilterEditPage")) {
			test.pass("Account Page Displayed");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("Account Page not  Displayed");
		}
		
		sa.assertTrue(up.usernavi.getText().equals("krishnaa GUPTA"));
		
		if (up.usernavi.getText().equals("krishnaa GUPTA")) {
			test.pass("correct used name displayed");
		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("Username displayed in correct");
		}
		
		
		test.info("Create new clicked");
		if (oCommonUtilities.waitForElementVisible(cp.viewName)) {
			cp.viewName.clear();
			cp.viewName.sendKeys("om22243");
			viewtext="om22233";
		}
		test.info("View Name"+viewtext +" entered");

		if (oCommonUtilities.waitForElementVisible(cp.viewUniquename)) {
			cp.viewUniquename.clear();
			cp.viewUniquename.sendKeys("om11134");
			viewUniqueText="om11113";
		}
		test.info("View Name"+viewUniqueText +" entered");

		if (oCommonUtilities.waitForElementVisible(cp.clickSave)) {
			cp.clickSave.click();	
		}
		test.info("save clicked");
		Select select = new Select(cp.displayView);

		// get selected option with getFirstSelectedOption() with its text
		WebElement o = select.getFirstSelectedOption();
		String selectedoption = o.getText();
		oCommonUtilities.logOut(driver);

		sa.assertEquals(selectedoption,viewtext);
		if ((selectedoption).equals (viewtext)) {
			test.pass("TestCase 11 "+" PASSED");

		} else {
			test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
			Assert.fail("TestCase 11 "+" failed");
			String path = oCommonUtilities.takeScreenshot();
			test.addScreenCaptureFromPath(path);
		}	
		sa.assertAll();
	
	}

		
		//@Test
		//TC-12 Edit view
		public  void  TC12Editview(Method mName)throws IOException, InterruptedException {			
			String BrowserName="Chrome";
			Login (BrowserName);
			String viewtext="",viewUniqueText="";
			if (oCommonUtilities.waitForElementVisible(cp.accountClick)) {
				cp.accountClick.click();	
			}
		
			sa.assertTrue(driver.getCurrentUrl().contains(oDataUtils.ReadAccountProperties("Salesforce.HomePage")));
			if (driver.getCurrentUrl().contains(oDataUtils.ReadAccountProperties("Salesforce.HomePage"))) {

				test.pass(mName.getName()+" PASSED");
			} else {
				test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
				Assert.fail(mName.getName()+"TC02 FAILED");
			}
		
		if (cp.dropDownClick.isDisplayed()) {
			
			if (oCommonUtilities.waitForElementVisible(cp.editClick)) {
				cp.editClick.click();	
			}
			
			if (oCommonUtilities.waitForElementVisible(cp.viewName1)) {
				cp.viewName1.clear();
				cp.viewName1.sendKeys("john");
			}
			if (oCommonUtilities.waitForElementVisible(cp.accountName1)) {
				
				Select select= new Select(cp.accountName1);
				select.selectByVisibleText("Account Name");
			}
		
			
			if (oCommonUtilities.waitForElementVisible(cp.opertaor)) {
				
				Select select= new Select(cp.opertaor);
				select.selectByVisibleText("contains");
			}
			
			if (oCommonUtilities.waitForElementVisible(cp.value)) {
				cp.value.clear();
				cp.value.sendKeys("<a>");
			}
			
		//save
			if (oCommonUtilities.waitForElementVisible(cp.save1)) {
				cp.save1.click();	
			}
		}
		oCommonUtilities.logOut(driver);
		sa.assertAll();	
	}
	
		//@Test
		//TC-14 crteate Report
		public  void  TC14CreateReport(Method mName)throws IOException, InterruptedException {			
			String BrowserName="Chrome";
			Login (BrowserName);
			String viewtext="",viewUniqueText="";

			if (oCommonUtilities.waitForElementVisible(cp.accountClick)) {
				cp.accountClick.click();	
			}
			
			if (oCommonUtilities.waitForElementVisible(cp.AccountreportClick)) {
				cp.AccountreportClick.click();	
			}
			
			if (oCommonUtilities.waitForElementVisible(cp.fromDate)) {
				cp.fromDate.click();	
			}
			if (oCommonUtilities.waitForElementVisible(cp.todayDate)) {
				cp.todayDate.click();	
			}
			if (oCommonUtilities.waitForElementVisible(cp.toDate)) {
				cp.toDate.click();	
			}
			if (oCommonUtilities.waitForElementVisible(cp.today_Date)) {
				cp.today_Date.click();	
			}

			if (oCommonUtilities.waitForElementVisible(cp.saveButton)) {
				cp.saveButton.click();	
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
			//driver.close();
			//driver.switchTo().window(parentWindowHandle);
			
			if (oCommonUtilities.waitForElementVisible(cp.reportName)) {
				cp.reportName.clear();	
				cp.reportName.sendKeys("kk24");
			}
			
			if (oCommonUtilities.waitForElementVisible(cp.reportUniqueName)) {
				cp.reportUniqueName.clear();	
				cp.reportUniqueName.sendKeys("kk24");
			}
			Thread.sleep(1000);
			if (oCommonUtilities.waitForElementVisible(cp.saveRunBtn)) {
		//System.out.println("dfsdf&&&__"+cp.saveRunBtn.getText());
				cp.saveRunBtn.click();	
			}
			
			oCommonUtilities.logOut(driver);
			sa.assertAll();			

		
		}
		
	
		
		@Test
		//TC-13 Merge Report		
	public  void  TC13Merge(Method mName)throws IOException, InterruptedException {			
		String BrowserName="Chrome";
		Login (BrowserName);
		//String viewtext="",viewUniqueText="";
		if (oCommonUtilities.waitForElementVisible(cp.accountClick)) {
			cp.accountClick.click();	
		}
		
		if (oCommonUtilities.waitForElementVisible(cp.mergeAccount)) {
			cp.mergeAccount.click();	
		}
		if (oCommonUtilities.waitForElementVisible(cp.findAcctTest)) {
			cp.findAcctTest.sendKeys("hello123");	
		}
		//Select select= new Select(cp.findAcctTest);
		//select.selectByVisibleText("hello123");
		
		if (oCommonUtilities.waitForElementVisible(cp.findAcctSubmit)) {
			cp.findAcctSubmit.click();	
		}
		
		if (oCommonUtilities.waitForElementVisible(cp.datarRow1)) {
			cp.datarRow1.click();	
		}
		if (oCommonUtilities.waitForElementVisible(cp.datarRow2)) {
			cp.datarRow2.click();	
		}
		if (oCommonUtilities.waitForElementVisible(cp.nextSubmit1)) {
			cp.nextSubmit1.click();	
		}
		Thread.sleep(1000);
		if (oCommonUtilities.waitForElementVisible(cp.merge)) {
			cp.merge.click();	
		}
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		driver.switchTo().parentFrame();
		//Select select1 =new Select (cp.recentlyViewed);
		//select1.selectByVisibleText("Recently Viewed Accounts");
		
		//if (oCommonUtilities.waitForElementVisible(cp.go)) {
		//	cp.go.click();	
		//}
		oCommonUtilities.logOut(driver);
		sa.assertAll();	
	}
		
		
		
}





