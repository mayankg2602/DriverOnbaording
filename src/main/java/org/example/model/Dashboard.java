package org.example.model;

import lombok.Data;
import org.example.dataModel.DriverEntity;
import org.example.dataModel.DocumentEntity;
import org.example.dataModel.ShipmentEntity;

import java.util.List;

@Data
public class Dashboard {
    private Driver driverInfo;
    private DriverDocuments documents;
    private ShippingOrders shipments;

}
