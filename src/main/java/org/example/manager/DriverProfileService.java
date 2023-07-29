package org.example.manager;

import org.example.dataModel.DriverEntity;
import org.example.dataModel.DriverProfileEntity;
import org.example.exception.DriverRegistrationException;
import org.example.manager.interfaces.IDriverProfileService;
import org.example.repository.interfaces.IDriverRepository;
import org.example.transformer.DriverProfileTransformer;
import org.example.model.DriverProfile;
import org.example.model.request.DriverProfileRequest;
import org.example.validator.interfaces.IDriverProfileValidator;

import javax.inject.Inject;
import java.util.Optional;

public class DriverProfileService implements IDriverProfileService {

    private final IDriverRepository IDriverRepository;
    private final IDriverProfileValidator driverProfileValidator;

    @Inject
    public DriverProfileService(IDriverRepository IDriverRepository, IDriverProfileValidator driverProfileValidator) {
        this.IDriverRepository = IDriverRepository;
        this.driverProfileValidator = driverProfileValidator;
    }

    /**
     * @param driverProfileRequest
     * @return
     */
    @Override
    public DriverProfile updateDriverProfile(DriverProfileRequest driverProfileRequest) {
        driverProfileRequest = driverProfileValidator.validateDriverProfileRequest(driverProfileRequest);
        DriverProfileRequest finalDriverProfileRequest = driverProfileRequest;
        DriverEntity driver = Optional.ofNullable(IDriverRepository.findById(driverProfileRequest.getDriverId()))
                .orElseThrow(() -> new DriverRegistrationException("Driver with id " + finalDriverProfileRequest.getDriverId() + " not found."));
        DriverProfileEntity profile = driver.getProfile();

        if (profile == null) {
            profile = DriverProfileTransformer.transformRequestToEntity(driverProfileRequest);
        } else {
            // Updating the existing driver's profile
            DriverProfileTransformer.setProfileFromRequest(driverProfileRequest, profile);
        }
        driver.setProfile(profile);
        IDriverRepository.save(driver);
        return new DriverProfile(profile);
    }

    /**
     * @param driverId
     * @return
     */
    @Override
    public DriverProfile getDriverProfileById(Long driverId) {
        driverId = driverProfileValidator.validateDriverId(driverId);
        Long finalDriverId = driverId;
        DriverEntity driver = Optional.ofNullable(IDriverRepository.findById(driverId))
                .orElseThrow(() -> new DriverRegistrationException("Driver with id " + finalDriverId + " not found."));
        return new DriverProfile(driver.getProfile());
    }
}
