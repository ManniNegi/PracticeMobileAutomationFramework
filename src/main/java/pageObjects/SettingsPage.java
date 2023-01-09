package pageObjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class SettingsPage extends BaseClass{
	
	public SettingsPage(AndroidDriver<MobileElement> driver) {
		super(driver);	
	}

	// define locators
	//@AndroidFindBy(id="More options")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"More options\"]")
	private MobileElement mobileButton;
	
	@AndroidFindBy(id="com.sourcey.materialloginexample:id/title")
	private MobileElement settingsOption;
	
	// to open settings page
	public void openSettings() throws InterruptedException {
		Thread.sleep(5000);
		this.mobileButton.click();
		
		this.settingsOption.click();
		
	}

}
