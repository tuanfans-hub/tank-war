package com.tuanfans;

import java.io.IOException;
import java.util.Properties;

/**
 * @author TuanFans
 * @date 2026/5/31
 */
public class ConfigManager {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties props = new Properties();
    static {
        try {
            props.load(ConfigManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String get(String key){
        return props.getProperty(key);
    }

    public static String get(String key,String defaultValue){
        return props.getProperty(key,defaultValue);
    }

    public static int getInt(String key){
        return Integer.parseInt(get(key));
    }

    public static int getInt(String key,int defaultValue){
        return Integer.parseInt(get(key,String.valueOf(defaultValue)));
    }

    public static double getDouble(String key){
        return Double.parseDouble(get(key));
    }

    public static double getDouble(String key,double defaultValue){
        return Double.parseDouble(get(key,String.valueOf(defaultValue)));
    }
}
