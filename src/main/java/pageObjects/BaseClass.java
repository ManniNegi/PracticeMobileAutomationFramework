package pageObjects;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass {
	protected AndroidDriver<MobileElement> driver;
	public static URL url;
	public static DesiredCapabilities capabilities;

	// initialize driver;
	public BaseClass(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);

	}

	//@AfterMethod(alwaysRun = true)
    /*public void captureScreenShot(String fileName){
	 //String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(0));
	 String destination = System.getProperty("user.dir") + "/screenshots/" +fileName+".png";
	 
	  //TakesScreenshot takeScreenshot =  (TakesScreenshot)driver;

	  File srcFile = driver.getScreenshotAs(OutputType.FILE);
	  File destFile = new File(destination);
	  try {
		  Thread.sleep(5000);
		  FileUtils.copyFile(srcFile, destFile);
	 
	  System.out.println("Screenshot saved successfully at " + System.currentTimeMillis());
	 }
	 catch (Exception e) 
	    {
	    System.out.println("Exception while taking screenshot "+e.getMessage());
	    }
	 
	 }*/
	
	
}
