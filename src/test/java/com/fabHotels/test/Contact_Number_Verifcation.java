package com.fabHotels.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fabHotels.automation.TestSessionInitiator;

public class Contact_Number_Verifcation {
	
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
	public void Verify_Customer_Number_Contact_Icon_On_Right_Top_Corner() {
		Assert.assertTrue(test.homePage.verifyCustomerContactIcon(), "Assertion Failed: Customer Care call icon is not displayed");
		Reporter.log("Assertion Passed: Customer care call icon is present on home screen", true);
	}
	
	@Test(dependsOnMethods="Verify_Customer_Number_Contact_Icon_On_Right_Top_Corner")
	public void Verify_Customer_Care_Number_Comes_Pre_Filled_On_Dialer_Screen() {
		test.homePage.clickOnCustomerCareCallIcon();
		Assert.assertEquals(test.homePage.getDialerTextPresent(), "+91 70424 24242","Assertion Failed: Customer care number is not correct");
		Reporter.log("Assertion Passed: Customer care number is displayed correctly");
	}
	
	@AfterClass
	public void closeApplication() {
		test.closeSession();
	}
}
