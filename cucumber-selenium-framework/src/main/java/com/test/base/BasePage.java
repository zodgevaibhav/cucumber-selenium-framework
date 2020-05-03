package com.test.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.suite.commons.ExtentLogger;
import com.test.test_data.RuntimeTestDataHolder;
import com.test.webdriver_factory.WebDriverFactory;
/**
 * {@inheritDoc}
 * @param otherAnimal Tasty treat.
 */
public class BasePage {
	protected WebDriver driver;
	public ExtentLogger logger = new ExtentLogger();
	private Map<String,String> runTimeTestData = new HashMap<String,String>();
	
	public BasePage()
	{
		driver = WebDriverFactory.getDriver();
		PageFactory.initElements(driver, this);
		runTimeTestData = RuntimeTestDataHolder.getRunTimeTestData();
	}

	public String getRunTimeTestData(String key) {
		return runTimeTestData.get(key);
	}

	public void setRunTimeTestData(String key, String value) {
		
		if(runTimeTestData.containsKey(key))
		{
			logger.info("!!!!!!!!!! Duplicate Runtime Data Key inserstion found. Key "+key+" was already present in Map with value "+runTimeTestData.get(key)+". Same key is being asked to insert again, so, overwriting it with new value "+value+". Confirm if it is intended.");
		}
		runTimeTestData.put(key, value);
	}

}
