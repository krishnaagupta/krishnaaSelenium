package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;


public class CreateAccount {

	public CreateAccount(WebDriver driver) {
		
			if (driver!=null) {
			PageFactory.initElements(driver,this);
			}
	}	// T// TODO Auto-generated constructor stub
	//TC-10
	@FindBy(xpath="//*[@id=\"Account_Tab\"]/a")
	public  WebElement accountClick;

	
	@FindBy(xpath="//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input")
	public  WebElement newClick;
	
	@FindBy(xpath="//*[@id=\"acc2\"]")
	public  WebElement accountName;
	
	@FindBy(xpath="//*[@id=\"bottomButtonRow\"]/input[1]")
	public  WebElement saveClick;
	
	
	@FindBy(xpath="//*[@id=\"contactHeaderRow\"]/div[2]/h2")
	public  WebElement checkAccountName;

//Tc-11
	@FindBy(xpath="//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")
	public  WebElement createNewView;

	@FindBy(xpath="//*[@id=\"fname\"]")
	public  WebElement viewName;

	@FindBy(xpath="//*[@id=\"devname\"]")
	public  WebElement viewUniquename;
	
	@FindBy(xpath="//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]")
	public  WebElement clickSave;
	
	@FindBy(xpath="//*[@id=\"fcf\"]/option")
	public  WebElement displayView;
// tc-12
	@FindBy(xpath="//*[@id=\"fcf\"]/option[1]")
	public  WebElement dropDownClick;
	
	@FindBy(xpath="//*[@id=\"filter_element\"]/div/span/span[2]/a[1]")
	public  WebElement editClick;
	
	@FindBy(id="fname")
	public  WebElement viewName1;

	@FindBy(xpath="//*[@id=\"fcol1\"]")
	public  WebElement accountName1;
		
	@FindBy(xpath="//*[@id=\"fop1\"]")
	public  WebElement opertaor;	
		
	@FindBy(xpath="//*[@id=\"fval1\"]")
	public  WebElement value;	
	
	@FindBy(xpath="//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]")
	public  WebElement save1;	
//tc-14
	
	@FindBy(xpath="//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a")
	public  WebElement AccountreportClick;
	
	@FindBy(xpath="//*[@id=\"ext-gen152\"]")
	public  WebElement fromDate;	
	
	@FindBy(xpath="//*[@id=\"ext-comp-1111\"]")
	public  WebElement todayDate;	
	
	@FindBy(xpath="//*[@id=\"ext-gen154\"]")
	public  WebElement toDate;	
	
	@FindBy(xpath="//*[@id=\"ext-comp-1113\"]")
	public  WebElement today_Date;	
	
	@FindBy(xpath="//*[@id=\"ext-gen49\"]")
	public  WebElement saveButton;	
	
	@FindBy(xpath="//*[@id=\"saveReportDlg_reportNameField\"]")
	public  WebElement reportName;	
			
	@FindBy(xpath="//*[@id=\"saveReportDlg_DeveloperName\"]")
	public  WebElement reportUniqueName;	

	//@FindBy(xpath="//*[@id=\"ext-gen282\"]")
	@FindBy(xpath="//*[@id=\"ext-gen319\"]")
	//@FindBy(xpath="//*[@id=\"dlgSaveAndRun\"]")
	public  WebElement saveRunBtn;	

	//*[@id="dlgSaveAndRun"]
//tc-13
	
	@FindBy(xpath="//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a")
	public  WebElement mergeAccount;
	
	
	@FindBy(xpath="//*[@id=\"srch\"]")
	public  WebElement findAcctTest;

	@FindBy(xpath="//*[@id=\"stageForm\"]/div/div[2]/div[4]/input[2]")
	public  WebElement findAcctSubmit;
	
	@FindBy(xpath="//*[@id=\"stageForm\"]/div/div[2]/div[1]/div/input[1]")
	public  WebElement nextSubmit;
	
	
	@FindBy(xpath="//*[@id=\"stageForm\"]/div/div[2]/div[1]/div/input[2]")
	public  WebElement mergeSubmit;
	
	@FindBy(xpath="//*[@id=\"cid0\"]")
	public  WebElement datarRow1;

	@FindBy(xpath="//*[@id=\"cid1\"]")
	public  WebElement datarRow2;
	


	@FindBy(xpath="//*[@id=\"stageForm\"]/div/div[2]/div[1]/div/input[1]")
	public  WebElement nextSubmit1;
	
	@FindBy(xpath="//*[@id=\"stageForm\"]/div/div[2]/div[1]/div/input[2]")
	public  WebElement merge;
	
	@FindBy(xpath="//*[@id=\"00B5e000002ttlQ_listSelect\"]")
	public  WebElement recentlyViewed;
	
	@FindBy(xpath="//*[@id=\"filter_element\"]/div/span/span[1]/input")
	public  WebElement go;
	
}	





