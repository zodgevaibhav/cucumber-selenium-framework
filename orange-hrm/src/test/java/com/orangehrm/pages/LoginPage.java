package com.orangehrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suite.commons.PropertyHolder;
import com.test.base.BasePageBrowser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginPage extends BasePageBrowser{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

	
	@FindBy(id="txtUsername")
	private WebElement userName;
	
	@FindBy(id="txtPassword")
	private WebElement password;
	
	@FindBy(id="btnLogin")
	private WebElement loginButton;
	
	
	
	@Given("user navigate to orange hrm URL")
	public LoginPage navigateToOrangeHrmApplication()
	{
		String ENVIRONMENT = PropertyHolder.generalProperties.getProperty("ENVIRONMENT");
		driver.get(PropertyHolder.generalProperties.getProperty(ENVIRONMENT+"_URL"));
		setRunTimeTestData("BrowserTitle", driver.getTitle());
		return this;
	}
	
	@When("uses enters user name as {string} and password as {string} and click on login button")
	public void loginForSuccess(String userName, String password)
	{
		logger.info("********** In verify welcome message method");
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		this.loginButton.click();
	}

}
