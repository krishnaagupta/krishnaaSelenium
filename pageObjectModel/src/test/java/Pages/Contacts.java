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
	@FindBy(xpath="//*[@id=\"name_lastcon2\"]")
	public WebElement lastName;
	

	@FindBy(xpath="//*[@id=\"con4\"]")
	public WebElement accountName;
	
	@FindBy(xpath="//*[@id=\"bottomButtonRow\"]/input[1]")
	public WebElement Save;
	@FindBy(xpath="//*[@id=\"topButtonRow\"]/input[2]")
	public WebElement SaveNew;
	
	
	@FindBy(xpath="//*[@id=\"00B5e000002ttlf_listSelect\"]")
	public WebElement dropDownContacts;

	@FindBy(xpath="//*[@id=\"fcf\"]")
	public WebElement dropDownContactsnew;
	
	
	@FindBy(xpath="//*[@id=\"hotlist_mode\"]")
	public WebElement recentlyViewed;
	
	@FindBy(xpath="//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a")
	public WebElement contactName;
	
	//tc-26
	
	@FindBy(xpath="//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")
	public WebElement createNew;	
	
	@FindBy(xpath="//*[@id=\"fname\"]")
	public WebElement viewName;
	
	@FindBy(xpath="//*[@id=\"devname\"]")
	public WebElement viewUniName;
	
	@FindBy(xpath="//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]")
	public WebElement saveSubmit;
	
	
	@FindBy(xpath="//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[2]")
	public WebElement cancelSubmit;
	
	@FindBy(xpath="//*[@id=\"con4_lkwgt\"]//*[@id=\"con4_lkid\"]")
	public WebElement accountDropDown;
	
	@FindBy(xpath="//*[@id=\"editPage\"]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]")
	public WebElement errormessage;
	
	@FindBy(xpath="//*[@id=\"fcf\"]")
	public WebElement viewselection;
	//*[@id="fcf"]
}