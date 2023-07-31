package org.example.service.interfaces;

import org.example.exception.DocumentValidationException;
import org.example.exception.DriverRegistrationException;
import org.example.model.response.DriverDocuments;
import org.example.model.request.DocumentUploadRequest;

/**
 * Interface for managing operations related to documents.
 */
public interface IDriverDocumentService {

    /**
     * Uploads a document for a driver.
     *
     * @param driverId The ID of the driver for which to upload the document.
     * @param documentUploadRequest The request containing the details of the document to be uploaded.
     * @throws DriverRegistrationException If the driver ID is not valid.
     * @throws DocumentValidationException If the document is not valid.
     * @return The uploaded document details.
     */
    DriverDocuments uploadDocument(Long driverId, DocumentUploadRequest documentUploadRequest) throws DriverRegistrationException, DocumentValidationException;

    /**
     * Retrieves documents for a driver.
     *
     * @param driverId The ID of the driver for which to retrieve the documents.
     * @throws DriverRegistrationException If the driver ID is not valid.
     * @throws DocumentValidationException If an error occurs during document retrieval.
     * @return The retrieved documents.
     */
    DriverDocuments getDocumentsByDriverId(Long driverId) throws DriverRegistrationException, DocumentValidationException;
}