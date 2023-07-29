package org.example.exception;

public class ShipmentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ShipmentException(String message) {
        super(message);
    }

    public ShipmentException(String message, Throwable cause) {
        super(message, cause);
    }

}

