package com.product.tests;

import org.openqa.selenium.WebDriver;

import com.web.webdriver_factory.WebDriverFactory;

public class BasePage {
	protected WebDriver driver;
	public BasePage()
	{
		driver = WebDriverFactory.getDriver();
	}

}
