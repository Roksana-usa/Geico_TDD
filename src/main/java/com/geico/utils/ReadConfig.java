package com.geico.utils;

import java.io.IOException;
import java.util.Properties;

import com.geico.constants.ConfigConstant;

public class ReadConfig {

	Properties prop;

	public ReadConfig() {
		loadPreoperties();

	}

	public void loadPreoperties() {

		prop = new Properties();
		try {

			prop.load(getClass().getClassLoader().getResourceAsStream("configuration.properties"));

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public String getValue(ConfigConstant key) {
		return prop.getProperty(key.toString());

	}

}
