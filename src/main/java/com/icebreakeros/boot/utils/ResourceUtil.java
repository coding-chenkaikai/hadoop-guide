package com.icebreakeros.boot.utils;

import java.util.ResourceBundle;

public class ResourceUtil {

    private static final ResourceBundle resourceBundle;

    static{
        resourceBundle = ResourceBundle.getBundle("application");
    }

    public static String getKey(String key){
        return resourceBundle.getString(key);
    }
}
