package com.web.webdriver_factory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.suite.commons.PropertyHolder;

public class WebDriverManager {

	private static String driverMode;
	private static String browser;
	private static String browserVersion;
	private static String platform;
	private static String hubUrl;
	private static String driverPropertyName;
	private static String driverExecutablePath;

	static {
		loadDesiredData();
	}

	synchronized public static WebDriver CreateInstance() throws MalformedURLException {
		WebDriver dr = null;
		if (driverMode.contains("BROWSER")) {
			System.setProperty(driverPropertyName, driverExecutablePath);
			try {
				if (browser.contains("chrome"))
					dr = new ChromeDriver();
				else if (browser.contains("firefox"))
					dr = new FirefoxDriver();
				else if (browser.contains("ie"))
					dr = new InternetExplorerDriver();
				else {
					System.out.println("!!!!!!!!!!  Browser Name is not found. Exiting");
					System.exit(-1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (driverMode.contains("REMOTE")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName(browser);
			cap.setVersion(browserVersion);
			cap.setCapability("platform", platform);

			dr = new RemoteWebDriver(new URL(hubUrl), cap);

		} else {
			System.out.println("!!!!!!!! Unable to create driver object");
			System.exit(-1);
		}

		System.out.println("******** In after object creation");
		dr.manage().window().maximize();
		return dr;
	}

	synchronized private static DesiredCapabilities getDerivedCapabilities() {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("chrome");
		/*
		 * if(System.getProperty("platform").contentEquals("web")){
		 * capabilities.setBrowserName(System.getProperty("browser")); //
		 * capabilities.setVersion(System.getProperty("version")); }else
		 * if(System.getProperty("platform").contentEquals("mobile")){
		 * capabilities.setCapability("BROWSER_NAME", "Android");
		 * capabilities.setCapability("VERSION", "4.2.2");
		 * capabilities.setCapability("deviceName", "Emulator");
		 * capabilities.setCapability("platformName", "Android");
		 * capabilities.setCapability("appPackage", "com.android.chrome");
		 * capabilities.setCapability("appActivity", "com.android.chrome"); }
		 */
		return capabilities;
	}

	private static void loadDesiredData() {

		if (null == System.getProperty("browser")) {
			loadConfigFromPropertyFile();
		} else {
			loadConfigFromSystemProperty();
		}

	}

	private static void loadConfigFromSystemProperty() {

		browser = System.getProperty("browser");
		browserVersion = System.getProperty("browserVersion");
		platform = System.getProperty("platform");
		hubUrl = System.getProperty("hubUrl");
		driverMode = "REMOTE";
	}

	private static void loadConfigFromPropertyFile() {
		if (null == PropertyHolder.webdriverConfig) {
			System.out.println(
					"!!!!!! Webdriver Object Creation failed. WebDriverConfig.properties does not load properly");
			System.exit(-1);
		}

		if (PropertyHolder.webdriverConfig.get("DRIVER").toString().contentEquals("BROWSER")) {
			browser = PropertyHolder.webdriverConfig.getProperty("browser");
			browserVersion = PropertyHolder.webdriverConfig.getProperty("browserVersion");
			platform = PropertyHolder.webdriverConfig.getProperty("platform");
			driverMode = "BROWSER";
			driverPropertyName = PropertyHolder.webdriverConfig.getProperty("DRIVER_PROPERTY_NAME");
			;
			driverExecutablePath = PropertyHolder.webdriverConfig.getProperty("DRIVER_EXECUTABLE_PATH");
		} else if (PropertyHolder.webdriverConfig.get("DRIVER").toString().contentEquals("REMOTE")) {
			browser = PropertyHolder.webdriverConfig.getProperty("browser");
			browserVersion = PropertyHolder.webdriverConfig.getProperty("browserVersion");
			platform = PropertyHolder.webdriverConfig.getProperty("platform");
			hubUrl = PropertyHolder.webdriverConfig.getProperty("hubUrl");
			driverMode = "REMOTE";
		} else {
			System.out.println(
					"!!!!!! DRIVER property from WebDriverConfig.properties should be either REMOTE or BROWSER.");
			System.exit(-1);
		}

	}

}
