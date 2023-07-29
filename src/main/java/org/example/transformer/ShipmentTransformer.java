package org.example.transformer;

import org.example.dataModel.ShipmentEntity;
import org.example.model.enums.ShipmentStatus;
import org.example.model.request.ShipmentCreationRequest;

import java.time.LocalDateTime;

public class ShipmentTransformer {
    public static ShipmentEntity transformRequestToEntity(ShipmentCreationRequest shipmentRequest) {
        ShipmentEntity shipment = new ShipmentEntity();
        shipment.setShipmentType(shipmentRequest.getShipmentType());
        shipment.setShipmentStatus(ShipmentStatus.ORDERED);
        shipment.setOrderDate(LocalDateTime.now());
        shipment.setTrackingNumber(shipment.getTrackingNumber());
        shipment.setCreatedAt(LocalDateTime.now());
        shipment.setUpdatedAt(LocalDateTime.now());
        return shipment;
    }
}
