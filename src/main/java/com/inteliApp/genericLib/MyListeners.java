package com.inteliApp.genericLib;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.common.io.Files;

public class MyListeners extends BaseTest implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {

		Reporter.log(result.getName() + " Test Start", true);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log(result.getName() + " Test Finish", true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String failname = result.getName();
		Reporter.log(failname + " Test fail", true);

		TakesScreenshot ts = (TakesScreenshot) driver;
		File from = ts.getScreenshotAs(OutputType.FILE);
		File to = new File("./screenshot/" + failname + ".png");
		try {
			Files.copy(from, to);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log(result.getName() + " Test Skip", true);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		Reporter.log(context.getName() + " Test Start", true);

	}

	@Override
	public void onFinish(ITestContext context) {
		Reporter.log(context.getName() + " Test Finish", true);

	}

}
