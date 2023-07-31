package org.example.exception;

/**
 * ShipmentException is a custom exception that is thrown when there are issues with shipment management.
 */
public class ShipmentException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ShipmentException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method)
     */
    public ShipmentException(String message) {
        super(message);
    }

    /**
     * Constructs a new ShipmentException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method)
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public ShipmentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ShipmentException with the specified cause.
     *
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public ShipmentException(Throwable cause) {
        super(cause);
    }
}
