package org.example.model;

import lombok.Data;
import org.example.dataModel.DocumentEntity;

import java.util.ArrayList;
import java.util.List;

@Data
public class DriverDocuments {
    List<DriverDocument> documents;

    public DriverDocuments(List<DocumentEntity> documentEntities) {
        if(this.documents == null) {
            this.documents = new ArrayList<>();
        }
        documentEntities.forEach(documentEntity -> this.documents.add(new DriverDocument(documentEntity)));
    }
}
