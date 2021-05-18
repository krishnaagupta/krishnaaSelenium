package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInpage {
	
	public LogInpage(WebDriver driver) {
	//PageFactory.initElements(driver,LogInpage.class);
	if (driver!=null) {
	PageFactory.initElements(driver,this);
	}
}
	
	@FindBy(xpath="//input[@id='username']")
	//@FindBy(id= "id-identity")
	public  WebElement userName;

	@FindBy(id="password")
	public  WebElement password;
	@FindBy(xpath="//*[@id=\"Login\"]")
	public  WebElement Login;
	@FindBy(id="rem")
	public  WebElement rememberMe;
	@FindBy(xpath="//*[@id=\"error\"]")
	public  WebElement errorMsg;
}
