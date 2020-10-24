package com.casesapi.utils;

import com.google.gson.Gson;

import java.util.Map;

public class GeneralUtils {

    /**
     * Check if object is empty
     * @param obj Object
     * @return boolean true/false
     * @access public
     */
    public static boolean isEmpty(Object obj) {
        return (obj == null);
    }

    /**
     * Build json String from attributes
     * @param objectAttr Map<String, String>
     * @return json String
     * @access public
     */
    public static String parseToJson(Map<String, String> objectAttr) {
        Gson gson = new Gson();
        return gson.toJson(objectAttr);
    }

    public static String removeSlashes(String value) {
        return value.replace("\"", "");
    }
}
