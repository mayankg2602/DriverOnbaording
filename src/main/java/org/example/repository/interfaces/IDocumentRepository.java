package org.example.repository.interfaces;

import org.example.dataModel.DocumentEntity;

public interface IDocumentRepository {
    DocumentEntity findById(Long id);

    void save(DocumentEntity document);
}
