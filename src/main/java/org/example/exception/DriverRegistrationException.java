package org.example.exception;

public class DriverRegistrationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DriverRegistrationException(String message) {
        super(message);
    }

    public DriverRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

}

