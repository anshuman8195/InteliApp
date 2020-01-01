package com.inteliApp.test;

import java.io.InputStreamReader;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inteliApp.genericLib.BaseTest;
import com.inteliApp.genericLib.FileLib;
import com.inteliApp.pages.LoginPage;
//@Listeners(com.inteliApp.genericLib.MyListeners.class)// wish to attach My listeners class with test
public class LoginTest extends BaseTest {
	FileLib flib;
	LoginPage lp;
	
	@Test
	public void loginTest() throws Throwable {
		flib=new FileLib();
		lp=new LoginPage();
		extentTest=extent.startTest("loginTest");
		
//		String un = flib.getPropKeyValue(PROP_PATH, "EmailAddress");
//		String fn = flib.getPropKeyValue(PROP_PATH, "FirstName ");
//		String ln = flib.getPropKeyValue(PROP_PATH, "LastName ");
//		
//		lp.signin(un, fn, ln);
//		lp.clickSignin();
		System.out.println("test cases executed");
		
	}

}
