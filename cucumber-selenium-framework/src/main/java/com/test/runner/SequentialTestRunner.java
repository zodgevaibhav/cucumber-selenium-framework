package com.test.runner;

import org.testng.annotations.Test;

import com.suite.commons.AssertionFactory;
import com.suite.commons.reporting.ExtentReportTestFactory;
import com.test.base.BaseTest;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

public class SequentialTestRunner extends BaseTest{
	
	 @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "cucumber-examples-sequential")
	 public void runScenarios(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable
	 {
		 ExtentReportTestFactory.createNewTest(featureWrapper.toString(),pickleWrapper.toString());
		 ExtentReportTestFactory.assignTestCategories(pickleWrapper.getPickle().getTags());
		 cucumberRunner.runScenario(pickleWrapper.getPickle());
		 AssertionFactory.getSoftAssert().assertAll();
	 }
	
}
