package materialLoginApplication;


import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import logger.Log;
import pageObjects.SettingsPage;
import setup.SetupAndroidTest;

public class NavigateSettingsPage extends SetupAndroidTest{
	
	
	@Test(testName ="openSettingsPage")
	public void openSettingsPage() throws InterruptedException {
		AndroidDriver<MobileElement> driver = null;
		SettingsPage settingsPage = new SettingsPage(driver);
		Thread.sleep(5000);
		// open settings page
		Log.info("Test to open setting page started");
		settingsPage.openSettings();
		Thread.sleep(5000);
		Log.info("Test to open setting page completed successfully");
		
	} 
}
