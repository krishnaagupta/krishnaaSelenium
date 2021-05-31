package Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import Utilities.DataUtilities;

import com.aventstack.extentreports.Status;

import Base.BaseTest;

public class CommonUtilities {
 DataUtilities data=new DataUtilities();
	public void enterText(WebElement element, String textToEnter, String elementName) {
		if (element.isDisplayed()) {
			element.sendKeys(textToEnter);
			BaseTest.test.log(Status.INFO, elementName + " is entered");
		} else
			BaseTest.test.log(Status.FAIL, elementName + " is not displayed");
	}

	public void clickonElement(WebElement element, String elementName) {

		element.click();
		BaseTest.test.log(Status.INFO, elementName + " is clicked");
	}

	public void verifyText(String ActualText, String ExpectedText, String msg) throws IOException {
		if (ActualText.equals(ExpectedText)) {
			BaseTest.test.pass(msg + "is verified successfully");
		} else {
			BaseTest.test.fail(msg + " verification is failed");
//			BaseTest.test.addScreenCaptureFromPath(takeScreenshot());
		}
	}

	public void verifyUsermenu(String ElementXapth) throws IOException {
		List<WebElement> userMenuItems = BaseTest.driver.findElements(By.xpath(ElementXapth));
		String[] ExpectedmenuItems = { "My Profile", "My Settings", "Developer Console",
				"Switch to Lightning Experience", "Logout" };

		for (int i = 0; i < userMenuItems.size(); i++) {
			Assert.assertEquals(userMenuItems.get(i).getText(), ExpectedmenuItems[i]);
			BaseTest.test.log(Status.INFO, ExpectedmenuItems[i] + " is Verified");
		}
	}

	public boolean waitForElementVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseTest.driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}


//	 TakesScreenshot srcShot = ((TakesScreenshot) driver);
//        File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
//        String imagePath = reportFolder + new SimpleDateFormat("'Image_'YYYYMMddHHmm'.png'").format(new Date());
//        File destFile = new File(imagePath);
//        FileUtils.copyFile(srcFile,destFile);
//        return imagePath;
	public String takeScreenshot() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) BaseTest.driver;
		String addDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		//String destinationPath = System.getProperty("user.dir") + "/Reports/Screenshots/" + addDate + ".PNG";
		// my String destinationPath = System.getProperty("/Users/krishnaagupta/eclipse-workspace/javaprog/Testmaven/target/HtmlReports" )+ addDate + ".PNG";
		//String destinationPath = AppConstants.EXTENT_HTML_REPORT_PATH+"/"+addDate + ".PNG";
		String destinationPath = addDate + ".PNG";
		File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
		File dstfile = new File(destinationPath);
		FileUtils.copyFile(srcfile, dstfile);
//        test.addScreenCaptureFromPath(destinationPath);
//        test.fail("Login to homepage failed");
		return destinationPath;
	}

	public void logintoSFDC(String username, String pass) throws InterruptedException, IOException {
//        test = extent.createTest("logintoSFDC_TC02");
		WebElement username1 = BaseTest.driver.findElement(By.name("username"));
		enterText(username1, username, "Username");
		BaseTest.test.log(Status.INFO, "usernmae is enterd");
		WebElement password = BaseTest.driver.findElement(By.name("pw"));
		enterText(password, pass, "Password");
		BaseTest.test.log(Status.INFO, "Password field");
		WebElement loginButton = BaseTest.driver.findElement(By.id("Login"));
		clickonElement(loginButton, "LoginButton");
		Thread.sleep(5000);
//		verifyText(BaseTest.driver.getTitle(), readPropertiesfile("Messages", "homepage.title"), "HomePage title");
//		 To logout
		
	
	}

	public void logOut(WebDriver driver) throws IOException {
		// TODO Auto-generated method stub
		WebElement usernavgi=driver.findElement(By.xpath("//*[@id=\"userNav\"]"));
		clickonElement(usernavgi, "usernavgation click");		
		BaseTest.test.log(Status.INFO, "usernmae is enterd");
		WebElement logout=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickonElement(logout,"LogOut");
		BaseTest.test.log(Status.INFO, "logoutClicked");
		//String logOutText="https://xyz-72f-dev-ed.my.salesforce.com/secur/logout.jsp";
		Assert.assertEquals((driver.getCurrentUrl()),(data.ReadPageURLproperties("logout.text")));
		//Assert.assertEquals((driver.getCurrentUrl()),(logOutText));
		//System.out.println(driver.getCurrentUrl()+"---"+(data.ReadPageURLproperties("logout.text")));
		if ((driver.getCurrentUrl()).equals(data.ReadPageURLproperties("logout.text"))) {
			//System.out.println("pass");
			BaseTest.test.pass("logout success");
		} else {
			//System.out.println("fail");
			BaseTest.test.addScreenCaptureFromPath(takeScreenshot());
			Assert.fail("logout fail");
		}
	
	}
	public  static void selectdateByJs( WebDriver driver,WebElement element,String date)
	{
			JavascriptExecutor js=((JavascriptExecutor)driver);
			js.executeScript("arguments[0].setAttribute('value','"+date+"');", element);
	}
}


