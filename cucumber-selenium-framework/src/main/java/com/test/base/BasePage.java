package com.test.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.suite.commons.ExtentLogger;
import com.web.webdriver_factory.WebDriverFactory;

public class BasePage {
	protected WebDriver driver;
	public ExtentLogger logger = new ExtentLogger();
	public BasePage()
	{
		driver = WebDriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

}
