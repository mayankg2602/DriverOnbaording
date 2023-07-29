package org.example.model.enums;

/**
 * Enum for representing the type of a shipment.
 */
public enum ShipmentType {
    TRACKING_DEVICE("Tracking Device"),
    DOCUMENTS("Documents");

    private final String description;

    /**
     * Constructor for enum.
     *
     * @param description a descriptive string for the shipment type
     */
    ShipmentType(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the shipment type.
     *
     * @return a string representing the shipment type
     */
    @Override
    public String toString() {
        return description;
    }

    /**
     * Returns the enum constant of this type with the specified description.
     *
     * @param description string representing the shipment type
     * @return the enum constant with the specified description
     * @throws IllegalArgumentException if this enum type has no constant with the specified description
     */
    public static ShipmentType fromDescription(String description) {
        for (ShipmentType shipmentType : values()) {
            if (shipmentType.description.equalsIgnoreCase(description)) {
                return shipmentType;
            }
        }
        throw new IllegalArgumentException("No enum constant with description " + description);
    }
}
