package com.suite.commons.listeners;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.suite.common.reporting.ExtentReportTestFactory;
import com.suite.commons.SeleniumUtils;
import com.web.webdriver_factory.WebDriverFactory;
import com.web.webdriver_factory.WebDriverManager;


public class SeleniumMethodInvocationListener implements IInvokedMethodListener {

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		System.out.println("******** In before invocation");
		if(method.isTestMethod())
		{
			System.out.println("******** In before invocation");
			try {
				WebDriverFactory.setDriver(WebDriverManager.CreateInstance());
				//ExtentReportTestFactory.createNewTest(method);
				System.out.println("******** Driver object and test report instance created successfully");
			} catch (MalformedURLException e) {
				System.out.println("!!!!!!!! Exception while creating Driver object and test report instance ");
				e.printStackTrace();
			}
		}
		
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if(method.isTestMethod())
		{
			System.out.println("******** In after invocation");
			System.out.println("******** In after invocation - Test Case Status " +testResult.isSuccess());

			if(!testResult.isSuccess())
			{
				try {
				System.out.println("!!!!!!!!! Test case found failed");
				ExtentReportTestFactory.getTest().fail(testResult.getThrowable());
				String fileName = new Long(System.currentTimeMillis()).toString().replace(".", "").replace(":", "");
				SeleniumUtils.takeScreenshot(System.getProperty("user.dir")+"\\"+method.getTestMethod().getMethodName()+".png");
				System.out.println("********* Screenshot taken at location "+System.getProperty("user.dir")+"/"+fileName+".png");
				
					ExtentReportTestFactory.getTest().addScreenCaptureFromPath(System.getProperty("user.dir")+"/"+fileName+".png");
					System.out.println("******** Screenshot attached to extent report");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			WebDriverFactory.getDriver().quit();
			ExtentReportTestFactory.flushReport();
		}
	}

	
}
