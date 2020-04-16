package com.test.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.suite.common.reporting.ExtentReportTestFactory;
import com.test.base.BaseTest;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

public class TestRunner extends BaseTest{
    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

	
	 @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "cucumber-examples-parallel")
	 public void runScenarios(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable
	 {
		 logger.info("**************In side of test");
		 ExtentReportTestFactory.createNewTest(featureWrapper.toString(),pickleWrapper.toString());
		 cucumberRunner.runScenario(pickleWrapper.getPickle());
	 }
	
}
