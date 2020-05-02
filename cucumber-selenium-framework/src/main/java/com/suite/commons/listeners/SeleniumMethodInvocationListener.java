package com.suite.commons.listeners;

import java.io.IOException;
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.suite.commons.SeleniumUtils;
import com.suite.commons.reporting.ExtentReportTestFactory;
import com.test.webdriver_factory.WebDriverFactory;
import com.test.webdriver_factory.WebDriverManager;


public class SeleniumMethodInvocationListener implements IInvokedMethodListener {
    private static final Logger logger = LoggerFactory.getLogger(SeleniumMethodInvocationListener.class);

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		logger.info("******** In before invocation");
		if(method.isTestMethod())
		{
			logger.info("******** In before invocation");
			try {
				WebDriverFactory.setDriver(WebDriverManager.CreateInstance());
				//ExtentReportTestFactory.createNewTest(method);
				logger.info("******** Driver object and test report instance created successfully");
			} catch (MalformedURLException e) {
				logger.error("!!!!!!!! Exception while creating Driver object and test report instance ");
				e.printStackTrace();
			}
		}
		
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if(method.isTestMethod())
		{
			logger.info("******** In after invocation");
			logger.info("******** In after invocation - Test Case Status " +testResult.isSuccess());

			if(!testResult.isSuccess())
			{
				try {
				ExtentReportTestFactory.getTest().fail(testResult.getThrowable());
				String fileName = new Long(System.currentTimeMillis()).toString().replace(".", "").replace(":", "");
				SeleniumUtils.takeScreenshot(System.getProperty("user.dir")+"\\"+fileName+".png");				
					ExtentReportTestFactory.getTest().addScreenCaptureFromPath(System.getProperty("user.dir")+"/"+fileName+".png");
					logger.debug("******** Screenshot attached to extent report");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			WebDriverFactory.getDriver().quit();
		}
	}

	
}
