package com.inteliApp.genericLib;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.google.common.io.Files;
import com.inteliApp.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest implements IAutoConsts {
	public  WebDriver driver;
	public FileLib fLib;
	LoginPage lp;
	public MyListeners ml;
	public ExtentReports extent;
	public ExtentTest extentTest;
	

	@BeforeClass
	public void openBrowser() throws Throwable {
		fLib = new FileLib();

		String browserValue = fLib.getPropKeyValue(PROP_PATH, "browser");

		if (browserValue.equalsIgnoreCase("chrome")) {

			System.setProperty(CHROME_KEY, CHROME_VALUE);
			driver = new ChromeDriver();

		} else if (browserValue.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
			System.setProperty(GECKO_KEY, GECKO_VALUE);

		} else {
			Reporter.log("Enter the valid browser", true);
		}

		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get(fLib.getPropKeyValue(PROP_PATH, "url"));
		

	}
	@BeforeTest
	public void setExtent()
	{
		extent=new ExtentReports(System.getProperty("user.dir")+"/test-output/MyExtentReports.html",true);
		extent.addSystemInfo("HostName", "Ranjit-Window");
		extent.addSystemInfo("UserName", "Ranjit");
		extent.addSystemInfo("Environment", "Automation-QA");
		
		
	}
	@AfterTest
	public void endReports()
	{
		extent.flush();
		extent.close();
	}
	public static String getScreenshot(WebDriver driver,String screenshotName) throws Exception
	{
		String dateName=new SimpleDateFormat("dd-mm-yyyy").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destFile = System.getProperty("user.dir")+"/failedTestsScreenshots/"+screenshotName+dateName+".png";
		File destination = new File(destFile);
		FileUtils.copyFile(source,destination);
		return destFile;
		
	}
	

	@BeforeMethod
	public void loginToApp() throws Throwable {
		fLib = new FileLib();
//		lp = new LoginPage();
//		Thread.sleep(2000);

		String un = fLib.getPropKeyValue(PROP_PATH, "EmailAddress");

		String fn = fLib.getPropKeyValue(PROP_PATH, "FirstName");

		String ln = fLib.getPropKeyValue(PROP_PATH, "LastName");
		System.out.println(un);
		System.out.println(fn);
		System.out.println(ln);
//		lp =new LoginPage();
        lp.signin(un, fn, ln);
       

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception
	{
	if(result.getStatus()==ITestResult.FAILURE)
	{
	  extentTest.log(LogStatus.FAIL,"Test Case failed"+result.getName());// to add name in extent report
	  extentTest.log(LogStatus.FAIL,"Test Case failed"+result.getThrowable());// to add error and exception in extent report
	  String screenshotPath=BaseTest.getScreenshot(driver, result.getName());
	  extentTest.log(LogStatus.FAIL,extentTest.addScreencast(screenshotPath));// add screen shot in extent report
	  
	  
	}
	else if(result.getStatus()==ITestResult.SKIP)
	{
		 extentTest.log(LogStatus.SKIP,"Test Case skipped"+result.getName());// to add name in extent report
		  extentTest.log(LogStatus.SKIP,"Test Case skipped"+result.getThrowable());// to add error and exception in extent report
		  String screenshotPath=BaseTest.getScreenshot(driver, result.getName());
		  extentTest.log(LogStatus.SKIP,extentTest.addScreencast(screenshotPath));// add screen shot in extent report
	}
	extent.endTest(extentTest);// ending test
	driver.quit();
	}

//	@AfterClass
//	public void tearDown() {
//		driver.quit();
//		System.out.println("test ends here");
//	}

}