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

    public ShipmentRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public ShipmentEntity findShipmentByTrackingNumber(String trackingNumber) {
        log.info("Looking for shipment with tracking number: {}", trackingNumber);
        Query query = currentSession().createQuery("FROM ShipmentEntity WHERE trackingNumber = :trackingNumber");
        query.setParameter("trackingNumber", trackingNumber);

        Optional<ShipmentEntity> shipmentEntity = Optional.ofNullable(uniqueResult(query));
        return shipmentEntity.orElse(null);
    }
}
