package Pages;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Ramdom {

	public Ramdom(WebDriver driver) {

			if (driver!=null) {
			PageFactory.initElements(driver,this);
			}
	}	
	//TC-10
	@FindBy(xpath="//*[@id=\"home_Tab\"]/a")
	public  WebElement homeTab;

	@FindBy(xpath="//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a")
	public  WebElement firstLastName;

	@FindBy(xpath="//*[@id=\"AllTab_Tab\"]/a/img")
	public  WebElement plusMark;
	
	@FindBy(xpath="//*[@id=\"bodyCell\"]/div[3]/div[1]/table/tbody/tr/td[2]/input")
	public  WebElement customizeTab;

	@FindBy(id="duel_select_1")
	public  WebElement selection;

	@FindBy(xpath="//*[@id=\"duel_select_0_left\"]/img")
	public  WebElement remove;
	
	
	@FindBy(xpath="//*[@id=\"bottomButtonRow\"]/input[1]")
	public  WebElement save;
	
	@FindBy(xpath="//*[@id=\"tabBar\"]")
	public  WebElement tabMenu;
	//@FindBy(xpath="//*[@id=\"ptBody\"]/div/div[1]/input1")
	//public  WebElement CheckFirstLastName1;
	
	@FindBy(xpath="//*[@id=\"tailBreadcrumbNode\"]")
	public  WebElement CheckFirstLastName;
	
	@FindBy(xpath="//*[@id=\"userNav-menuItems\"]/a[1]")
	public  WebElement myProfile;
	
	@FindBy(xpath="//*[@id=\"contactTab\"]/a")
	public  WebElement contactTab;
	
	
	
}
