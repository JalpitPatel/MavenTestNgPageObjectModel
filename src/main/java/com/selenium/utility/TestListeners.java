package com.selenium.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListeners extends Base implements ITestListener  {

	public void onStart(ITestContext context) {
		System.out.println("Start Method Called.");
	}

	public void onTestStart(ITestResult result) {
		System.out.println("Test is Started: "+result.getName());
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test is Success: "+result.getName());
		
	}
	public void onTestFailure(ITestResult result) {
		System.out.println("Test is Failed: "+result.getName());
		Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        if(!result.isSuccess()){
        	try {
        		File scrFile =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports/failure_screenshots";
                String time = formater.format(calendar.getTime());   
                File destFile = new File((String) reportDirectory+"/"+methodName+"_"+time+".png");
                FileUtils.copyFile(scrFile, destFile);
                Reporter.log("<br>----------Screenshot---------<br>");
                String path = "<img src=\"./failure_screenshots/" +methodName+"_"+time+".png" + "\" height=\"600\" width=\"800\">";                       
                System.setProperty("org.uncommons.reportng.escape-output", "false");                       
                Reporter.log(path);
               
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WebDriverException we) {
            	we.printStackTrace();
            }
      	}
	}
	
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test is skipped: "+result.getName());
		
	}
	
	public void onFinish(ITestContext context) {
		System.out.println("Test is Finished");
		
	}

}
