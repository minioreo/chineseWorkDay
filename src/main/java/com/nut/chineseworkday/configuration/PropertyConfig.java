package com.nut.chineseworkday.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConfig implements Config {
    private  static Properties properties = new Properties();

    public PropertyConfig(String path) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getConfigStringValue(String key) {
        return properties.getProperty(key);
    }

}
