package org.example.model.enums;

/**
 * Enum for representing the status of a verification process.
 */
public enum Status {
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private final String description;

    /**
     * Constructor for enum.
     *
     * @param description a descriptive string for the status
     */
    Status(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the status.
     *
     * @return a string representing the status
     */
    @Override
    public String toString() {
        return description;
    }

    /**
     * Returns the enum constant of this type with the specified description.
     *
     * @param description string representing the status
     * @return the enum constant with the specified description
     * @throws IllegalArgumentException if this enum type has no constant with the specified description
     */
    public static Status fromDescription(String description) {
        for (Status status : values()) {
            if (status.description.equalsIgnoreCase(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with description " + description);
    }
}
