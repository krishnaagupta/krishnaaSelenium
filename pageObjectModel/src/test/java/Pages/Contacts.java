package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contacts {

	public Contacts(WebDriver driver) {
		// TODO Auto-generated constructor stub
		if (driver!=null) {
			PageFactory.initElements(driver,this);
			}
	}	// T// TODO Auto-generated constructor stub
	//TC-10
	@FindBy(xpath="//*[@id=\"Contact_Tab\"]/a")
	public  WebElement contactsClick;
	@FindBy(xpath="//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input")
	public WebElement newClick;

	@FindBy(xpath="//*[@id=\"name_lastcon2\"]")
	public WebElement firstName;

	@FindBy(xpath="//*[@id=\"con4\"]")
	public WebElement accountName;
	
	@FindBy(xpath="//*[@id=\"bottomButtonRow\"]/input[1]")
	public WebElement Save;
	
	@FindBy(xpath="//*[@id=\"00B5e000002ttlf_listSelect\"]")
	public WebElement dropDownContacts;

	@FindBy(xpath="//*[@id=\"fcf\"]")
	public WebElement dropDownContactsnew;
	
	
	@FindBy(xpath="//*[@id=\"hotlist_mode\"]")
	public WebElement recentlyViewed;
	
	@FindBy(xpath="//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th")
	public WebElement contactName;
	//*[@id="bodyCell"]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th
}