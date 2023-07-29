package org.example.manager.interfaces;

import org.example.model.Driver;
import org.example.model.request.AvailabilityRequest;
import org.example.model.request.DriverCreationRequest;

public interface IDriverService {
    Driver registerDriver(DriverCreationRequest driverCreationRequest);
    void updateAvailability(AvailabilityRequest availabilityRequest);
}
