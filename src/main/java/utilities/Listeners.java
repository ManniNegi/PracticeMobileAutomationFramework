package utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pageObjects.BaseClass;


public class Listeners implements ITestListener {
	

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log(" Method name is -" + result.getName());
		System.out.println("Test execution starts for " + result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("Status of result" + result.getStatus());
		System.out.println("Test execution completed successfully for " + result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("Status of test result" + result.getStatus());
		System.out.println("Testcase failed for " + result.getMethod().getMethodName());
		CaptureScreenshot bc = new CaptureScreenshot();
		bc.captureScreenShot(result.getMethod().getMethodName());
	}
	
	@Override
    public void onTestSkipped(ITestResult result) {
		Reporter.log(" Method name is skipped" + result.getName());
		System.out.println("Test execution skipped");
		
	}
	
}

