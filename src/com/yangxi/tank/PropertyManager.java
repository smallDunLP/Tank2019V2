package com.yangxi.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static Properties pros;

    static{
        pros = new Properties();
        try {
            pros.load(PropertyManager.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String get(String key){
        return (String)pros.get(key);
    }
}
