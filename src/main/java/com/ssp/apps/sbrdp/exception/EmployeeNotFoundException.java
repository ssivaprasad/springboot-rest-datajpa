package com.ssp.apps.sbrdp.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException() {}

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
