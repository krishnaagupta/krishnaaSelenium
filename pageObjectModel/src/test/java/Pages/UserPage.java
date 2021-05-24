package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage {

	public UserPage(WebDriver driver) {
			if (driver!=null) {
			PageFactory.initElements(driver,this);
			}
		}	// TODO Auto-generated constructor stub
	@FindBy(xpath="//*[@id=\"userNav\"]")
	public  WebElement usernavi;
	
	@FindBy(xpath="//*[@id=\"userNav-menuItems\"]/a[5]")
	public  WebElement logOut;
}
