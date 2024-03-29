package com.test.webdriver_factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suite.commons.PropertyHolder;
import com.suite.commons.listeners.SeleniumMethodInvocationListener;

public class CapabilityFactory {
	private static final Logger logger = LoggerFactory.getLogger(SeleniumMethodInvocationListener.class);

	private static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

	static {
		System.out.println("*********** Static block called. Platform - " + PropertyHolder.testSuiteConfigurationProperties.get("platform").toString());
		switch (PropertyHolder.testSuiteConfigurationProperties.get("platform").toString()) {
		case "ANDROID":
			System.out.println("*********** ANDROIF IN");
			fillCapabilities(getProperties("AndroidCapabilities.properties"));
			System.out.println("*********** ANDROIF OUT");
			break;
		case "IOS":
			System.out.println("*********** IOS IN");
			fillCapabilities(getProperties("iOS.properties"));
			System.out.println("*********** IOS OUT");
			break;
		default:
			System.out.println("*********** BROWSER IN");
			fillCapabilities(getProperties("Browser.properties"));
			System.out.println("*********** BROWSER OUT");
			break;
		}

		System.out.println("*********** Static block end");
	}

	public static DesiredCapabilities getDesiredCapabilities() {
		System.out.println("*********** Called get desiredCapabilities()");

		if (null == desiredCapabilities) {
			logger.error("!!!!!!!!!!!  Desired capability found null");
			System.out.println("!!!!!!!!!!!  Desired capability found null");
		}
		desiredCapabilities.asMap()
				.forEach((k, v) -> System.out.println("************* Capabilities " + k + " - " + v));

		return desiredCapabilities;
	}

	private static void fillCapabilities(Properties properties) {
		System.out.println("fillCapabilities IN - "+properties.toString());
		properties.forEach((k, v) ->desiredCapabilities.setCapability(k.toString(), v.toString()));
		System.out.println("fillCapabilities OUT - "+properties.toString());
	}

	private static Properties getProperties(String propertyFileName) {
		System.out.println("getProperties IN - "+propertyFileName);
		InputStream input;
		Properties properties = new Properties();
		try {
			input = new FileInputStream("src/test/resources/properties/capabilities/" + propertyFileName);
			properties = new Properties();
			properties.load(input);
		} catch (FileNotFoundException e) {
			System.out
					.println("!!!!! Unable to laoad property file at location src/test/resources/properties/capabilities/"
							+ propertyFileName);
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.out
					.println("!!!!! Unable to laoad property file at location src/test/resources/properties/capabilities/"
							+ propertyFileName + " \n Please check content of property file and try again.");
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("getProperties OUT - "+propertyFileName);
		return properties;
	}

}
