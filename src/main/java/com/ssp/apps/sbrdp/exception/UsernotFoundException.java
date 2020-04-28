package com.ssp.apps.sbrdp.exception;

public class UsernotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8687532343830691372L;

    public UsernotFoundException() {}

    public UsernotFoundException(String message) {
        super(message);
    }

}
