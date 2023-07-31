package org.example.service.interfaces;

import org.example.exception.DriverRegistrationException;
import org.example.exception.ShipmentException;
import org.example.model.response.ShippingOrder;
import org.example.model.response.ShippingOrders;
import org.example.model.request.ShipmentCreationRequest;

/**
 * Interface for managing operations related to shipments.
 */
public interface IShipmentService {

    /**
     * Creates a new shipment.
     *
     * @param driverId The ID of the driver for which to create the shipment.
     * @param shipmentRequest The request containing the details of the shipment to be created.
     * @throws DriverRegistrationException If the driver ID is not valid.
     * @throws ShipmentException If an error occurs during shipment creation.
     * @return The created shipment.
     */
    ShippingOrder createShipment(Long driverId, ShipmentCreationRequest shipmentRequest) throws DriverRegistrationException, ShipmentException;

    /**
     * Retrieves a shipment by its tracking number.
     *
     * @param trackingNumber The tracking number of the shipment to be retrieved.
     * @throws ShipmentException If an error occurs during shipment retrieval.
     * @return The retrieved shipment.
     */
    ShippingOrder getShipmentByTrackingNumber(Long driverId, String trackingNumber) throws ShipmentException;

    /**
     * Retrieves all shipments for a driver.
     *
     * @param driverId The ID of the driver for which to retrieve the shipments.
     * @throws DriverRegistrationException If the driver ID is not valid.
     * @throws ShipmentException If an error occurs during shipment retrieval.
     * @return The retrieved shipments.
     */
    ShippingOrders getShipmentsForDriver(Long driverId) throws DriverRegistrationException, ShipmentException;
}