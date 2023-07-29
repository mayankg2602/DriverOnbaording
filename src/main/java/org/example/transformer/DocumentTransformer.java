package org.example.transformer;

import org.example.dataModel.DocumentEntity;
import org.example.model.Document;
import org.example.model.request.DocumentUploadRequest;

public class DocumentTransformer {
    public static DocumentEntity transformUploadRequestToEntity(Document document) {
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setDocumentUrl(document.getDocumentUrl());
        documentEntity.setDocumentType(document.getDocumentType());
        return documentEntity;
    }
}
