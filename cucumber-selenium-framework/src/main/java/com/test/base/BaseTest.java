package com.test.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.suite.commons.ExtentLogger;
import com.suite.commons.listeners.SeleniumMethodInvocationListener;

import io.cucumber.testng.TestNGCucumberRunner;

@Listeners(SeleniumMethodInvocationListener.class)
public class BaseTest {

	public TestNGCucumberRunner cucumberRunner;

	@AfterClass(alwaysRun=true)
	public void afterClass()
	{
		cucumberRunner.finish(); // Cloused used instances and print summary of execution. 
	}
	
	 @Parameters({"featureFilePath","glueCodePackageName"})
	 @BeforeSuite
	 public void beforeSuite(String featureFilePath, String glueCodePackageName)
	 {
		 System.setProperty("cucumber.options",featureFilePath+" --glue "+glueCodePackageName+" ");
		 cucumberRunner = new TestNGCucumberRunner(this.getClass());
	 }
	 
	 
	 @DataProvider(name="cucumber-examples-parallel",parallel=true)
	 public Object[][] dataProviderParallel()
	 {
		 return cucumberRunner.provideScenarios();
	 }
	 
	 @DataProvider(name="cucumber-examples-sequential",parallel=false)
	 public Object[][] dataProviderSequencrial()
	 {
		 return cucumberRunner.provideScenarios();
	 } 
	 

}
