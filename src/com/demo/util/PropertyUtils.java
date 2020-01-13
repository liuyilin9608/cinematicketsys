package com.demo.util;


import com.demo.factory.BaseFactory;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(BaseFactory.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
