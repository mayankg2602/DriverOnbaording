package org.example.repository.interfaces;

import org.example.dataModel.ShipmentEntity;

import java.util.List;

public interface IShipmentRepository {
    ShipmentEntity findShipmentByTrackingNumber(String trackingNumber);
}
