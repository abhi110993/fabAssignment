package com.fabHotels.actions;

import java.util.ArrayList;

import com.fabHotels.pageObjects.BaseActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

@SuppressWarnings("rawtypes")
public class HomePageActions extends BaseActions{
	
	AndroidDriver driver;
	public HomePageActions(AndroidDriver driver) {
		super("HomePage",driver);
		this.driver = driver;
	}
	
	public boolean clickOnCityImage(String city) {
		ArrayList<AndroidElement> ele = (ArrayList<AndroidElement>) elements("img_city");
		for(AndroidElement element: ele) {
			logMessage("Element: "+element.getText());
			if(element.getText().contains(city)) {
				element.click();
				return true;
			}
		}
		return false;
	}
	
	public boolean verifyCustomerContactIcon() {
		return element("icon_customerCareCall").isDisplayed();
	}
	
	public void clickOnCustomerCareCallIcon() {
		clickOn("icon_customerCareCall","User clicks on customer care call icon");
	}
	
	public String getDialerTextPresent() {
		return element("editText_dialer").getText();
	}
	
	public void searchTerm(String searchItem) {
		clickOn("input_searchField","User clicks on search Field");
		enterText(element("input_searchFieldOnSearchScreen"), searchItem);
	}
	
	public boolean verifyUserNavigatedToSearchFormScreen() {
		return element("img_navigation").isDisplayed();
	}
}
