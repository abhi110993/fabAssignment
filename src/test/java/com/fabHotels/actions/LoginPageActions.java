package com.fabHotels.actions;

import org.testng.Assert;
import org.testng.Reporter;

import com.fabHotels.pageObjects.BaseActions;

import io.appium.java_client.android.AndroidDriver;

@SuppressWarnings("rawtypes")
public class LoginPageActions extends BaseActions{
	
	AndroidDriver driver;
	public LoginPageActions(AndroidDriver driver) {
		super("LoginPage",driver);
		this.driver = driver;
	}
	
	public void verifySkipButtonPresence() {
		Assert.assertEquals(element("btn_skip").getText(),"Skip","Assertion Failed: 'Skip' button is not displayed correctly");
		Reporter.log("Assertion Passed: 'Skip' button displayed correctly", true);
	}
	
	public void clickOnSkipButton() {
		clickOn("btn_skip", "User clicks on 'Skip' button");
	}

}
