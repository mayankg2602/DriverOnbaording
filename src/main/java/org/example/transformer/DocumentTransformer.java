package org.example.transformer;

import org.example.dataModel.DriverDocumentEntity;
import org.example.model.request.Document;
import org.example.model.enums.Status;

import java.time.LocalDateTime;

public class DocumentTransformer {
    /**
     * Transforms a Document object to a DriverDocumentEntity object.
     *
     * @param document The input Document object.
     * @return A new DriverDocumentEntity object with data from the request.
     * @throws IllegalArgumentException if the input Document is null.
     */
    public static DriverDocumentEntity transformUploadRequestToEntity(Document document) {
        if (document == null) {
            throw new IllegalArgumentException("Document cannot be null");
        }

        DriverDocumentEntity driverDocumentEntity = new DriverDocumentEntity();
        driverDocumentEntity.setDocumentUrl(document.getDocumentUrl());
        driverDocumentEntity.setDocumentType(document.getDocumentType());
        driverDocumentEntity.setVerificationStatus(Status.PENDING);
        driverDocumentEntity.setCreatedAt(LocalDateTime.now());

        return driverDocumentEntity;
    }
}
