package org.example.transformer;

import org.example.dataModel.DriverEntity;
import org.example.model.enums.Status;
import org.example.model.request.DriverCreationRequest;

import java.time.LocalDateTime;

public class DriverTransformer {
    /**
     * Transforms a DriverCreationRequest object to a DriverEntity object.
     *
     * @param driverCreationRequest The input DriverCreationRequest object.
     * @return A new DriverEntity object with data from the request.
     */
    public static DriverEntity transformRequestToEntity(DriverCreationRequest driverCreationRequest) {
        DriverEntity driver = new DriverEntity();
        driver.setAddress(driverCreationRequest.getAddress());
        driver.setPassword(driverCreationRequest.getPassword());
        driver.setName(driverCreationRequest.getName());
        driver.setEmail(driverCreationRequest.getEmail());
        driver.setPhoneNumber(driverCreationRequest.getPhoneNumber());
        driver.setRegistrationDate(LocalDateTime.now());
        driver.setCreatedAt(LocalDateTime.now());
        driver.setVerificationStatus(Status.PENDING);
        return driver;
    }
}
