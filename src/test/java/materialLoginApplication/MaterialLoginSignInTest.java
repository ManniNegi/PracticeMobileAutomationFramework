package materialLoginApplication;

import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import logger.Log;

import org.testng.AssertJUnit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import setup.SetupAndroidTest;
import utilities.PropertyFile;

public class MaterialLoginSignInTest extends SetupAndroidTest {

	
	//private static final String ANDROID_DEVICE_NAME = "EUVCHUJVY9EU4DEA";
	private static final String ANDROID_DEVICE_NAME = "emulator-5554";
	private static final String MATERIAL_LOGIN_APK = "/MaterialLoginExample.apk";
	//private static final String MATERIAL_LOGIN_APK = "/VyondApplication.apk";
	private static final String APPIUM_AUTOMATION_NAME = "UIAutomator2";
	private static final String NEW_COMMAND_TIMEOUT = "60";
	public String email;
	public String password;
	public String propertyFile = "/src/main/java/testData/android.properties";

	/*@DataProvider(name = "testDataForSignInTest")
	public Object[][] getDataForSignInTest() {
		return new Object[][] { { "abcd@gmail.com", "abcdefghi" }, };

	}*/

	@BeforeSuite
	public void setupDriver() throws MalformedURLException {
		driver = setupDriver(ANDROID_DEVICE_NAME, MATERIAL_LOGIN_APK, APPIUM_AUTOMATION_NAME, NEW_COMMAND_TIMEOUT);
	}

	//@Test(enabled = true, groups = { "signin", "critical" }, dataProvider = "testDataForSignInTest")

	//public void signInTest(String email, String password) throws InterruptedException {
	@Test(testName ="signInTest")
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

	}
	
	 testScreenshot() {
		
	}
	
	/*@BeforeSuite
	public void setupDriver() throws MalformedURLException {
		driver = setupDriver(ANDROID_DEVICE_NAME, MATERIAL_LOGIN_APK, APPIUM_AUTOMATION_NAME, NEW_COMMAND_TIMEOUT);
	}
	
	@Test
	public void SampleTest() {
		SignInPage signInPage = new SignInPage(driver); // create object
		
		signInPage.loginUser();
		
	}*/
	
}
