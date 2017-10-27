package com.carManagement.exception;

public class CarAlreadyInUseException extends Exception {

    private static final long serialVersionUID = 6158207070376419217L;

    public CarAlreadyInUseException(String message) {
        super(message);
    }
}
