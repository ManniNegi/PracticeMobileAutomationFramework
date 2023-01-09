package utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import logger.Log;
import pageObjects.BaseClass;

public class CommonMethods {
	public CommonMethods(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
	}


	protected AndroidDriver<MobileElement> driver;
	// common timeout for all tests can be set here
    public final int timeOut = 40;
	
	
	/**
     * method verify whether element is present on screen
     *
     * @param targetElement element to be present
     * @return true if element is present else throws exception
     * @throws InterruptedException Thrown when a thread is waiting, sleeping,
     *                              or otherwise occupied, and the thread is interrupted, either before
     *                              or during the activity.
     */
    public Boolean isElementPresent(By targetElement) throws InterruptedException {
        Boolean isPresent = driver.findElements(targetElement).size() > 0;
        return isPresent;
    }
    
    /**
     * method to hide keyboard
     */
    public void hideKeyboard() {
        ((AppiumDriver) driver).hideKeyboard();
    }

    /**
     * method to go back by Android Native back click
     */
    public void back() {
        ((AndroidDriver) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    /**
     * method to wait for an element to be visible
     *
     * @param targetElement element to be visible
     * @return true if element is visible else throws TimeoutException
     */
    public boolean waitForVisibility(By targetElement) {
        try {
            long timeOut = 0;
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOfElementLocated(targetElement));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is not visible: " + targetElement);
            throw e;

        }
    }
    
    /**
     * method to wait for an element until it is invisible
     *
     * @param targetElement element to be invisible
     * @return true if element gets invisible else throws TimeoutException
     */
    public boolean waitForInvisibility(By targetElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(targetElement));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is still visible: " + targetElement);
            System.out.println();
            System.out.println(e.getMessage());
            throw e;

        }
    }
    
    /**
     * method to verify if alert is present
     *
     * @return returns true if alert is present else false
     */
    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            throw e;
        }
    }
    
    /**
    * method to Accept Alert if alert is present
    */

   public void acceptAlert() {
       WebDriverWait wait = new WebDriverWait(driver, timeOut);
       wait.until(ExpectedConditions.alertIsPresent());
       driver.switchTo().alert().accept();
   }

   /**
    * method to Dismiss Alert if alert is present
    */

   public void dismissAlert() {
       WebDriverWait wait = new WebDriverWait(driver, timeOut);
       wait.until(ExpectedConditions.alertIsPresent());
       driver.switchTo().alert().dismiss();
   }
   
   /**
    * method to long press on specific element by passing locator
    *
    * @param locator element to be long pressed
    */
   public void longPress(By locator) {
       try {
           WebElement element = driver.findElement(locator);

           TouchAction touch = new TouchAction((MobileDriver) driver);
           LongPressOptions longPressOptions = new LongPressOptions();
           longPressOptions.withElement(ElementOption.element(element));
           touch.longPress(longPressOptions).release().perform();
           //Log.info("Long press successful on element: " + element);
       } catch (NoSuchElementException e) {
           //Log.logError(this.getClass().getName(), "findElement", "Element not found" + locator);
           throw e;
       }

   }
   
   /**
    * method to launchApp
    */

   public void launchApp() {
       ((AppiumDriver) driver).launchApp();
   }
   
   /**
    * method to scroll down on screen from java-client 6
    *
    * @param swipeTimes       number of times swipe operation should work
    * @param durationForSwipe time duration of a swipe operation
    */
   public void scrollDown(int swipeTimes, int durationForSwipe) { 
       Dimension dimension = driver.manage().window().getSize();

       for (int i = 0; i <= swipeTimes; i++) {
           int start = (int) (dimension.getHeight() * 0.5);
           int end = (int) (dimension.getHeight() * 0.3);
           int x = (int) (dimension.getWidth() * .5);


           new TouchAction((AppiumDriver) driver).press(PointOption.point(x, start)).moveTo(PointOption.point(x, end))
                   .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe)))
                   .release().perform();
       }
   }


   /**
    * method to scroll up on screen from java-client 6
    *
    * @param swipeTimes       number of times swipe operation should work
    * @param durationForSwipe time duration of a swipe operation
    */
   public void scrollUp(int swipeTimes, int durationForSwipe) {
       Dimension dimension = driver.manage().window().getSize();

       for (int i = 0; i <= swipeTimes; i++) {
           int start = (int) (dimension.getHeight() * 0.3);
           int end = (int) (dimension.getHeight() * 0.5);
           int x = (int) (dimension.getWidth() * .5);


           new TouchAction((AppiumDriver) driver).press(PointOption.point(x, start)).moveTo(PointOption.point(x, end))
                   .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe)))
                   .release().perform();
       }
   } 
   
         
        
    
}
