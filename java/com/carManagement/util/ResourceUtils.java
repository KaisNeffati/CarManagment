package com.carManagement.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.carManagement.exception.CreateSerializeException;

import java.io.IOException;
import java.util.UUID;

public class ResourceUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    private ResourceUtils() {
        // Private constructor
    }

    /**
     * Generate a new, random hex-encoded globally unique id.
     *
     * @return base64 encoded UTF8 string
     */
    public static long generateUniqueId() {
        // A UUID represents a 128-bit value, that is 16 bytes long value.
        final UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.getMostSignificantBits());
    }


    public static <T> T JsonToObject(final String json, final Class<T> clazz) throws CreateSerializeException {
        try {
            return json == null ? null : mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new CreateSerializeException(clazz.getSimpleName(), e);
        }
    }

    public static String toJsonString(Object object) throws CreateSerializeException {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new CreateSerializeException(object, e);
        }
    }


}
