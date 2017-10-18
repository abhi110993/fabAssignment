package com.fabHotels.automation;

import static com.fabHotels.utils.ConfigPropertyReader.getProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Reporter;

import com.fabHotels.actions.HomePageActions;
import com.fabHotels.actions.HotelAndBookingPageActions;
import com.fabHotels.actions.ListingsPageActions;
import com.fabHotels.actions.LoginPageActions;

import io.appium.java_client.android.AndroidDriver;

public class TestSessionInitiator {

	@SuppressWarnings("rawtypes")
	public AndroidDriver driver;
	private final WebDriverFactory wdfactory;
	public static Map<String, String> config;
	public LoginPageActions loginPage;
	public HomePageActions homePage;
	public ListingsPageActions listingPage;
	public HotelAndBookingPageActions hAndBPage;
	
	public void _initActions() {
		loginPage = new LoginPageActions(driver);
		homePage = new HomePageActions(driver);
		listingPage = new ListingsPageActions(driver);
		hAndBPage = new HotelAndBookingPageActions(driver);
	}
	
	public TestSessionInitiator() {
		wdfactory = new WebDriverFactory();
		_getSessionConfig();
		_configureDevice();
		_initActions();
	}

	private void _configureDevice() {
		driver = wdfactory.getAndroidDriver(config);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "appiumServer","app_file_path"};
		config = new HashMap<>();
		for (String string : configKeys) {
			if (System.getProperty(string) != null) {
				config.put(string, System.getProperty(string));
			} else {
				config.put(string, getProperty("./Config.properties", string));
				System.setProperty(string, getProperty("./Config.properties", string));
			}
		}
		return config;
	}

	public void launchApplication() {
		Reporter.log("The test device is :- " + config.get("device") + "\n",true);
		driver.launchApp();
	}
	
	public void closeSession() {
		driver.quit();
	}

	public void closeWindow() {
		driver.close();
	}
}
