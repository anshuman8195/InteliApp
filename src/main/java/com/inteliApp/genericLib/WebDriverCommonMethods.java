package com.inteliApp.genericLib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class WebDriverCommonMethods extends BaseTest {
	public void mouseHover(WebElement element) {

		Actions ac = new Actions(driver);
		ac.moveToElement(element).perform();

	}

	public void dragAndDrop(WebElement source, WebElement target) {

		Actions ac = new Actions(driver);
		ac.dragAndDrop(source, target).perform();

	}

	public void select(WebElement element, int index) {

		Select sel = new Select(element);
		sel.selectByIndex(index);

	}

	public void select(WebElement element, String value) {

		Select sel = new Select(element);
		sel.selectByValue(value);

	}

	public void select(String text, WebElement element) {

		Select sel = new Select(element);
		sel.selectByVisibleText(text);

	}

	public void waitForPageLoad() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public void waitForElement(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForTitle(String title) {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(title));
	}

	public String getTitle() {
		return driver.getTitle();

	}

	public void verify(String actual, String expected, String message) {

		Assert.assertEquals(actual, expected);
		Reporter.log(message + " Verification pass", true);
	}

	public void verifyNegative(String actual, String expected, String message) {

		Assert.assertNotEquals(actual, expected);
		Reporter.log(message + " Verification pass", true);
	}

	public void maximize() {
		driver.manage().window().maximize();
	}

	public void clickandhold(WebElement element) {

		Actions ac = new Actions(driver);
		ac.clickAndHold().moveToElement(element).perform();
	}

}
