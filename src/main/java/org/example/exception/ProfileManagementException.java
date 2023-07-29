package org.example.exception;

public class ProfileManagementException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProfileManagementException(String message) {
        super(message);
    }

    public ProfileManagementException(String message, Throwable cause) {
        super(message, cause);
    }

}
