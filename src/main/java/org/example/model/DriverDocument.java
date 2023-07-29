package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dataModel.DocumentEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDocument {
    private Long documentId;
    private String documentType;
    private String documentUrl;

    public DriverDocument(DocumentEntity documentEntity) {
        this.documentId = documentEntity.getDocumentId();
        this.documentType = documentEntity.getDocumentType();
        this.documentUrl = documentEntity.getDocumentUrl();
    }
}
