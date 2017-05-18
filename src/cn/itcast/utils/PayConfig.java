package cn.itcast.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PayConfig {
    private static Properties prop = new Properties();

    static {
        InputStream in = PayConfig.class.getClassLoader().getResourceAsStream("merchantInfo.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            new RuntimeException(e);
        }
    }

    public static String getValue(String key) {
        return prop.getProperty(key);
    }
}
