package com.prolifics.util;

import java.util.HashMap;
import java.util.Map;

public class ApplicationProperties {
	private static Map<String, String> sysProperties = new HashMap<>();
	
	/**
	 * @return the sysProperties
	 */
	public static Map<String, String> getSysProperties() {
		return sysProperties;
	}
	/**
	 * @param sysProperties the sysProperties to set
	 */
	public static void setSysProperties(Map<String, String> sysProperties) {
		ApplicationProperties.sysProperties = sysProperties;
	}
	
	public static String getProperty(String propertyName) {
		if(sysProperties.containsKey(propertyName)) {
			return sysProperties.get(propertyName);
		}
		return "";
	}
}
