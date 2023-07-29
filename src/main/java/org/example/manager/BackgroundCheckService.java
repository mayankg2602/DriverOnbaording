package org.example.manager;


import org.example.dataModel.DocumentEntity;
import org.example.dataModel.DriverEntity;
import org.example.manager.interfaces.IBackgroundCheckService;
import org.example.model.enums.Status;
import org.example.repository.DocumentRepository;
import org.example.repository.interfaces.IDriverRepository;
import org.example.validator.BaseValidator;

import javax.inject.Inject;

public class BackgroundCheckService implements IBackgroundCheckService {

    private DocumentRepository documentRepository;
    private IDriverRepository IDriverRepository;

    @Inject
    public BackgroundCheckService(DocumentRepository documentRepository, IDriverRepository IDriverRepository) {
        this.documentRepository = documentRepository;
        this.IDriverRepository = IDriverRepository;
    }

    @Override
    public void approveDocument(Long documentId) throws Exception {
        documentId = BaseValidator.validate(documentId);
        DocumentEntity document = documentRepository.findById(documentId);
        document.setVerificationStatus(Status.COMPLETED);
        documentRepository.save(document);
    }

    @Override
    public void approveDriver(Long driverId) throws Exception {
        driverId = BaseValidator.validate(driverId);
        DriverEntity driver = IDriverRepository.findById(driverId);
        driver.setVerificationStatus(Status.COMPLETED);
        IDriverRepository.save(driver);
    }
}
