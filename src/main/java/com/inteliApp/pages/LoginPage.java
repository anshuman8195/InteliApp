package com.inteliApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.inteliApp.genericLib.BaseTest;

public class LoginPage extends BaseTest {

	@FindBy(xpath = "//input[@id='email']") // for email adress
	private WebElement unTB;

	@FindBy(xpath = "//input[@id='firstname']") // for firstname
	private WebElement fnTB;

	@FindBy(xpath = "//input[@id='lastname']") // for lastname
	private WebElement lnTB;

	@FindBy(xpath = "//span[@class='jss450']") // for signin
	private WebElement loginBtn;

	public void signin(String un, String fn, String ln) {
		unTB.sendKeys(un);
		fnTB.sendKeys(fn);
		lnTB.sendKeys(ln);
		loginBtn.click();

	}

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

}
