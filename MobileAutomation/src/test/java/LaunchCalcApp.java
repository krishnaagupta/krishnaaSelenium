

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class LaunchCalcApp {

public static void main(String args[]) throws MalformedURLException{
	AppiumDriver<MobileElement> driver;
	URL url=new URL("http://0.0.0.0:4723/wd/hub");
	DesiredCapabilities dc=new DesiredCapabilities();
	//dc.setCapability("appPackage ","com.google.android.calculator");
//	dc.setCapability("appActivity", "com.android.calculator2.Calculator");
//	dc.setCapability("platformName", "Andriod");
//	dc.setCapability("platformVersion", "9.0");
//	dc.setCapability("deviceName", "LGL322DL79e6f2d4");
	
	dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.calculator");
	dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");
	dc.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, "Android");
	dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
	dc.setCapability(MobileCapabilityType.DEVICE_NAME, "LGL322DL79e6f2d4");
	dc.setCapability("autoGrantPermissions",true);
	//dc.setCapability("unlockPin",2404);
	driver = new AndroidDriver<MobileElement>(url,dc);
	
	System.out.println("App Launched");
	//driver.findElementByXPath("//*[@id=\"selectedElementContainer\"]/div/div[2]/div/div[2]/div/div/div/div/div/div/table/tbody/tr[1]/td[2]/div").click();
	//click number 2
	driver.findElementById("com.google.android.calculator:id/digit_2").click();
	System.out.println("5 clicked");
	//click addition operation
		driver.findElementById("com.google.android.calculator:id/op_add").click();
	//click number 5
	driver.findElementById("com.google.android.calculator:id/digit_5").click();
	//click ==
	driver.findElementById("com.google.android.calculator:id/eq").click();
	
	
	
	
}
}
