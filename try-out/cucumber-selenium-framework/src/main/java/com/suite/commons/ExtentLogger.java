package com.suite.commons;

import com.aventstack.extentreports.Status;
import com.suite.common.reporting.ExtentReportTestFactory;

public class ExtentLogger {

	public void info(String message) {
		ExtentReportTestFactory.getTest().log(Status.INFO, message);
	}
	
	public void error(String message) {
		ExtentReportTestFactory.getTest().error(message);
	}
	
	public void error(Throwable thorowable) {
		ExtentReportTestFactory.getTest().error(thorowable);
	}
	
	public void fail(String message) {
		ExtentReportTestFactory.getTest().fail(message);
	}
	
	public void fail(Throwable thorowable) {
		ExtentReportTestFactory.getTest().fail(thorowable);
	}

}
