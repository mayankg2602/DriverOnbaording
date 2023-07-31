package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dataModel.DriverEntity;
import org.example.exception.DriverRegistrationException;
import org.example.service.interfaces.IDashboardService;
import org.example.model.response.Dashboard;
import org.example.repository.interfaces.IDriverRepository;
import org.example.transformer.DashboardTransformer;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Service for managing operations related to the Driver's Dashboard.
 */
@Slf4j
public class DashboardService implements IDashboardService {

    private final IDriverRepository iDriverRepository;

    /**
     * Initializes a new instance of the DashboardService class.
     *
     * @param iDriverRepository The repository for managing driver data.
     */
    @Inject
    public DashboardService(IDriverRepository iDriverRepository) {
        this.iDriverRepository = iDriverRepository;
    }

    /**
     * Retrieve dashboard data for a given driver ID.
     *
     * @param driverId The ID of the driver for whom to retrieve dashboard data.
     * @return Dashboard data for the specified driver.
     * @throws DriverRegistrationException if the driver is not found.
     */
    @Override
    public Dashboard getDashboard(Long driverId) throws DriverRegistrationException {
        DriverEntity driver = findDriverById(driverId);
        return DashboardTransformer.transformEntityToResponseModel(driver);
    }

    private DriverEntity findDriverById(Long driverId) throws DriverRegistrationException {
        Optional<DriverEntity> driverEntityOptional = iDriverRepository.findById(driverId);
        if (!driverEntityOptional.isPresent()) {
            log.error("Driver with id {} not found.", driverId);
            throw new DriverRegistrationException("Driver with id " + driverId + " not found.");
        }
        return driverEntityOptional.get();
    }
}
