package org.example.manager;

import org.example.dataModel.DriverEntity;
import org.example.dataModel.ShipmentEntity;
import org.example.exception.DriverRegistrationException;
import org.example.manager.interfaces.IShipmentService;
import org.example.model.ShippingOrder;
import org.example.model.ShippingOrders;
import org.example.repository.interfaces.IDriverRepository;
import org.example.repository.interfaces.IShipmentRepository;
import org.example.model.request.ShipmentCreationRequest;
import org.example.transformer.ShipmentTransformer;
import org.example.validator.interfaces.IShipmentValidator;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;

public class ShipmentService implements IShipmentService {

    private final IShipmentRepository shipmentRepository;
    private final IShipmentValidator shipmentValidator;

    private final IDriverRepository IDriverRepository;

    @Inject
    public ShipmentService(IShipmentRepository shipmentRepository, IShipmentValidator shipmentValidator, IDriverRepository IDriverRepository) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentValidator = shipmentValidator;
        this.IDriverRepository = IDriverRepository;
    }

    @Override
    public ShippingOrder createShipment(ShipmentCreationRequest shipmentRequest) {
        shipmentRequest = shipmentValidator.validateRequest(shipmentRequest);

        ShipmentCreationRequest finalShipmentRequest = shipmentRequest;
        DriverEntity driver = Optional.ofNullable(IDriverRepository.findById(shipmentRequest.getDriverId()))
                .orElseThrow(() -> new DriverRegistrationException("Driver with id " + finalShipmentRequest.getDriverId() + " not found."));

        if(driver.getShipments() == null){
            driver.setShipments(new ArrayList<>());
        }
        ShipmentEntity shipment = ShipmentTransformer.transformRequestToEntity(shipmentRequest);
        driver.getShipments().add(shipment);
        IDriverRepository.save(driver);
        return new ShippingOrder(shipment);
    }

    @Override
    public ShippingOrder getShipmentByTrackingNumber(String trackingNumber) {
        trackingNumber = shipmentValidator.validateTrackingNumber(trackingNumber);
        ShipmentEntity shipment = shipmentRepository.findShipmentByTrackingNumber(trackingNumber);
        return new ShippingOrder(shipment);
    }

    @Override
    public ShippingOrders getShipmentsForDriver(Long driverId) {
        driverId = shipmentValidator.validateDriverId(driverId);

        Long finalDriverId = driverId;
        DriverEntity driver = Optional.ofNullable(IDriverRepository.findById(driverId))
                .orElseThrow(() -> new DriverRegistrationException("Driver with id " + finalDriverId + " not found."));

        return new ShippingOrders(driver.getShipments());
    }
}