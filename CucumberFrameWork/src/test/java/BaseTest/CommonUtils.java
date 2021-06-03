package BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonUtils {
	
	private static CommonUtils commonUtils = null;
	
	public WebDriver driver;
	public LoginPage lp;

	private CommonUtils() {
	}
	
	public static CommonUtils getInstance() {
		
		if (commonUtils == null) {
			System.out.println("creating new iinstance");
			commonUtils = new CommonUtils();
			
		}
		return commonUtils;
	}
	
	public void setup() {
		System.out.println("setting up a ChromeDriverffff");
		
		WebDriverManager.chromedriver().setup();
		this.driver=new ChromeDriver();
		lp=new LoginPage(driver);
	}
	
	public void as_a_user_i_should_be_able_to_launch_the_application() {
		//WebDriverManager.chromedriver().setup();
		driver.get("https://login.salesforce.com"); 
	}

	public void as_a_user_i_should_be_able_to_enter_user_credentials() {
	    
	}
	
	public void user_enters_valid_username_and_valid_password_and_clicks_login(String username,String password) {
		login_with_username_password(username, password);
		lp.Login.click();
		 System.out.println(" details"+username+"=="+password);
	}

	public void user_should_be_able_to_see_the_homepage_of_application() {
		System.out.println("correct details");
	}
	

	public void login_with_username_password(String username,String password) {
			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);		
	}

	public void clickonElement(WebElement element) {
		element.click();
	}
	
	public void logout() {
		WebElement usernavgi=driver.findElement(By.xpath("//*[@id=\"userNav\"]"));
		usernavgi.click();	
		WebElement logout=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickonElement(logout);
		//Assert.assertEquals((driver.getCurrentUrl()),(data.ReadPageURLproperties("logout.text")));
		/*if ((driver.getCurrentUrl()).equals(data.ReadPageURLproperties("logout.text"))) {
			//System.out.println("pass");
			BaseTest.test.pass("logout success");
		} else {
			//System.out.println("fail");
			BaseTest.test.addScreenCaptureFromPath(takeScreenshot());
			Assert.fail("logout fail");
		}*/
	}
	
	public boolean waitForElementVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
