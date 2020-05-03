package com.orangehrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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
