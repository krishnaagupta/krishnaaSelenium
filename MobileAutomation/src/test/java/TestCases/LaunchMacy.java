package TestCases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class LaunchMacy {
	public static void main(String args[]) throws MalformedURLException, InterruptedException{
	AppiumDriver<MobileElement> driver;
	URL url=new URL("http://0.0.0.0:4723/wd/hub");
	DesiredCapabilities dc=new DesiredCapabilities();
	dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.macys.android");
	dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.macys.main.ui.activity.MainActivity");
	dc.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, "Android");
	dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
	dc.setCapability(MobileCapabilityType.DEVICE_NAME, "LGL322DL79e6f2d4");
	dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,true);


	//dc.setCapability("unlockPin",2404);
	driver = new AndroidDriver<MobileElement>(url,dc);

	//com.kohls.mcommerce.opal:id/id_accept
	
	Thread.sleep(5000);
	System.out.println("App Launched");
	//MobileElement agree = driver.findElementById("com.kohls.mcommerce.opal:id/id_accept");
	//String a = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.Button";
	//MobileElement agree = driver.findElementByXPath(a);
	//agree.click();
	//*[@id="screenshotContainer"]/div/div/div/div/div/div[23]
	
	}
}