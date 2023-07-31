package org.example.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dataModel.DriverEntity;
import org.example.model.enums.Status;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"driver_id", "name", "email", "phone_number", "address", "registration_date", "is_available", "verification_status"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Driver {
    @JsonProperty("driver_id")
    private Long driverId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("registration_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;

    @JsonProperty("is_available")
    private Boolean available;

    @JsonProperty("verification_status")
    private Status verificationStatus;

    public Driver(DriverEntity driverEntity) {
        this.driverId = driverEntity.getDriverId();
        this.name = driverEntity.getName();
        this.email = driverEntity.getEmail();
        this.address = driverEntity.getAddress();
        this.verificationStatus = driverEntity.getVerificationStatus();
        this.phoneNumber = driverEntity.getPhoneNumber();
        this.registrationDate = driverEntity.getRegistrationDate();
        if(driverEntity.getAvailability() != null) {
            this.available = driverEntity.getAvailability().isAvailabilityStatus();
        }
    }
}
