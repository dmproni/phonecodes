package ru.rtk.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonUtilsTest {

    @Test
    void testJsonObjectToMap() throws Exception {
        String jsonString = "{'k1':'v1', 'k2':'v2', 'k3':'v3'}".replace("'", "\"");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonString);

        Map<String, String> expected = new HashMap<>();
        expected.put("k1", "v1");
        expected.put("k2", "v2");
        expected.put("k3", "v3");

        Assertions.assertEquals(expected, JsonUtils.jsonBodyToMap(node));
    }
}
