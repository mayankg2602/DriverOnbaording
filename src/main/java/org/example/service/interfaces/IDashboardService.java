package org.example.service.interfaces;

import org.example.exception.DriverRegistrationException;
import org.example.model.response.Dashboard;

/**
 * Interface for managing operations related to dashboard.
 */
public interface IDashboardService {

    /**
     * Retrieves a dashboard.
     *
     * @param driverId The ID of the driver for which to retrieve the dashboard.
     * @throws DriverRegistrationException If the driver ID is not valid.
     * @return The retrieved dashboard.
     */
    Dashboard getDashboard(Long driverId) throws DriverRegistrationException;
}