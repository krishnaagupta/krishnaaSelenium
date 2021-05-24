package Pages;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath="//*[@id=\"rememberUn\"]")
	public  WebElement rememberMe;
	
	@FindBy(xpath="//*[@id=\"error\"]")
	public  WebElement errorMsg;
	

	@FindBy(xpath="//*[@id=\"userNav-menuItems\"]/a[5]")
	public  WebElement logOut;

	@FindBy(xpath="//*[@id=\"idcard-identity\"]")
	public  WebElement remembermeUserName;
	
	@FindBy(xpath="//*[@id=\"forgot_password_link\"]")
	public  WebElement forgotPassword;
	
	@FindBy(id="un")
	public  WebElement email;
	
	@FindBy(id="continue")
	public  WebElement continueButton;
	
	
}