package Pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Leads {

	public Leads(WebDriver driver) {
		
			if (driver!=null) {
				PageFactory.initElements(driver,this);
			}	
	}
	//tc-20
	@FindBy(xpath="//*[@id=\"Lead_Tab\"]/a")
	public  WebElement leadsClick;
//tc-21
	
	@FindBy(xpath="//*[@id=\"fcf\"]")
	public  WebElement leadsDropDown;
	
	//tc-22
	//*[@id="00B5e000002ttky_listSelect"]
	@FindBy(xpath="//*[@id=\"00B5e000002ttky_listSelect\"]")
	
	//@FindBy(xpath="//select[@id=\"fcf\"]")
	public  WebElement leadsDropDown22;

	@FindBy(xpath="//*[@id=\"filter_element\"]/div/span/span[1]/input")
	public  WebElement goClick;
	
	//tc-23
	
	@FindBy(id="userNavButton")
	public  WebElement usernavgi;
	//tc-24
	
	@FindBy(xpath="//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input")
	public  WebElement newBtn;
	
	@FindBy(xpath="//*[@id=\"name_lastlea2\"]")
	public  WebElement lastName;

	@FindBy(xpath="//*[@id=\"lea3\"]")
	public  WebElement company;

	@FindBy(xpath="//*[@id=\"bottomButtonRow\"]/input[1]")
	public  WebElement save1;
	

	

	
	
	
}
