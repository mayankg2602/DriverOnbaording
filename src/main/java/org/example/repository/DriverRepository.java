package org.example.repository;

import io.dropwizard.hibernate.AbstractDAO;
import org.example.dataModel.DriverEntity;
import org.example.repository.interfaces.IDriverRepository;
import org.hibernate.SessionFactory;

public class DriverRepository extends AbstractDAO<DriverEntity> implements IDriverRepository {

    public DriverRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * @param driver
     * @return
     */
    @Override
    public DriverEntity save(DriverEntity driver) {
        return persist(driver);
    }

    /**
     * @param driverId
     * @return
     */
    @Override
    public DriverEntity findById(Long driverId) {
        return get(driverId);
    }
}
