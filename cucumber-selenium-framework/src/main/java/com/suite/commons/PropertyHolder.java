package com.suite.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suite.commons.listeners.SeleniumMethodInvocationListener;

public class PropertyHolder {

	public static Properties webdriverProperties = null;;
	public static Properties generalProperties = null;;
	private static final Logger logger = LoggerFactory.getLogger(SeleniumMethodInvocationListener.class);

	public static void loadWebDriverConfig() {
		InputStream input;
		try {
			input = new FileInputStream("src/test/resources/WebDriverConfig.properties");
			webdriverProperties = new Properties();
			webdriverProperties.load(input);
		} catch (FileNotFoundException e) {
			System.out.println(
					"!!!!! WebDriverConfig.properties file Not found. \n Please create WebDriverConfig.properties as src/test/resources location and provide configuration properly.");
			logger.error(
					"!!!!! WebDriverConfig.properties file Not found. \n Please create WebDriverConfig.properties as src/test/resources location and provide configuration properly.");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.out.println(
					"Error while loading property WebDriverConfig.properties file. \n Please check content of property file and try again.");
			logger.error(
					"Error while loading property WebDriverConfig.properties file. \n Please check content of property file and try again.");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static void loadGeneralConfig() {
		InputStream input;
		generalProperties = new Properties();
		File[] files = new File("src/test/resources/properties").listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				logger.error(
						"!!!!! Unexpected structure found : src/test/resources/properties folder contain sub folders. It should contain only properties files. Load properties form sub folder is not yet implemented.");
			} else {
				try {
					input = new FileInputStream(file);
					Properties tempGeneralConfig = new Properties();
					tempGeneralConfig.load(input);
					generalProperties.putAll(tempGeneralConfig);
				} catch (FileNotFoundException e) {
					logger.error(
							"!!!!! "+file.getAbsolutePath()+" file Not found. \n Please create WebDriverConfig.properties as src/test/resources location and provide configuration properly.");
					logger.error(
							"!!!!!  "+file.getAbsolutePath()+" file Not found. \n Please create WebDriverConfig.properties as src/test/resources location and provide configuration properly.");
					e.printStackTrace();
					//System.exit(-1);
				} catch (IOException e) {
					logger.error(
							"Error while loading property "+file.getAbsolutePath()+" file. \n Please check content of property file and try again.");
					logger.error(
							"Error while loading property "+file.getAbsolutePath()+" file. \n Please check content of property file and try again.");
					e.printStackTrace();
					//System.exit(-1);
				}
			}
		}
	}
}
