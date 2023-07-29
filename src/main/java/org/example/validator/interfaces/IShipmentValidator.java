package org.example.validator.interfaces;

import org.example.model.request.ShipmentCreationRequest;

public interface IShipmentValidator {

    Long validateDriverId(Long driverId);

    String validateTrackingNumber(String trackingNumber);

    ShipmentCreationRequest validateRequest(ShipmentCreationRequest shipmentRequest);
}
