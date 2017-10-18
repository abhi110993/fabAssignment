package com.fabHotels.pageObjects;

import static com.fabHotels.pageObjects.ObjectFileReader.getELementFromFile;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

@SuppressWarnings("rawtypes")
public class BaseActions {
	
	AndroidDriver driver;
	String page;
	
	public BaseActions(String page,AndroidDriver driver) {
		this.driver = driver;
		this.page = page; 
	}
	
	protected AndroidElement element(String elementToken, String... replacements) {
		AndroidElement elem = null;
		elem = (AndroidElement) driver.findElement(getLocator(elementToken, replacements));
		return elem;
	}

	@SuppressWarnings("unchecked")
	protected ArrayList<AndroidElement> elements(String elementToken, String... replacement) {
		return (ArrayList<AndroidElement>) driver.findElements(getLocator(elementToken, replacement));
	}
	
	protected By getLocator(String elementToken, String... replacements) {
		String[] locator = getELementFromFile(this.page, elementToken);
		char[] c = locator[2].toCharArray();
		locator[2] = "";
		int j = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] != '$') {
				locator[2] += c[i];
			} else {
				locator[2] += replacements[j++];
				while (c[i] != '}')
					i++;
			}
		}
		return getBy(locator[1].trim(), locator[2].trim());
	}
	
	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case css:
			return By.cssSelector(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		case linktext:
			return By.linkText(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}
	
	public void clickOn(String locators, String msg) {
		element(locators).click();
		logMessage("[INFO]: "+msg);
		waitForPageToLoadCompletely();
	}
	
	public void logMessage(String msg) {
		Reporter.log(msg, true);
	}
	
	public void swipeHorizontal(AndroidElement e, double xStartPer,double xEndPer,double hPer) {
		//Dimension size = driver.manage().window().getSize();
		Dimension size = e.getSize();
	    int start = (int) ((size.height * xStartPer));
	    int end = (int) ((size.width * xEndPer));
	    int height = (int) ((size.width * hPer));
	    new TouchAction(driver).press(start,height).waitAction().moveTo(end,height).release().perform();
		syncWait(1);
	}
	
	public void swipeVertical(AndroidElement e, double yStartPer,double yEndPer,double xPer) {
		Dimension size = e.getSize();
	    int start = (int) ((size.height * yStartPer));
	    int end = (int) ((size.width * yEndPer));
	    int x = (int) ((size.width * xPer));
	    new TouchAction(driver).press(x, start).waitAction().moveTo(x,end).release().perform();
		syncWait(1);
	    logMessage("User swipped up");
	}
	
	public void swipeRightToLeft(AndroidElement e) {
		swipeHorizontal(e,1.7,0.05,1.5);
		logMessage("User swipped from right to left");
	}
	
	public void swipeBottomToTop(AndroidElement e) {
		swipeVertical(e,0.9,.05,.9);
		logMessage("User swipped from bottom to up");
	}
	
	public void syncWait(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {}
	}
	
	protected void executeJavascript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}
	
	public void waitForPageToLoadCompletely() {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*")));
    }
	
	public void enterText(AndroidElement ele, String textToBeEnetered,String msg) {
		ele.sendKeys(textToBeEnetered);
		hideKeyboard();
		logMessage(msg);
	}
	
	public void enterText(AndroidElement ele, String textToBeEnetered) {
		//ele.click();
		ele.sendKeys(textToBeEnetered);
		hideKeyboard();
	}
	
	public void hideKeyboard() {
		try {
			driver.hideKeyboard();
		}catch(WebDriverException e) {}
	}
}
