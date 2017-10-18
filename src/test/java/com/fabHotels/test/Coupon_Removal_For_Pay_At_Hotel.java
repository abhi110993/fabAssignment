package com.fabHotels.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fabHotels.automation.TestSessionInitiator;

public class Coupon_Removal_For_Pay_At_Hotel {
	
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
	public void Navigate_To_Hotel_Screen_To_Book_A_Hotel() {
		test.homePage.clickOnCityImage("New Delhi");
		test.listingPage.clickOnTheFirstHotelPresentIntheList();
		test.hAndBPage.clickOnSelectDatesButton();
		test.hAndBPage.clickOnDoneButton();
		test.hAndBPage.clickOnBookNowButton();
	}
	
	@Test(dependsOnMethods="Navigate_To_Hotel_Screen_To_Book_A_Hotel")
	public void Verify_User_Is_Able_To_Apply_Coupon() {
		test.hAndBPage.applyCoupons("FABAPP25");
		test.hAndBPage.clickOnApplyButton();
		Assert.assertTrue(test.hAndBPage.getCouponAppliedMessage().contains("Fab! You just saved"), "Assertion Failed: Coupon wasn't applied successfully");
		Reporter.log("Assertion Passed: Coupon applied successfully",true);
	}
	
	@Test(dependsOnMethods="Verify_User_Is_Able_To_Apply_Coupon")
	public void Verify_Coupon_Removed_For_Pay_At_Hotel_Option() {
		test.hAndBPage.clickOnContinueButton();
		test.hAndBPage.enterNecessaryDetail();
		test.hAndBPage.clickOnRemoveCouponAndPayAtHotelButton();
		Assert.assertEquals(test.hAndBPage.getCouponMessageForOTP(),"A One Time Password (OTP) has been sent via SMS to +91-9953270255","Assertion Failed: The OTP message is incorrectly displayed");
		Reporter.log("Assertion Passed: The OTP message is correctly displayed",true);
	}
	
	@AfterClass
	public void closeApplication() {
		test.closeSession();
	}
}
