package org.example.model.enums;

/**
 * Enum for representing the status of a shipment.
 */
public enum ShipmentStatus {
    ORDERED("Ordered"),
    OUT_OF_STOCK("Out of Stock");

    private final String description;

    /**
     * Constructor for enum.
     *
     * @param description a descriptive string for the status
     */
    ShipmentStatus(String description) {
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
    public static ShipmentStatus fromDescription(String description) {
        for (ShipmentStatus status : values()) {
            if (status.description.equalsIgnoreCase(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with description " + description);
    }
}
