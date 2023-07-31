package org.example.exception;

/**
 * DriverRegistrationException is a custom exception that is thrown when driver registration fails.
 */
public class DriverRegistrationException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DriverRegistrationException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method)
     */
    public DriverRegistrationException(String message) {
        super(message);
    }

    /**
     * Constructs a new DriverRegistrationException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method)
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public DriverRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new DriverRegistrationException with the specified cause.
     *
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public DriverRegistrationException(Throwable cause) {
        super(cause);
    }
}
