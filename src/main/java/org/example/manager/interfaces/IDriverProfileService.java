package org.example.manager.interfaces;

import org.example.model.DriverProfile;
import org.example.model.request.DriverProfileRequest;

public interface IDriverProfileService {
    DriverProfile updateDriverProfile(DriverProfileRequest driverProfileRequest);

    DriverProfile getDriverProfileById(Long driverId);
}
