package com.test.base;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.asserts.SoftAssert;
import com.suite.commons.AssertionFactory;
import com.suite.commons.ExtentLogger;
import com.test.webdriver_factory.WebDriverFactory;
public class BasePage {

	protected SoftAssert softAssert;
	protected Wait<WebDriver> fluentwait;
	protected WebDriver driver;
	public ExtentLogger logger = new ExtentLogger();

	public BasePage() {
		driver = WebDriverFactory.getDriver();
		fluentwait = getFluentWaitTimeout();
		softAssert = new SoftAssert();
		AssertionFactory.setSoftAssert(softAssert);

		PageFactory.initElements(driver, this);

	}

	public Wait<WebDriver> getFluentWaitTimeout() {
		return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
	}

}
