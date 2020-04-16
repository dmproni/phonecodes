package ru.rtk.utils;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonUtils {

    public static Map<String, String> jsonBodyToMap(JsonNode json) {
        Map<String, String> buff = new HashMap<>();

        Iterator<String> names = json.fieldNames();
        while (names.hasNext()) {
            String name = names.next();
            buff.put(name, json.get(name).textValue());
        }

        return buff;
    }

}
