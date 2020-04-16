package com.web.webdriver_factory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverManager {
	
	synchronized public static WebDriver CreateInstance() throws MalformedURLException{
		WebDriver dr = null;
		//dr = new RemoteWebDriver(new URL(getDerivedHubUrl()),getDerivedCapabilities());		
		System.setProperty("webdriver.chrome.driver", "/Users/vaibhavzodge/Documents/selenium/chromedriver");
		System.out.println("******** In before object creation");
try {
	
	dr = new ChromeDriver();
}catch(Exception e)
{
	System.out.println("******** Exception while creating object");
	e.printStackTrace();
}
	System.out.println("******** In after object creation");
		return dr;
	}
	
	synchronized private static DesiredCapabilities getDerivedCapabilities(){
		
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

	synchronized private static String getDerivedHubUrl(){
		String hubURL = null;
		if(null!=System.getProperty("platform"))
		{
		if(System.getProperty("platform").contentEquals("web")){
			hubURL = System.getProperty("hubUrl");
		}else if(System.getProperty("platform").contentEquals("mobile")){
			hubURL = System.getProperty("appiumServerURL");
		}
		}else {
			hubURL ="http://127.0.0.1:4444/wd/hub";
		}
		hubURL ="http://127.0.0.1:4444/wd/hub";
		return hubURL;		
	}
}
