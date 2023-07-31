package org.example.validator.interfaces;

import org.example.exception.ProfileManagementException;
import org.example.model.request.DriverProfileRequest;

/**
 * IDriverProfileValidator interface provides contract for validating the driver profile request.
 */
public interface IDriverProfileValidator {

    /**
     * Validates the driver profile request.
     * @param driverProfileRequest the driver profile request
     * @return The validated driver profile request
     * @throws ProfileManagementException if the validation fails
     */
    DriverProfileRequest validateDriverProfileRequest(DriverProfileRequest driverProfileRequest) throws ProfileManagementException;
}