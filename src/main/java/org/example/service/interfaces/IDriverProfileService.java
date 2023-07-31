package org.example.service.interfaces;

import org.example.exception.DriverRegistrationException;
import org.example.exception.ProfileManagementException;
import org.example.model.response.DriverProfile;
import org.example.model.request.DriverProfileRequest;

/**
 * Interface for managing operations related to driver profiles.
 */
public interface IDriverProfileService {

    /**
     * Updates a driver profile.
     *
     * @param driverId The ID of the driver for which to update the profile.
     * @param driverProfileRequest The request containing the updated profile details.
     * @throws DriverRegistrationException If the driver ID is not valid.
     * @throws ProfileManagementException If an error occurs during profile update.
     * @return The updated driver profile.
     */
    DriverProfile updateDriverProfile(Long driverId, DriverProfileRequest driverProfileRequest) throws DriverRegistrationException, ProfileManagementException;

    /**
     * Retrieves a driver profile.
     *
     * @param driverId The ID of the driver for which to retrieve the profile.
     * @throws DriverRegistrationException If the driver ID is not valid.
     * @throws ProfileManagementException If an error occurs during profile retrieval.
     * @return The retrieved driver profile.
     */
    DriverProfile getDriverProfileById(Long driverId) throws DriverRegistrationException, ProfileManagementException;
}