package org.example.validator;

import org.apache.commons.lang3.StringUtils;
import org.example.exception.DocumentValidationException;
import org.example.model.enums.DocumentType;
import org.example.model.request.Document;
import org.example.model.request.DocumentUploadRequest;
import org.example.validator.interfaces.IDriverDocumentValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DriverDocumentValidator implements IDriverDocumentValidator {

    /**
     * This method validates the DocumentUploadRequest object.
     * @param documentUploadRequest DocumentUploadRequest object to validate.
     * @return The validated DocumentUploadRequest object.
     * @throws DocumentValidationException if any of the required document information is missing or invalid.
     */
    @Override
    public DocumentUploadRequest validateDriverDocumentUploadRequest(DocumentUploadRequest documentUploadRequest) throws DocumentValidationException {
        // Validate if the document upload request is not null
        if (documentUploadRequest == null) {
            throw new DocumentValidationException("Document upload request cannot be null.");
        }

        // Get the list of documents from the request
        List<Document> documents = documentUploadRequest.getDocuments();

        // Validate if documents are provided
        if (documents == null || documents.isEmpty()) {
            throw new DocumentValidationException("At least one document must be provided.");
        }

        // Validate each document's details
        for (Document document : documents) {
            validateDocumentDetails(document);
        }

        // Validate distinct document types in the request
        validateDistinctDocumentTypes(documents);

        return documentUploadRequest;
    }

    /**
     * This method validates if all document types in the list are distinct.
     * @param documents the list of documents
     * @throws DocumentValidationException if document types are not distinct
     */
    private void validateDistinctDocumentTypes(List<Document> documents) throws DocumentValidationException {
        Set<DocumentType> documentTypes = new HashSet<>();
        for (Document document : documents) {
            DocumentType documentType = document.getDocumentType();
            if (!documentTypes.add(documentType)) {
                throw new DocumentValidationException("All documents must be of distinct types.");
            }
        }
    }

    /**
     * This method validates the details of a document.
     * @param document the document to be validated
     * @throws DocumentValidationException if any document detail is invalid
     */
    private void validateDocumentDetails(Document document) throws DocumentValidationException {
        if (document.getDocumentType() == null) {
            throw new DocumentValidationException("Document type is required for each document.");
        }
        if (StringUtils.isBlank(document.getDocumentUrl())) {
            throw new DocumentValidationException("Document URL is required for each document.");
        }
    }
}
