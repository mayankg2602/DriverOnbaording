package org.example.validator.interfaces;

import org.example.exception.ShipmentException;
import org.example.model.request.ShipmentCreationRequest;

/**
 * IShipmentValidator interface provides contract for validating the shipment tracking number and creation request.
 */
public interface IShipmentValidator {

    /**
     * Validates the shipment tracking number.
     * @param trackingNumber the tracking number
     * @return The validated tracking number
     * @throws ShipmentException if the validation fails
     */
    String validateTrackingNumber(String trackingNumber) throws ShipmentException;

    /**
     * Validates the shipment creation request.
     * @param shipmentRequest the shipment creation request
     * @return The validated shipment creation request
     * @throws ShipmentException if the validation fails
     */
    ShipmentCreationRequest validateRequest(ShipmentCreationRequest shipmentRequest) throws ShipmentException;
}




