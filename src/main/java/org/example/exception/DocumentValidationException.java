package org.example.exception;

/**
 * DocumentValidationException is a custom exception that is thrown when a document fails validation.
 */
public class DocumentValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DocumentValidationException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method)
     */
    public DocumentValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a new DocumentValidationException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method)
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public DocumentValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new DocumentValidationException with the specified cause.
     *
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public DocumentValidationException(Throwable cause) {
        super(cause);
    }
}
