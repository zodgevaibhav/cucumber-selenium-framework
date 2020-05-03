package com.test.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.suite.commons.ExtentLogger;
import com.test.webdriver_factory.WebDriverFactory;

public class BasePage {
	protected WebDriver driver;
	protected SoftAssert softAssert;

	public ExtentLogger logger = new ExtentLogger();
	public BasePage()
	{
		driver = WebDriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public Wait<WebDriver> CustomFluentWait(int timeout, int pollingSeconds)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			    .withTimeout(Duration.ofSeconds(timeout)) 
			    .pollingEvery(Duration.ofSeconds(pollingSeconds)); 
		return wait;
	}

}
