package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dataModel.DriverEntity;
import org.example.dataModel.DriverProfileEntity;
import org.example.exception.DriverRegistrationException;
import org.example.exception.ProfileManagementException;
import org.example.service.interfaces.IDriverProfileService;
import org.example.model.request.DriverProfileRequest;
import org.example.model.response.DriverProfile;
import org.example.repository.interfaces.IDriverRepository;
import org.example.transformer.DriverProfileTransformer;
import org.example.validator.interfaces.IDriverProfileValidator;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Service for managing operations related to Driver's Profiles.
 */
@Slf4j
public class DriverProfileService implements IDriverProfileService {

    private final IDriverRepository iDriverRepository;
    private final IDriverProfileValidator driverProfileValidator;

    /**
     * Initializes a new instance of the DriverProfileService class.
     *
     * @param iDriverRepository Repository for managing driver data.
     * @param driverProfileValidator Validator for driver profile updates.
     */
    @Inject
    public DriverProfileService(IDriverRepository iDriverRepository, IDriverProfileValidator driverProfileValidator) {
        this.iDriverRepository = iDriverRepository;
        this.driverProfileValidator = driverProfileValidator;
    }

    /**
     * Update the profile of a given driver ID.
     *
     * @param driverId ID of the driver to update the profile for.
     * @param driverProfileRequest Profile update request details.
     * @return Updated profile of the specified driver.
     * @throws DriverRegistrationException If the driver is not found.
     * @throws ProfileManagementException  If profile update validation fails.
     */
    @Override
    public DriverProfile updateDriverProfile(Long driverId, DriverProfileRequest driverProfileRequest) throws DriverRegistrationException, ProfileManagementException {
        driverProfileRequest = driverProfileValidator.validateDriverProfileRequest(driverProfileRequest);
        DriverEntity driver = findDriverById(driverId);
        DriverProfileEntity profile = updateProfile(driver, driverProfileRequest);
        iDriverRepository.save(driver);
        return DriverProfileTransformer.fromEntity(profile);
    }

    /**
     * Retrieve the profile for a given driver ID.
     *
     * @param driverId ID of the driver to retrieve the profile for.
     * @return Profile of the specified driver.
     * @throws DriverRegistrationException If the driver is not found.
     * @throws ProfileManagementException  If the driver does not have a profile.
     */
    @Override
    public DriverProfile getDriverProfileById(Long driverId) throws DriverRegistrationException, ProfileManagementException {
        DriverEntity driver = findDriverById(driverId);
        if (driver.getProfile() == null) {
            log.error("No Profile currently attached to driver {}", driverId);
            throw new ProfileManagementException("No Profile currently attached to " + driverId + " driver");
        }
        return DriverProfileTransformer.fromEntity(driver.getProfile());
    }

    private DriverEntity findDriverById(Long driverId) throws DriverRegistrationException {
        Optional<DriverEntity> driverEntityOptional = iDriverRepository.findById(driverId);
        if (!driverEntityOptional.isPresent()) {
            log.error("Driver with id {} not found.", driverId);
            throw new DriverRegistrationException("Driver with id " + driverId + " not found.");
        }
        return driverEntityOptional.get();
    }

    private DriverProfileEntity updateProfile(DriverEntity driver, DriverProfileRequest driverProfileRequest) {
        DriverProfileEntity profile = driver.getProfile();

        if (profile == null) {
            profile = DriverProfileTransformer.transformRequestToEntity(driverProfileRequest);
        } else {
            DriverProfileTransformer.setProfileFromRequest(driverProfileRequest, profile);
        }
        driver.setProfile(profile);
        return profile;
    }
}
