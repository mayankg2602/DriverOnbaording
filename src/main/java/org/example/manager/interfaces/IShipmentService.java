package org.example.manager.interfaces;

import org.example.model.ShippingOrder;
import org.example.model.ShippingOrders;
import org.example.model.request.ShipmentCreationRequest;

import java.util.List;

public interface IShipmentService {
    ShippingOrder createShipment(ShipmentCreationRequest shipmentRequest);

    ShippingOrder getShipmentByTrackingNumber(String trackingNumber);

    ShippingOrders getShipmentsForDriver(Long driverId);
}
