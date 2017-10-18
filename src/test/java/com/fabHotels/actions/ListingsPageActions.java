package com.fabHotels.actions;

import java.util.ArrayList;
import java.util.HashSet;

import com.fabHotels.pageObjects.BaseActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

@SuppressWarnings("rawtypes")
public class ListingsPageActions extends BaseActions{
	
	AndroidDriver driver;
	public ListingsPageActions(AndroidDriver driver) {
		super("ListingsPage",driver);
		this.driver = driver;
	}
	
	public void getAllHotelsWithinLocalities() {
		waitForPageToLoadCompletely();
		swipeRightToLeft(element("img_property"));
		waitForPageToLoadCompletely();
		ArrayList<String> locality = new ArrayList<>();
		String elementText="";
		while(!locality.contains(elementText)) {
			 locality.add(elementText);
			 HashSet<String> hotels = new HashSet<>();
			 while(!hotels.contains(elements("list_hotelName").get(elements("list_hotelName").size()-1).getText())) {
				 for(AndroidElement e : elements("list_hotelName"))
					 hotels.add(e.getText());
				 swipeBottomToTop(element("view_hotels"));
			 }
			 for(String hotel: hotels)
				 logMessage("Hotel under '"+element("tab_selected").getText()+"' locality is:" +hotel);
			 swipeRightToLeft(element("img_property"));
			 waitForPageToLoadCompletely();
			 elementText = element("tab_selected").getText();
		}
	}
	
	public void clickOnTheFirstHotelPresentIntheList() {
		clickOn("list_hotelName","User clicks on 'First' hotel present in the list");
	}

}
