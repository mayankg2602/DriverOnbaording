package org.example.model.request;

import lombok.Data;
import org.example.model.enums.ShipmentType;

@Data
public class ShipmentCreationRequest {

    private Long driverId;
    private ShipmentType shipmentType;
    private String trackingNumber;
}