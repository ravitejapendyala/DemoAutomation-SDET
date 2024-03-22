

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

	private static final String PASSED_STEPS_SCREENSHOT = "passed_steps_screenshot";

	private ConfigLoader() {

		properties = getConfigPropertyFile(RESOURCES_PATH+CONFIG_PROPERTIES);

	}

	private Properties getConfigPropertyFile(String configFile) {
		return PropertyUtils.propertyLoader(configFile);
	}

	public String getPassedStepsScreenshot() {
		return getPropertyValue(PASSED_STEPS_SCREENSHOT);
	}

	private String getPropertyValue(String propertyKey) {
		String prop = properties.getProperty(propertyKey);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property " + propertyKey + " is not specified in the config.properties file");
		}
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}

	public String getBaseUrl() {
		return getPropertyValue(BASE_URL);
	}


}
