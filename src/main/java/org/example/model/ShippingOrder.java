package org.example.model;

import lombok.Data;
import org.example.dataModel.ShipmentEntity;
import org.example.model.enums.ShipmentStatus;
import org.example.model.enums.ShipmentType;

import java.time.LocalDateTime;

@Data
public class ShippingOrder {
    private LocalDateTime orderDate;
    private ShipmentStatus status;
    private String trackingNumber;
    private ShipmentType shipmentType;

    public ShippingOrder(ShipmentEntity shipment) {
        this.orderDate = shipment.getOrderDate();
        this.trackingNumber = shipment.getTrackingNumber();
        this.status = shipment.getShipmentStatus();
        this.shipmentType = shipment.getShipmentType();
    }

}
