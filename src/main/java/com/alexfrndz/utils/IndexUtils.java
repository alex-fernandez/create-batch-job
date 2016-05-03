package com.alexfrndz.utils;


import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class IndexUtils {

    public static Map<String, Object> getMultiField(String fieldName) {
        HashMap<String, Object> raw = Maps.newLinkedHashMap();

        HashMap<String, Object> rawFieldData = Maps.newHashMap();

        rawFieldData.put("type", "string");
        rawFieldData.put("index", "not_analyzed");

        HashMap<String, Object> analyzed = Maps.newHashMap();
        analyzed.put("type", "string");
        analyzed.put("analyzer", "default");
        analyzed.put("index", "analyzed");
        raw.put("raw", rawFieldData);
        raw.put(fieldName, analyzed);
        return raw;
    }
}
