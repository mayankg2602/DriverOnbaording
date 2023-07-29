package org.example.validator.interfaces;

import org.example.model.request.AvailabilityRequest;
import org.example.model.request.DriverCreationRequest;

public interface IDriverValidator {
    DriverCreationRequest validateRegistrationRequest(DriverCreationRequest driverCreationRequest);

    AvailabilityRequest validateAvailabilityRequest(AvailabilityRequest availabilityRequest);
}
