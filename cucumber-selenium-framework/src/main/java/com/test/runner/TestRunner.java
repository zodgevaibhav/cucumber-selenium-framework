package com.test.runner;

import org.testng.annotations.Test;

import com.suite.common.reporting.ExtentReportTestFactory;
import com.test.base.BaseTest;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

public class TestRunner extends BaseTest{
	
	
	 @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "cucumber-examples-parallel")
	 public void runScenarios(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable
	 {
		 System.out.println("**************In side of test");
		 ExtentReportTestFactory.createNewTest(featureWrapper.toString(),pickleWrapper.toString());
		 cucumberRunner.runScenario(pickleWrapper.getPickle());
	 }
	
}
