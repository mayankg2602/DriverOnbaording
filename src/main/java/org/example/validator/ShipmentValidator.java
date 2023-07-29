package org.example.validator;

import org.apache.commons.lang3.StringUtils;
import org.example.exception.ShipmentException;
import org.example.model.request.ShipmentCreationRequest;
import org.example.validator.interfaces.IShipmentValidator;

public class ShipmentValidator implements IShipmentValidator {
    /**
     * @param driverId
     * @return
     */
    @Override
    public Long validateDriverId(Long driverId) {
        if (driverId == null) {
            throw new ShipmentException("driver Id is null");
        }
        return driverId;
    }

    /**
     * @param trackingNumber
     * @return
     */
    @Override
    public String validateTrackingNumber(String trackingNumber) {
        if (StringUtils.isBlank(trackingNumber)) {
            throw new ShipmentException("invalid tracking number: " + trackingNumber);
        }
        return trackingNumber;
    }

    /**
     * @param shipmentRequest
     * @return
     */
    @Override
    public ShipmentCreationRequest validateRequest(ShipmentCreationRequest shipmentRequest) {
        if (shipmentRequest.getShipmentType() == null) {
            throw new ShipmentException("shipment type not present");
        }
        if (StringUtils.isBlank(shipmentRequest.getTrackingNumber())) {
            throw new ShipmentException("Tracking number is necessary and should be provided");
        }
        if (shipmentRequest.getDriverId() != null) {
            throw new ShipmentException("driver id is null");
        }
        return shipmentRequest;
    }
}
