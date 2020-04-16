package com.test.base;

import org.openqa.selenium.WebDriver;

import com.suite.commons.ExtentLogger;
import com.web.webdriver_factory.WebDriverFactory;

public class BasePage {
	protected WebDriver driver;
	public ExtentLogger logger = new ExtentLogger();
	public BasePage()
	{
		driver = WebDriverFactory.getDriver();
	}

}
