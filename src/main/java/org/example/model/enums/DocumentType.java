package org.example.model.enums;

public enum DocumentType {

    LICENSE("License"),
    INSURANCE("Insurance"),
    VEHICLEINFO("Vehicle Information");

    private final String description;

    DocumentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public static DocumentType fromString(String documentTypeString) {
        for (DocumentType documentType : DocumentType.values()) {
            if (documentType.description.equalsIgnoreCase(documentTypeString)) {
                return documentType;
            }
        }
        throw new IllegalArgumentException("No constant with text " + documentTypeString + " found");
    }
}
