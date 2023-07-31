package org.example.exception;

/**
 * ProfileManagementException is a custom exception that is thrown when there are issues with profile management.
 */
public class ProfileManagementException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ProfileManagementException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method)
     */
    public ProfileManagementException(String message) {
        super(message);
    }

    /**
     * Constructs a new ProfileManagementException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method)
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public ProfileManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ProfileManagementException with the specified cause.
     *
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public ProfileManagementException(Throwable cause) {
        super(cause);
    }
}
