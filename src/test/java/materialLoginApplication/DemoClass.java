package materialLoginApplication;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import logger.Log;
import pageObjects.HomePage;
import pageObjects.SettingsPage;
import pageObjects.SignInPage;
import setup.SetupAndroidTest;
import utilities.CaptureScreenshot;
import utilities.PropertyFile;

public class DemoClass extends SetupAndroidTest{
	private static final String ANDROID_DEVICE_NAME = "emulator-5554";
	private static final String MATERIAL_LOGIN_APK = "/MaterialLoginExample.apk";
	private static final String APPIUM_AUTOMATION_NAME = "UIAutomator2";
	private static final String NEW_COMMAND_TIMEOUT = "60";
	public String email;
	public String password;
	public String propertyFile = "/src/main/java/testData/android.properties";

	@Test(priority = 0)
	public void setupDriver() throws MalformedURLException {
		driver = setupDriver(ANDROID_DEVICE_NAME, MATERIAL_LOGIN_APK, APPIUM_AUTOMATION_NAME, NEW_COMMAND_TIMEOUT);
	}
	
	@Test(testName ="signInTest", priority =1)
	public void signInTest() throws InterruptedException, IOException {
		
		// read value from property file
		PropertyFile pf = new PropertyFile(); 
		email = pf.readPropertiesFile(propertyFile, "userName");
	    password = pf.readPropertiesFile(propertyFile, "password");
	    
		SignInPage signInPage = new SignInPage(driver); // create object
		// enter credentials and complete sign up , verify also
		signInPage.enterUserName(email);
		signInPage.enterPassword(password);
		signInPage.clickSubmitButton();

		// validation that home page is displayed after sign in.
		HomePage homePage = new HomePage(driver);
		Assert.assertTrue(homePage.isInitialized());
		//Assert.assertFalse(homePage.isInitialized());
		
		ssTest();

	}
	
	public void ssTest() {
	//CaptureScreenshot cs = new CaptureScreenshot(null);
	//cs.captureScreenShot("1.png");
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(0));
		 String destination = System.getProperty("user.dir") + "/screenshots/" + "1.png";
		 
		 TakesScreenshot takeScreenshot =  ((TakesScreenshot)this.driver);
		  File srcFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
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
	
/*	@Test(testName ="openSettingsPage", priority =2)
	public void openSettingsPage() throws InterruptedException {
		AndroidDriver<MobileElement> driver = null;
		SettingsPage settingsPage = new SettingsPage(driver);
		Thread.sleep(5000);
		// open settings page
		Log.info("Test to open setting page started");
		settingsPage.openSettings();
		Thread.sleep(5000);
		Log.info("Test to open setting page completed successfully");
		
	} */
}
