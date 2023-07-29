package org.example.repository;

import io.dropwizard.hibernate.AbstractDAO;
import org.example.dataModel.DocumentEntity;
import org.example.repository.interfaces.IDocumentRepository;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class DocumentRepository extends AbstractDAO<DocumentEntity> implements IDocumentRepository {
    public DocumentRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public DocumentEntity findById(Long id) {
        return get(id);
    }

    /**
     * @param document
     */
    @Override
    public void save(DocumentEntity document) {
        save(document);
    }


}