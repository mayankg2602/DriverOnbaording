package org.example.repository;

import io.dropwizard.hibernate.AbstractDAO;
import org.example.dataModel.DriverEntity;
import org.example.repository.interfaces.IDriverRepository;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class DriverRepository extends AbstractDAO<DriverEntity> implements IDriverRepository {
    public DriverRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Save a DriverEntity to the database.
     * @param driver The DriverEntity to be saved.
     * @return The saved DriverEntity.
     */
    @Override
    public DriverEntity save(DriverEntity driver) {
        return persist(driver);
    }

    /**
     * Find a DriverEntity by its ID.
     * @param driverId ID of the DriverEntity to find.
     * @return An Optional with the DriverEntity if found, empty otherwise.
     */
    @Override
    public Optional<DriverEntity> findById(Long driverId) {
        return Optional.ofNullable(get(driverId));
    }
}
