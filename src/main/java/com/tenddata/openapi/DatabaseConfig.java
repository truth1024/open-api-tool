package com.tenddata.openapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by LD on 2015/10/29 0029.
 */
public class DatabaseConfig {
    private static Properties properties = null;

    static {
        properties = new Properties();
        InputStream inputStream = DatabaseConfig.class.getResourceAsStream("/database.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String name) {
        return properties.getProperty(name);
    }

    public static void main(String[] args) {
        System.out.println(DatabaseConfig.getProperties("jdbc.ip"));
    }
}
