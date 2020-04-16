package com.orangehrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.base.BasePage;

import io.cucumber.java.en.Then;

public class HomePage extends BasePage {

	@FindBy(id = "welcome")
	private WebElement welComeText;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@Then("user should be able to see {string} message")
	public HomePage verifyWelcomeText(String strWelComeText) {
		Assert.assertEquals(welComeText.getText(), strWelComeText);
		return this;
	}
}
