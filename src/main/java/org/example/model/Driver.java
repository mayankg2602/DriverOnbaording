package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dataModel.DriverEntity;
import org.example.model.enums.Status;
import org.example.model.request.DriverCreationRequest;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Driver {
    private Long driverId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime registrationDate;
    private boolean available;
    private String password;
    private Status verificationStatus;



    public Driver(DriverEntity driverEntity) {
        this.driverId = driverEntity.getDriverId();
        this.name = driverEntity.getName();
        this.email = driverEntity.getEmail();
        this.address = driverEntity.getAddress();
        this.password = driverEntity.getPassword();
        this.verificationStatus = driverEntity.getVerificationStatus();
        this.phoneNumber = driverEntity.getPhoneNumber();
        this.registrationDate = driverEntity.getRegistrationDate();
        if(driverEntity.getAvailability() != null) {
            this.available = driverEntity.getAvailability().isAvailabilityStatus();
        }
    }
}
