package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dataModel.DriverDocumentEntity;
import org.example.dataModel.DriverEntity;
import org.example.exception.DocumentValidationException;
import org.example.exception.DriverRegistrationException;
import org.example.model.enums.DocumentType;
import org.example.model.enums.Status;
import org.example.model.request.DocumentUploadRequest;
import org.example.model.response.DriverDocuments;
import org.example.repository.interfaces.IDriverRepository;
import org.example.service.interfaces.IDriverDocumentService;
import org.example.transformer.DocumentTransformer;
import org.example.validator.DriverDocumentValidator;
import org.example.validator.interfaces.IDriverDocumentValidator;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service for managing operations related to Driver's Documents.
 */
@Slf4j
public class DriverDriverDocumentService implements IDriverDocumentService {

    private final IDriverDocumentValidator documentValidator;
    private final IDriverRepository iDriverRepository;

    /**
     * Initializes a new instance of the DriverDriverDocumentService class.
     *
     * @param documentValidator Validator for driver document uploads.
     * @param iDriverRepository Repository for managing driver data.
     */
    @Inject
    public DriverDriverDocumentService(DriverDocumentValidator documentValidator, IDriverRepository iDriverRepository) {
        this.documentValidator = documentValidator;
        this.iDriverRepository = iDriverRepository;
    }

    /**
     * Upload document for a given driver ID.
     *
     * @param driverId              ID of the driver to upload document for.
     * @param documentUploadRequest Document upload request details.
     * @return Details of the uploaded documents for the specified driver.
     * @throws DriverRegistrationException If the driver is not found.
     * @throws DocumentValidationException If document validation fails.
     */
    @Override
    public DriverDocuments uploadDocument(Long driverId, DocumentUploadRequest documentUploadRequest) throws DriverRegistrationException, DocumentValidationException {
        documentUploadRequest = documentValidator.validateDriverDocumentUploadRequest(documentUploadRequest);
        DriverEntity driver = findDriverById(driverId);
        Map<DocumentType, DriverDocumentEntity> documentTypeToDocumentUrlMap = getDriverDocuments(driver);
        updateDocuments(driver, documentUploadRequest, documentTypeToDocumentUrlMap);
        iDriverRepository.save(driver);
        return new DriverDocuments(driver.getDocuments());
    }

    /**
     * Retrieve documents for a given driver ID.
     *
     * @param driverId ID of the driver to retrieve documents for.
     * @return Details of the documents for the specified driver.
     * @throws DriverRegistrationException If the driver is not found.
     * @throws DocumentValidationException If driver does not have any documents.
     */
    @Override
    public DriverDocuments getDocumentsByDriverId(Long driverId) throws DriverRegistrationException, DocumentValidationException {
        DriverEntity driver = findDriverById(driverId);
        if (driver.getDocuments() == null || driver.getDocuments().isEmpty()) {
            log.error("No documents uploaded for driver {}", driverId);
            throw new DocumentValidationException("No documents uploaded for driver " + driverId);
        }
        return new DriverDocuments(driver.getDocuments());
    }

    private DriverEntity findDriverById(Long driverId) throws DriverRegistrationException {
        Optional<DriverEntity> driverEntityOptional = iDriverRepository.findById(driverId);
        if (!driverEntityOptional.isPresent()) {
            log.error("Driver with id {} not found.", driverId);
            throw new DriverRegistrationException("Driver with id " + driverId + " not found.");
        }
        return driverEntityOptional.get();
    }

    private Map<DocumentType, DriverDocumentEntity> getDriverDocuments(DriverEntity driver) {
        Map<DocumentType, DriverDocumentEntity> documentTypeToDocumentURLMap = new HashMap<>();
        driver.getDocuments().forEach(document -> {
            documentTypeToDocumentURLMap.put(document.getDocumentType(), document);
        });
        return documentTypeToDocumentURLMap;
    }

    private void updateDocuments(
            DriverEntity driver,
            DocumentUploadRequest documentUploadRequest,
            Map<DocumentType, DriverDocumentEntity> documentTypeToDocumentUrlMap) {
        documentUploadRequest.getDocuments().forEach(documentUploaded -> {
            DriverDocumentEntity document;
            if (documentTypeToDocumentUrlMap.containsKey(documentUploaded.getDocumentType())) {
                document = documentTypeToDocumentUrlMap.get(documentUploaded.getDocumentType());
                document.setDocumentUrl(documentUploaded.getDocumentUrl());
                document.setVerificationStatus(Status.PENDING);
                document.setUpdatedAt(LocalDateTime.now());
            } else {
                document = DocumentTransformer.transformUploadRequestToEntity(documentUploaded);

                if (driver.getDocuments() == null) {
                    driver.setDocuments(new ArrayList<>());
                }
                driver.getDocuments().add(document);
            }
        });
    }
}
