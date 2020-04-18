package com.suite.commons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suite.commons.listeners.SeleniumMethodInvocationListener;

public class PropertyHolder {
	
	public static Properties webdriverConfig = null;;
    private static final Logger logger = LoggerFactory.getLogger(SeleniumMethodInvocationListener.class);
	public static void loadWebDriverConfig()
	{
		InputStream input;
		try {
			input = new FileInputStream("src/test/resources/WebDriverConfig.properties");
			webdriverConfig = new Properties();
			webdriverConfig.load(input);
		} catch (FileNotFoundException e) {
			System.out.println("!!!!! WebDriverConfig.properties file Not found. \n Please create WebDriverConfig.properties as src/test/resources location and provide configuration properly.");
			logger.error("!!!!! WebDriverConfig.properties file Not found. \n Please create WebDriverConfig.properties as src/test/resources location and provide configuration properly.");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Error while loading property WebDriverConfig.properties file. \n Please check content of property file and try again.");
			logger.error("Error while loading property WebDriverConfig.properties file. \n Please check content of property file and try again.");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
