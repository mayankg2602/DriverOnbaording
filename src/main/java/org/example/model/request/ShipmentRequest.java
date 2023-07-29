package org.example.model.request;

import lombok.Data;

@Data
public class ShipmentRequest {
    private Long driverId;
    private String trackingNumber;
    private String shipmentStatus;
    private String shipmentType;
}
