/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Selenium Java Test Framework & Best Practices - Masterclass (https://www.udemy.com/course/selenium-java-test-framework/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 */

/***************************************************/

package org.selenium.utils;


import java.util.Properties;

/*Singleton Design pattern*/
public class ConfigLoader {

	private static final String BASE_URL = "baseUrl";
	private static final String CONFIG_PROPERTIES = "/configuration.properties";

	private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/";
	private Properties properties;
	// private final Properties properties;
	private static ConfigLoader configLoader;

	private ConfigLoader() {

		properties = getConfigPropertyFile(RESOURCES_PATH+CONFIG_PROPERTIES);

	}

	private Properties getConfigPropertyFile(String configFile) {
		return PropertyUtils.propertyLoader(RESOURCES_PATH + configFile);
	}

	private String getPropertyValue(String propertyKey) {
		String prop = properties.getProperty(propertyKey);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property " + propertyKey + " is not specified in the config.properties file");
		}
	}

	public String getBaseUrl() {
		return getPropertyValue(BASE_URL);
	}


}
