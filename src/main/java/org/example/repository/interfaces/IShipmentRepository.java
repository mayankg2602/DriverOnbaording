package org.example.repository.interfaces;

import org.example.dataModel.ShipmentEntity;

import java.util.Optional;

/**
 * The IShipmentRepository interface provides the contract for any class that will serve as a repository of
 * ShipmentEntity objects, defining standard functions to retrieve such objects.
 */
public interface IShipmentRepository {

    /**
     * Retrieves a ShipmentEntity object from the repository using the provided trackingNumber.
     * @param trackingNumber The tracking number of the shipment to be fetched.
     * @return An Optional containing the ShipmentEntity if found, empty otherwise.
     */
    Optional<ShipmentEntity> findShipmentByTrackingNumber(Long driverId, String trackingNumber);
}
