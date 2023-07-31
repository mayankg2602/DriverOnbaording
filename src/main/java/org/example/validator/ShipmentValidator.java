package org.example.validator;

import org.apache.commons.lang3.StringUtils;
import org.example.exception.ShipmentException;
import org.example.model.request.ShipmentCreationRequest;
import org.example.validator.interfaces.IShipmentValidator;

public class ShipmentValidator implements IShipmentValidator {

    /**
     * This method validates the tracking number.
     * @param trackingNumber the tracking number to validate
     * @return The validated tracking number
     * @throws ShipmentException if the tracking number is null or empty
     */
    @Override
    public String validateTrackingNumber(String trackingNumber) throws ShipmentException {
        if (StringUtils.isBlank(trackingNumber)) {
            throw new ShipmentException("Invalid tracking number: " + trackingNumber);
        }
        return trackingNumber;
    }

    /**
     * This method validates the ShipmentCreationRequest object.
     * @param shipmentRequest the ShipmentCreationRequest object to validate
     * @return The validated ShipmentCreationRequest object
     * @throws ShipmentException if the shipment type is missing or the tracking number is not provided
     */
    @Override
    public ShipmentCreationRequest validateRequest(ShipmentCreationRequest shipmentRequest) throws ShipmentException {
        if (shipmentRequest.getShipmentType() == null) {
            throw new ShipmentException("Shipment type not present.");
        }

        // validate tracking number
        validateTrackingNumber(shipmentRequest.getTrackingNumber());

        return shipmentRequest;
    }
}
