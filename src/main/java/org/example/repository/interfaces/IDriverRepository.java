package org.example.repository.interfaces;

import org.example.dataModel.DriverEntity;

import java.util.Optional;

/**
 * The IDriverRepository interface provides the contract for any class that will serve as a repository of
 * DriverEntity objects, defining standard functions to retrieve and store such objects.
 */
public interface IDriverRepository {

    /**
     * Saves a DriverEntity object into the repository.
     * It will update the existing entity if it already exists or create a new one.
     * @param driver The DriverEntity object to be saved.
     * @return The saved DriverEntity object.
     */
    DriverEntity save(DriverEntity driver);

    /**
     * Retrieves a DriverEntity object from the repository using the provided driverId.
     * @param driverId The id of the driver to be fetched.
     * @return An Optional containing the DriverEntity if found, empty otherwise.
     */
    Optional<DriverEntity> findById(Long driverId);
}
