package com.orangehrm.pages.mobile;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suite.commons.PropertyHolder;
import com.test.base.BasePageMobile;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginPage extends BasePageMobile{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

	
//	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='ic_header_logo']")
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='txtUsername']")
	private WebElement userName;	
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='txtPassword']")
	private WebElement password;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='btnLogin']")
	private WebElement loginButton;
	
	
	
	@Given("user navigate to orange hrm URL")
	public LoginPage navigateToOrangeHrmApplication()
	{
		String ENVIRONMENT = PropertyHolder.generalProperties.getProperty("ENVIRONMENT");
		driver.get(PropertyHolder.generalProperties.getProperty(ENVIRONMENT+"_URL"));
		
		
	try {
		Thread.sleep(8000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		System.out.println(driver.getPageSource());
		//setRunTimeTestData("BrowserTitle", driver.getTitle());
		return this;
	}
	
	@When("uses enters user name as {string} and password as {string} and click on login button")
	public void loginForSuccess(String userName, String password)
	{
		logger.info("********** In verify welcome message method");
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		this.loginButton.click();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
