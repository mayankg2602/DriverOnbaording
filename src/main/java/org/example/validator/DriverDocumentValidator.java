package org.example.validator;

import org.apache.commons.lang3.StringUtils;
import org.example.exception.DocumentValidationException;
import org.example.model.Document;
import org.example.model.request.DocumentUploadRequest;
import org.example.validator.interfaces.IDriverDocumentValidator;

import java.util.List;

public class DriverDocumentValidator implements IDriverDocumentValidator {
    public DocumentUploadRequest validateDriverDocumentUploadRequest(DocumentUploadRequest documentUploadRequest) {
        if(documentUploadRequest == null) {
            throw new DocumentValidationException("request is null");
        }

        if(documentUploadRequest.getDriverId() == null) {
            throw new DocumentValidationException("driver id is null");
        }
        if(documentUploadRequest.getDocuments() == null || documentUploadRequest.getDocuments().isEmpty()) {
            throw new DocumentValidationException("no documents are provided");
        }
        List<Document> documents = documentUploadRequest.getDocuments();
        for(Document document: documents) {
            if(StringUtils.isBlank(document.getDocumentType()) || StringUtils.isBlank(document.getDocumentUrl())) {
                throw new DocumentValidationException("document/s are not correct");
            }
        }
        return documentUploadRequest;
    }

    public Long validateDriverId(Long driverId) {
        if(driverId == null) {
            throw new DocumentValidationException("driver id is null");
        }
        return driverId;
    }
}
