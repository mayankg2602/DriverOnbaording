package org.example.manager;

import org.example.dataModel.DocumentEntity;
import org.example.dataModel.DriverEntity;
import org.example.exception.DriverRegistrationException;
import org.example.manager.interfaces.IDocumentService;
import org.example.model.DriverDocuments;
import org.example.model.request.DocumentUploadRequest;
import org.example.repository.interfaces.IDriverRepository;
import org.example.transformer.DocumentTransformer;
import org.example.validator.DriverDocumentValidator;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DocumentService implements IDocumentService {

    private final DriverDocumentValidator documentValidator;
    private final IDriverRepository IDriverRepository;


    @Inject
    public DocumentService(DriverDocumentValidator documentValidator, IDriverRepository IDriverRepository) {
        this.documentValidator = documentValidator;
        this.IDriverRepository = IDriverRepository;
    }


    @Override
    public DriverDocuments uploadDocument(DocumentUploadRequest documentUploadRequest) {
        documentUploadRequest = documentValidator.validateDriverDocumentUploadRequest(documentUploadRequest);

        DocumentUploadRequest finalDocumentUploadRequest = documentUploadRequest;
        List<DocumentEntity> documentEntities = documentUploadRequest.getDocuments().stream().map(documentUploaded -> {
            DocumentEntity document = DocumentTransformer.transformUploadRequestToEntity(documentUploaded);
            DriverEntity driver = Optional.ofNullable(IDriverRepository.findById(finalDocumentUploadRequest.getDriverId()))
                    .orElseThrow(() -> new DriverRegistrationException("Driver with id " + finalDocumentUploadRequest.getDriverId() + " not found."));
//            document.setDriver(driver);
            if (driver.getDocuments() == null) {
                driver.setDocuments(new ArrayList<>());
            }
            driver.getDocuments().add(document);
            IDriverRepository.save(driver);
            return document;
        }).collect(Collectors.toList());
        return new DriverDocuments(documentEntities);
    }

    @Override
    public DriverDocuments getDocumentsByDriverId(Long driverId) {
        driverId = documentValidator.validateDriverId(driverId);
        Long finalDriverId = driverId;
        DriverEntity driver = Optional.ofNullable(IDriverRepository.findById(driverId))
                .orElseThrow(() -> new DriverRegistrationException("Driver with id " + finalDriverId + " not found."));
        return new DriverDocuments(driver.getDocuments());
    }
}