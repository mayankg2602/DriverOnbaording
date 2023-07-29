package org.example.manager;

import org.example.dataModel.AvailabilityEntity;
import org.example.dataModel.DriverEntity;
import org.example.exception.DriverRegistrationException;
import org.example.manager.interfaces.IDriverService;
import org.example.model.Driver;
import org.example.model.request.DriverCreationRequest;
import org.example.model.request.AvailabilityRequest;
import org.example.repository.interfaces.IDriverRepository;
import org.example.transformer.DriverTransformer;
import org.example.validator.interfaces.IDriverValidator;

import javax.inject.Inject;
import java.util.Optional;

public class DriverService implements IDriverService {

    private final IDriverRepository IDriverRepository;
    private final IDriverValidator driverRegistrationValidator;

    @Inject
    public DriverService(IDriverRepository IDriverRepository, IDriverValidator driverRegistrationValidator) {
        this.IDriverRepository = IDriverRepository;
        this.driverRegistrationValidator = driverRegistrationValidator;
    }

    @Override
    public Driver registerDriver(DriverCreationRequest driverCreationRequest) {
        driverCreationRequest = driverRegistrationValidator.validateRegistrationRequest(driverCreationRequest);
        DriverEntity driver = DriverTransformer.transformRequestToEntity(driverCreationRequest);
        driver = IDriverRepository.save(driver);
        return new Driver(driver);
    }

    @Override
    public void updateAvailability(AvailabilityRequest availabilityRequest) {
        availabilityRequest = driverRegistrationValidator.validateAvailabilityRequest(availabilityRequest);
        AvailabilityRequest finalAvailabilityRequest = availabilityRequest;
        DriverEntity driver = Optional.ofNullable(IDriverRepository.findById(availabilityRequest.getDriverId()))
                .orElseThrow(() -> new DriverRegistrationException("Driver with id " + finalAvailabilityRequest.getDriverId() + " not found."));

        if(driver.getAvailability() != null && driver.getAvailability().isAvailabilityStatus() != availabilityRequest.isAvailabilityStatus()) {
            return ;
        }

        // Get the existing AvailabilityEntity or create a new one
        AvailabilityEntity availabilityEntity = Optional.ofNullable(driver.getAvailability())
                .orElseGet(() -> {
                    AvailabilityEntity newAvailability = new AvailabilityEntity();
//                    newAvailability.setDriverEntity(driver);
                    driver.setAvailability(newAvailability);
                    return newAvailability;
                });

        // Update the availability status based on the request
        availabilityEntity.setAvailabilityStatus(availabilityRequest.isAvailabilityStatus());

        // Save the updated entities
        IDriverRepository.save(driver);
    }
}
