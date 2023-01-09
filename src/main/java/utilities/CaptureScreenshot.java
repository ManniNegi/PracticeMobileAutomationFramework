package utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObjects.BaseClass;

public  class CaptureScreenshot extends BaseClass{
	
	public CaptureScreenshot(AndroidDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


		//public static AndroidDriver<MobileElement> driver;
	     // function to capture screenshot.
		 public void captureScreenShot(String fileName){
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(0));
		 String destination = System.getProperty("user.dir") + "/screenshots/" +fileName+".png";
		 
		 TakesScreenshot takeScreenshot =  ((TakesScreenshot)this.driver);
		  File srcFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		 //File srcFile = driver.getScreenshotAs(OutputType.FILE);
		  File destFile = new File(destination);
		  try {
			  FileUtils.copyFile(srcFile, destFile);
		 
		  System.out.println("Screenshot saved successfully at " + System.currentTimeMillis());
		 }
		 catch (Exception e) 
		    {
		    System.out.println("Exception while taking screenshot "+e.getMessage());
		    }
		 
		 }
			//Take the screenshot
			 
				/*File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				 

				//Copy the file to a location and use try catch block to handle exception
				 
				try {
				 
				FileUtils.copyFile(screenshot, new File("\\Users\\poojabhandari\\Desktop\\FAILEDSCREENSHOT\\homePageScreenshot.png"));
				 
				} catch (IOException e) {
				 
				System.out.println(e.getMessage());
				 
				}
	
}*/
}