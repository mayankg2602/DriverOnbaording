package org.example.manager.interfaces;

import org.example.dataModel.DocumentEntity;
import org.example.model.DriverDocument;
import org.example.model.DriverDocuments;
import org.example.model.request.DocumentUploadRequest;

import java.util.List;

public interface IDocumentService {
    DriverDocuments uploadDocument(DocumentUploadRequest documentUploadRequest);

    DriverDocuments getDocumentsByDriverId(Long driverId);
}
