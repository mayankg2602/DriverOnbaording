package org.example.transformer;

import org.example.dataModel.DriverEntity;
import org.example.model.response.*;

import java.util.Optional;

public class DashboardTransformer {
    /**
     * Transforms a DriverEntity object to a Dashboard response model.
     *
     * @param driver The input DriverEntity object.
     * @return A new Dashboard object with data from the entity, or null if the input driver is null.
     */
    public static Dashboard transformEntityToResponseModel(DriverEntity driver) {
        if (driver == null) {
            return null;
        }

        Dashboard dashboard = new Dashboard();

        dashboard.setDriverInfo(new Driver(driver));

        // Transform documents if they are not empty
        Optional.ofNullable(driver.getDocuments())
                .filter(documents -> !documents.isEmpty())
                .ifPresent(documents -> dashboard.setDocuments(new DriverDocuments(documents)));

        // Transform shipments if they are not empty
        Optional.ofNullable(driver.getShipments())
                .filter(shipments -> !shipments.isEmpty())
                .ifPresent(shipments -> dashboard.setShipments(ShipmentTransformer.fromEntities(shipments)));

        // Transform driver profile if available
        Optional.ofNullable(driver.getProfile())
                .ifPresent(profile -> dashboard.setDriverProfile(DriverProfileTransformer.fromEntity(profile)));

        // Set driver availability
        Optional.ofNullable(driver.getAvailability())
                .ifPresent(availability -> dashboard.setAvailable(availability.isAvailabilityStatus()));

        return dashboard;
    }
}
