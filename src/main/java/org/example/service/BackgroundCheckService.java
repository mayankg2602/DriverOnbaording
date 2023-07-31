package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dataModel.DriverDocumentEntity;
import org.example.dataModel.DriverEntity;
import org.example.exception.DocumentValidationException;
import org.example.exception.DriverRegistrationException;
import org.example.service.interfaces.IBackgroundCheckService;
import org.example.model.enums.Status;
import org.example.repository.DocumentRepository;
import org.example.repository.interfaces.IDocumentRepository;
import org.example.repository.interfaces.IDriverRepository;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Service for performing background checks on drivers and their documents.
 */
@Slf4j
public class BackgroundCheckService implements IBackgroundCheckService {

    private final IDocumentRepository documentRepository;
    private final IDriverRepository driverRepository;

    /**
     * Initializes a new instance of the BackgroundCheckService class.
     *
     * @param documentRepository The repository for managing document data.
     * @param driverRepository   The repository for managing driver data.
     */
    @Inject
    public BackgroundCheckService(DocumentRepository documentRepository, IDriverRepository driverRepository) {
        this.documentRepository = documentRepository;
        this.driverRepository = driverRepository;
    }

    /**
     * Approve a document given its ID.
     *
     * @param documentId The ID of the document to be approved.
     * @throws DocumentValidationException if the document is not found.
     */
    @Override
    public void approveDocument(Long documentId) throws DocumentValidationException {
        DriverDocumentEntity driverDocumentEntity = findDocumentById(documentId);
        setDocumentStatusToCompleted(driverDocumentEntity);
        documentRepository.save(driverDocumentEntity);
    }

    /**
     * Approve a driver given its ID.
     *
     * @param driverId The ID of the driver to be approved.
     * @throws DriverRegistrationException if the driver is not found.
     */
    @Override
    public void approveDriver(Long driverId) throws DriverRegistrationException {
        DriverEntity driver = findDriverById(driverId);
        setDriverStatusToCompleted(driver);
        driverRepository.save(driver);
    }

    private DriverDocumentEntity findDocumentById(Long documentId) throws DocumentValidationException {
        Optional<DriverDocumentEntity> documentEntityOptional = documentRepository.findById(documentId);
        if (!documentEntityOptional.isPresent()) {
            log.error("Document with id {} not found.", documentId);
            throw new DocumentValidationException("Document with id " + documentId + " not found.");
        }
        return documentEntityOptional.get();
    }

    private DriverEntity findDriverById(Long driverId) throws DriverRegistrationException {
        Optional<DriverEntity> driverEntityOptional = driverRepository.findById(driverId);
        if (!driverEntityOptional.isPresent()) {
            log.error("Driver with id {} not found.", driverId);
            throw new DriverRegistrationException("Driver with id " + driverId + " not found.");
        }
        return driverEntityOptional.get();
    }

    private void setDocumentStatusToCompleted(DriverDocumentEntity driverDocumentEntity) {
        driverDocumentEntity.setVerificationStatus(Status.COMPLETED);
    }

    private void setDriverStatusToCompleted(DriverEntity driver) {
        driver.setVerificationStatus(Status.COMPLETED);
    }
}
