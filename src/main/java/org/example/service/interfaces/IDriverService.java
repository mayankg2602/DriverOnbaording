package org.example.service.interfaces;

import org.example.exception.DriverRegistrationException;
import org.example.model.response.Driver;
import org.example.model.request.AvailabilityRequest;
import org.example.model.request.DriverCreationRequest;

/**
 * Interface for managing operations related to drivers.
 */
public interface IDriverService {

    /**
     * Registers a new driver.
     *
     * @param driverCreationRequest The request containing the details of the driver to be registered.
     * @throws DriverRegistrationException If an error occurs during driver registration.
     * @return The registered driver.
     */
    Driver registerDriver(DriverCreationRequest driverCreationRequest) throws DriverRegistrationException;

    /**
     * Updates the availability of a driver.
     *
     * @param driverId The ID of the driver for which to update the availability.
     * @param availabilityRequest The request containing the updated availability details.
     * @throws DriverRegistrationException If the driver ID is not valid.
     */
    void updateAvailability(Long driverId, AvailabilityRequest availabilityRequest) throws DriverRegistrationException;
}