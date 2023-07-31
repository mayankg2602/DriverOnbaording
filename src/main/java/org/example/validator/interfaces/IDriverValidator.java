package org.example.validator.interfaces;

import org.example.exception.DriverRegistrationException;
import org.example.model.request.DriverCreationRequest;

/**
 * IDriverValidator interface provides contract for validating the driver registration request.
 */
public interface IDriverValidator {

    /**
     * Validates the driver registration request.
     * @param driverCreationRequest the driver registration request
     * @return The validated driver registration request
     * @throws DriverRegistrationException if the validation fails
     */
    DriverCreationRequest validateRegistrationRequest(DriverCreationRequest driverCreationRequest) throws DriverRegistrationException;
}