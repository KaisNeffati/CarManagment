package com.carManagement.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class CreateSerializeException extends Exception {

    private static final long serialVersionUID = 4482608395789792044L;

    public CreateSerializeException(String message) {
        super(message);
    }

    public CreateSerializeException(Object object, JsonProcessingException e) {
        super(object.toString(), e);
    }

    public CreateSerializeException(String simpleName, IOException e) {
        super(simpleName, e);
    }
}
