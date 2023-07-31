package org.example.repository;

import io.dropwizard.hibernate.AbstractDAO;
import org.example.dataModel.DriverDocumentEntity;
import org.example.repository.interfaces.IDocumentRepository;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class DocumentRepository extends AbstractDAO<DriverDocumentEntity> implements IDocumentRepository {
    public DocumentRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Find a DriverDocumentEntity by its ID.
     * @param id ID of the DriverDocumentEntity to find.
     * @return An Optional with the DriverDocumentEntity if found, empty otherwise.
     */
    @Override
    public Optional<DriverDocumentEntity> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    /**
     * Save a DriverDocumentEntity to the database.
     * @param document The DriverDocumentEntity to be saved.
     */
    @Override
    public void save(DriverDocumentEntity document) {
        persist(document);
    }
}
