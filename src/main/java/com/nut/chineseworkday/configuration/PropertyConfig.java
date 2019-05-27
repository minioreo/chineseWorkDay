package com.nut.chineseworkday.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConfig implements Config {
    private  static Properties properties = new Properties();

    static {
        InputStream stream = PropertyConfig.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();    // FunctionCompute LogStore will accept it.
        }
    }

    @Override
    public String getConfigStringValue(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        new PropertyConfig();
    }
}
