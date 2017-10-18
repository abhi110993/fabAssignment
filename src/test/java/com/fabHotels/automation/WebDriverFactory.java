package com.fabHotels.automation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

@SuppressWarnings("rawtypes")
public class WebDriverFactory {

	private static URL url;
	private static final DesiredCapabilities capabilities = new DesiredCapabilities();
	static String downloadFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
			+ File.separator + "resources" + File.separator + "Downloads";

	public AndroidDriver getAndroidDriver(Map<String, String> seleniumconfig) {
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");//
		capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
		capabilities.setCapability(MobileCapabilityType.SUPPORTS_JAVASCRIPT, true);
		capabilities.setCapability("androidPackage", "com.fabhotels.guests");
		capabilities.setCapability("appActivity", "app.fabhotels.MainActivity");
		// capabilities.setCapability(MobileCapabilityType.ROTATABLE, true);
		// capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
		// "6.0.1");
		// capabilities.setCapability(MobileCapabilityType.ORIENTATION,
		// "LANDSCAPE");
		// capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,
		// 60);
		capabilities.setCapability(MobileCapabilityType.APP,
				new File(seleniumconfig.get("app_file_path")).getAbsolutePath());
		capabilities.setJavascriptEnabled(true);
		try {
			url = new URL(seleniumconfig.get("appiumServer"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new AndroidDriver(url, capabilities);
	}
}
