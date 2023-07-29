package org.example.validator.interfaces;

import org.example.model.request.DriverProfileRequest;

public interface IDriverProfileValidator {
    DriverProfileRequest validateDriverProfileRequest(DriverProfileRequest driverProfileRequest);

    Long validateDriverId(Long driverId);
}
