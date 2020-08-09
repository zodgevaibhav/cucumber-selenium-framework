package com.test.base;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.asserts.SoftAssert;

import com.suite.commons.AssertionFactory;
import com.suite.commons.ExtentLogger;
import com.test.webdriver_factory.WebDriverFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePageMobile {
	protected final static int DEFAULTWAITSECONDS = 10;
	protected final static int MINWAITSECONDS = 1;
	protected final static int MAXWAITSECONDS = 15;
	protected SoftAssert softAssert;
	@SuppressWarnings("rawtypes")
	protected Wait<AppiumDriver> fluentwait;
	protected AppiumDriver<MobileElement> driver;
	public ExtentLogger logger = new ExtentLogger();
	@SuppressWarnings("unchecked")
	public BasePageMobile()
	{
		System.out.println("******* In Base Page mobile");
		driver = (AppiumDriver<MobileElement>) WebDriverFactory.getDriver();
		//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		fluentwait = getFluentWaitObject(15);
		softAssert = new SoftAssert();
		AssertionFactory.setSoftAssert(softAssert);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);	

	}
	@SuppressWarnings("rawtypes")
	public Wait<AppiumDriver> getFluentWaitObject(int timeout)
	{
		Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>((AppiumDriver) driver)        
				.withTimeout(Duration.ofSeconds(10) )        
				.pollingEvery(Duration.ofSeconds(1))        
				.ignoring(NoSuchElementException.class);
		return wait;
	}

}
