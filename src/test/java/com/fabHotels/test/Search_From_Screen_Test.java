package com.fabHotels.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fabHotels.automation.TestSessionInitiator;

public class Search_From_Screen_Test {
	
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
	public void Verify_Search_Form_Screen_Opens_Up_When_User_Taps_On_Search_Text_Field() {
		test.homePage.searchTerm("New Delhi");
		Assert.assertTrue(test.homePage.verifyUserNavigatedToSearchFormScreen(),"Assertion Failed: User is not navigated to Search Form Screen");
		Reporter.log("Assertion Passed: user is navivated successfully to Search Form Screen", true);
	}	
	
	@AfterClass
	public void closeApplication() {
		test.closeSession();
	}
}
