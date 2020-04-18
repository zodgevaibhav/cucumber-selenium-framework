package com.orangehrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.suite.commons.JavaUtils;
import com.test.base.BasePage;

import io.cucumber.java.en.Then;

public class HomePage extends BasePage {

	//BasePage provides driver object, logger object
	
	@FindBy(id = "welcome")
	private WebElement welComeText;

	@Then("user should be able to see {string} message")
	public HomePage verifyWelcomeText(String strWelComeText) {
		logger.info("********** In verify welcome message method");
		Assert.assertEquals(welComeText.getText(), strWelComeText);
		return this;
	}
}
