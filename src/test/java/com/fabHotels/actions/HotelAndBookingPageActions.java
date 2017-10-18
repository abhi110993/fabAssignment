package com.fabHotels.actions;

import com.fabHotels.pageObjects.BaseActions;

import io.appium.java_client.android.AndroidDriver;

@SuppressWarnings("rawtypes")
public class HotelAndBookingPageActions extends BaseActions{
	
	AndroidDriver driver;
	public HotelAndBookingPageActions(AndroidDriver driver) {
		super("HAndBPage",driver);
		this.driver = driver;
	}
	
	public void clickOnSelectDatesButton() {
		clickOn("btn_selectDate", "User clicks on 'Select date' button");
	}
	
	public void clickOnDoneButton() {
		clickOn("btn_doneDateSelection", "User clicks on 'Done' button after selecting date");
	}
	
	public void clickOnBookNowButton() {
		clickOn("btn_bookNow", "User clicks on 'Book Now' button");
	}
	
	public void applyCoupons(String couponID) {
		swipeBottomToTop(element("layout_linear"));
		enterText(element("input_coupon"), couponID,"User enters '"+couponID+"' into coupon text field");
	}
	
	public void clickOnApplyButton() {
		clickOn("btn_apply", "User clicks on 'Apply' button");
	}
	
	public String getCouponAppliedMessage() {
		return element("txt_couponApplied").getText();
	}
	
	public void clickOnContinueButton() {
		clickOn("btn_continue", "User clicks on 'Continue' button");
	}
	
	public void  enterNecessaryDetail() {
		enterText(element("input_fullName"), "test lastname");
		enterText(element("input_email"), "test@testfabHotel.com");
		enterText(element("input_mobileNo"), "9953270255");
		clickOn("btn_proceedToPay","User clicks on 'Proceed To Pay' after filling the necessary details");
	}
	
	public void clickOnRemoveCouponAndPayAtHotelButton() {
		swipeBottomToTop(element("layout_Frame"));
		clickOn("btn_removeCouponAndPayAtHotel", "User clicks on 'Remove Coupon and Pay At Hotel' button");
	}
	
	public String getCouponMessageForOTP() {
		clickOn("btn_allowMessage", "User clicks on 'Allow' button to allow mobile to send message");
		return element("txt_optVerificationMsg").getText();
	}
}
