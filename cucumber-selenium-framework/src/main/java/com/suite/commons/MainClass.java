package com.suite.commons;

public class MainClass {

	
	public static void main(String[] args) {
		PropertyHolder.loadWebDriverConfig();
		System.out.println(PropertyHolder.webdriverConfig.get("DRIVER"));
	}
}
