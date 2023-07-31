package org.example.repository.interfaces;

import org.example.dataModel.DriverDocumentEntity;

import java.util.Optional;

/**
 * The IDocumentRepository interface defines the contract for any class that wishes to serve as a repository of
 * DriverDocumentEntity objects, with standard functions to retrieve and store such objects.
 */
public interface IDocumentRepository {

    /**
     * Fetches a DriverDocumentEntity object from the repository based on the provided documentId.
     * @param id The id of the document to be fetched.
     * @return An Optional containing the DriverDocumentEntity if found, empty otherwise.
     */
    Optional<DriverDocumentEntity> findById(Long id);

    /**
     * Persists a DriverDocumentEntity object into the repository.
     * @param document The DriverDocumentEntity object to be stored.
     */
    void save(DriverDocumentEntity document);
}
