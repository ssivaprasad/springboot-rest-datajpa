package com.ssp.apps.sbrdp.exception;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {}

    public UserNotFoundException(String message) {
        super(message);
    }
}
