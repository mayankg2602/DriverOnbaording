package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dataModel.AvailabilityEntity;
import org.example.dataModel.DriverEntity;
import org.example.dataModel.DriverProfileEntity;
import org.example.exception.DriverRegistrationException;
import org.example.service.interfaces.IDriverService;
import org.example.model.request.AvailabilityRequest;
import org.example.model.request.DriverCreationRequest;
import org.example.model.response.Driver;
import org.example.repository.interfaces.IDriverRepository;
import org.example.transformer.DriverTransformer;
import org.example.validator.interfaces.IDriverValidator;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Service for managing operations related to Drivers.
 */
@Slf4j
public class DriverService implements IDriverService {

    private final IDriverRepository iDriverRepository;
    private final IDriverValidator driverRegistrationValidator;


    /**
     * Initializes a new instance of the DriverService class.
     *
     * @param iDriverRepository           Repository for managing driver data.
     * @param driverRegistrationValidator Validator for driver registration.
     */
    @Inject
    public DriverService(IDriverRepository iDriverRepository, IDriverValidator driverRegistrationValidator) {
        this.iDriverRepository = iDriverRepository;
        this.driverRegistrationValidator = driverRegistrationValidator;
    }

    /**
     * Register a new driver.
     *
     * @param driverCreationRequest Details for creating a new driver.
     * @return The newly registered driver.
     * @throws DriverRegistrationException If registration validation fails.
     */
    @Override
    public Driver registerDriver(DriverCreationRequest driverCreationRequest) throws DriverRegistrationException {
        driverCreationRequest = driverRegistrationValidator.validateRegistrationRequest(driverCreationRequest);
        DriverEntity driver = createDriverWithProfile(driverCreationRequest);
        iDriverRepository.save(driver);
        return new Driver(driver);
    }

    /**
     * Update the availability of a given driver ID.
     *
     * @param driverId            ID of the driver to update the availability for.
     * @param availabilityRequest Request containing the new availability status.
     * @throws DriverRegistrationException If the driver is not found.
     */
    @Override
    public void updateAvailability(Long driverId, AvailabilityRequest availabilityRequest) throws DriverRegistrationException {
        DriverEntity driver = findDriverById(driverId);
        updateDriverAvailability(availabilityRequest, driver);
        iDriverRepository.save(driver);
    }

    private DriverEntity findDriverById(Long driverId) throws DriverRegistrationException {
        Optional<DriverEntity> driverEntityOptional = iDriverRepository.findById(driverId);
        if (!driverEntityOptional.isPresent()) {
            log.error("Driver with id {} not found.", driverId);
            throw new DriverRegistrationException("Driver with id " + driverId + " not found.");
        }
        return driverEntityOptional.get();
    }

    private DriverEntity createDriverWithProfile(DriverCreationRequest driverCreationRequest) {
        DriverEntity driver = DriverTransformer.transformRequestToEntity(driverCreationRequest);
        DriverProfileEntity driverProfile = new DriverProfileEntity();
        driver.setProfile(driverProfile);
        return driver;
    }

    private void updateDriverAvailability(AvailabilityRequest availabilityRequest, DriverEntity driver) {
        AvailabilityEntity availabilityEntity =
                Optional.ofNullable(driver.getAvailability())
                .orElseGet(() -> createAvailabilityEntity(driver));

        if (availabilityEntity.isAvailabilityStatus() != availabilityRequest.getAvailabilityStatus()) {
            availabilityEntity.setAvailabilityStatus(availabilityRequest.getAvailabilityStatus());
        }
    }

    private AvailabilityEntity createAvailabilityEntity(DriverEntity driver) {
        AvailabilityEntity newAvailability = new AvailabilityEntity();
        driver.setAvailability(newAvailability);
        return newAvailability;
    }
}
