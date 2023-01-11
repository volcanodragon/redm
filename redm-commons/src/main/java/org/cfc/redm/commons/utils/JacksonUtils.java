package org.cfc.redm.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonUtils {

    private static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            var newObjectMapper = new ObjectMapper();
            // 时间处理模块
            newObjectMapper.registerModule(new JavaTimeModule());
            objectMapper = newObjectMapper;
        }
        return objectMapper;
    }
}
