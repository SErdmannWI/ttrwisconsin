package com.serdmannwi.practiceprograms.tickettoridewisconsin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String serializeToJson(List<String> data) {
        try {
            return OBJECT_MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serialzing List: " + e.getMessage());
        }
    }

    public static List<String> deserializeFromJson(String jsonData) {
        try {
            return OBJECT_MAPPER.readValue(jsonData, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing JSON into List: " + e.getMessage());
        }
    }
}
