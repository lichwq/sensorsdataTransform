package com.baixing.bi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zjl on 2017/6/13.
 */
public class ProjectProperties {
    private Properties properties;

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public ProjectProperties() {
        properties = new Properties();
    }

    public void loadProperties() {
        StringBuilder filePath = new StringBuilder("/").append("config.properties");
        Properties pps = new Properties();
        InputStream in = null;
        in = this.getClass().getResourceAsStream(filePath.toString());
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadProperties(String path) {
        StringBuilder filePath = new StringBuilder("/").append(path);
        Properties pps = new Properties();
        InputStream in = null;
        in = this.getClass().getResourceAsStream(filePath.toString());
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

