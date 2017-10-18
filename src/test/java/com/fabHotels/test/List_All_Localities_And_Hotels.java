package com.fabHotels.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fabHotels.automation.TestSessionInitiator;

public class List_All_Localities_And_Hotels {
	
	TestSessionInitiator test;
	
	@BeforeClass
	public void initiateApplication() {
		test = new TestSessionInitiator();
	}

	@Test
	public void Launch_Application_And_Skip_OTP_Verification_Test() {
		test.loginPage.verifySkipButtonPresence();
		test.loginPage.clickOnSkipButton();
	}
	
	@Test(dependsOnMethods="Launch_Application_And_Skip_OTP_Verification_Test")
	public void Tap_On_New_Delhi_Icon_Present_On_HomeScreen() {
		Assert.assertTrue(test.homePage.clickOnCityImage("New Delhi"),"Assertion Failed: Unable to click on the City Image");
	}
	
	@Test(dependsOnMethods="Tap_On_New_Delhi_Icon_Present_On_HomeScreen")
	public void List_Number_Of_Hotels_In_Each_Localities() {
		test.listingPage.getAllHotelsWithinLocalities();
	}
	
	@AfterClass
	public void closeApplication() {
		test.closeSession();
	}
}
