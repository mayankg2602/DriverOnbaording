package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dataModel.DriverEntity;
import org.example.dataModel.ShipmentEntity;
import org.example.exception.DriverRegistrationException;
import org.example.exception.ShipmentException;
import org.example.model.response.ShippingOrder;
import org.example.model.response.ShippingOrders;
import org.example.model.enums.ShipmentType;
import org.example.model.request.ShipmentCreationRequest;
import org.example.repository.interfaces.IDriverRepository;
import org.example.repository.interfaces.IShipmentRepository;
import org.example.service.interfaces.IShipmentService;
import org.example.transformer.ShipmentTransformer;
import org.example.validator.interfaces.IShipmentValidator;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service for managing operations related to Shipments.
 */
@Slf4j
public class ShipmentService implements IShipmentService {

    private final IShipmentRepository shipmentRepository;
    private final IShipmentValidator shipmentValidator;
    private final IDriverRepository driverRepository;

    /**
     * Initializes a new instance of the ShipmentService class.
     *
     * @param shipmentRepository Repository for managing shipment data.
     * @param shipmentValidator Validator for shipment.
     * @param driverRepository Repository for managing driver data.
     */
    @Inject
    public ShipmentService(IShipmentRepository shipmentRepository, IShipmentValidator shipmentValidator, IDriverRepository driverRepository) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentValidator = shipmentValidator;
        this.driverRepository = driverRepository;
    }

    /**
     * Create a new shipment.
     *
     * @param driverId ID of the driver to assign the shipment to.
     * @param shipmentRequest Details for creating a new shipment.
     * @return The newly created shipment.
     * @throws DriverRegistrationException If the driver is not found.
     * @throws ShipmentException If validation fails.
     */
    @Override
    public ShippingOrder createShipment(Long driverId, ShipmentCreationRequest shipmentRequest) throws DriverRegistrationException, ShipmentException {
        shipmentRequest = shipmentValidator.validateRequest(shipmentRequest);
        DriverEntity driver = findDriverById(driverId);
        ShipmentEntity shipment = createOrUpdateShipment(shipmentRequest, driver);
        driverRepository.save(driver);
        return ShipmentTransformer.toShippingOrderResponse(shipment);
    }

    /**
     * Get a shipment by its tracking number.
     *
     * @param driverId ID of the driver to assign the shipment to.
     * @param trackingNumber Tracking number of the shipment to retrieve.
     * @return The retrieved shipment.
     * @throws ShipmentException If the shipment is not found.
     */
    @Override
    public ShippingOrder getShipmentByTrackingNumber(Long driverId, String trackingNumber) throws ShipmentException {
        trackingNumber = shipmentValidator.validateTrackingNumber(trackingNumber);
        Optional<ShipmentEntity> shipmentEntityOptional = shipmentRepository.findShipmentByTrackingNumber(driverId, trackingNumber);
        if (!shipmentEntityOptional.isPresent()) {
            log.error("No Shipments attached to tracking number {}", trackingNumber);
            throw new ShipmentException("No Shipments attached to tracking number " + trackingNumber);
        }
        return ShipmentTransformer.toShippingOrderResponse(shipmentEntityOptional.get());
    }

    /**
     * Get all shipments for a given driver ID.
     *
     * @param driverId ID of the driver to retrieve the shipments for.
     * @return All shipments for the given driver.
     * @throws DriverRegistrationException If the driver is not found.
     * @throws ShipmentException If no shipments are found for the driver.
     */
    @Override
    public ShippingOrders getShipmentsForDriver(Long driverId) throws DriverRegistrationException, ShipmentException {
        DriverEntity driver = findDriverById(driverId);
        if (driver.getShipments() == null || driver.getShipments().isEmpty()) {
            log.error("No Shipments attached to driver {}", driverId);
            throw new ShipmentException("No Shipments attached to driver " + driverId);
        }
        return ShipmentTransformer.fromEntities(driver.getShipments());
    }

    private DriverEntity findDriverById(Long driverId) throws DriverRegistrationException {
        Optional<DriverEntity> driverEntityOptional = driverRepository.findById(driverId);
        if (!driverEntityOptional.isPresent()) {
            log.error("Driver with id {} not found.", driverId);
            throw new DriverRegistrationException("Driver with id " + driverId + " not found.");
        }
        return driverEntityOptional.get();
    }

    private ShipmentEntity createOrUpdateShipment(ShipmentCreationRequest shipmentRequest, DriverEntity driver) {
        Map<ShipmentType, ShipmentEntity> map = createShipmentMap(driver);
        ShipmentEntity shipment = map.get(shipmentRequest.getShipmentType());
        if (shipment != null) {
            updateShipment(shipment);
        } else {
            shipment = createNewShipment(shipmentRequest, driver);
        }
        return shipment;
    }

    private Map<ShipmentType, ShipmentEntity> createShipmentMap(DriverEntity driver) {
        Map<ShipmentType, ShipmentEntity> map = new HashMap<>();
        if (driver.getShipments() != null) {
            driver.getShipments().forEach(shipmentEntity -> map.put(shipmentEntity.getShipmentType(), shipmentEntity));
        }
        return map;
    }

    private void updateShipment(ShipmentEntity shipment) {
        shipment.setUpdatedAt(LocalDateTime.now());
        shipment.setTrackingNumber(shipment.getTrackingNumber());
    }

    private ShipmentEntity createNewShipment(ShipmentCreationRequest shipmentRequest, DriverEntity driver) {
        ShipmentEntity shipment = ShipmentTransformer.transformRequestToEntity(shipmentRequest);
        driver.getShipments().add(shipment);
        return shipment;
    }
}
