package org.example.repository;

import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;
import org.example.dataModel.ShipmentEntity;
import org.example.repository.interfaces.IShipmentRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Optional;

@Slf4j
public class ShipmentRepository extends AbstractDAO<ShipmentEntity> implements IShipmentRepository {

    public static final String QUERY_FETCH_SHIPMENT = "FROM ShipmentEntity WHERE trackingNumber = :trackingNumber AND driver_id = :driverId";

    public ShipmentRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Find a ShipmentEntity by its tracking number.
     * @param trackingNumber tracking number of the ShipmentEntity to find.
     * @return An Optional with the ShipmentEntity if found, empty otherwise.
     */
    @Override
    public Optional<ShipmentEntity> findShipmentByTrackingNumber(Long driverId, String trackingNumber) {
        log.info("Looking for shipment with tracking number: {} and driver id: {}", trackingNumber, driverId);
        Query<ShipmentEntity> query = currentSession().createQuery(QUERY_FETCH_SHIPMENT, ShipmentEntity.class);
        query.setParameter("trackingNumber", trackingNumber);
        query.setParameter("driverId", driverId);
        return Optional.ofNullable(uniqueResult(query));
    }
}