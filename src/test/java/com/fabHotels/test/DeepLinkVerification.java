package com.fabHotels.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.fabHotels.utils.HtmlReader;
import com.fabHotels.utils.HttpClient;

public class DeepLinkVerification {
	
	@Test
	public void Verify_DeepLink_Takes_User_For_New_Delhi_Search_Result() {
		String response = HttpClient.getHttpResponse("https://www.fabhotels.com/hotels-in-new-delhi");
		HtmlReader read = new HtmlReader(response);
		Assert.assertEquals(read.getElementByTagName("title"),"Budget Hotels in New Delhi, Online Budget Hotel Booking in New Delhi - FabHotels","Assertion Failed: User is not navigated to search result screen for New Delhi");
		Reporter.log("Assertion Passed: User is navigated to search result screen for New Delhi");
	}
}
