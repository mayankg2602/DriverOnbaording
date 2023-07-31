package org.example.validator.interfaces;

import org.example.exception.DocumentValidationException;
import org.example.model.request.DocumentUploadRequest;

/**
 * IDriverDocumentValidator interface provides contract for validating the driver document upload request.
 */
public interface IDriverDocumentValidator {

    /**
     * Validates the document upload request for a driver.
     * @param documentUploadRequest the document upload request
     * @return The validated document upload request
     * @throws DocumentValidationException if the validation fails
     */
    DocumentUploadRequest validateDriverDocumentUploadRequest(DocumentUploadRequest documentUploadRequest) throws DocumentValidationException;
}