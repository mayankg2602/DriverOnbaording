package org.example.model.request;

import lombok.Data;
import org.example.dataModel.DriverProfileEntity;
import org.example.model.DriverProfile;

import java.time.LocalDateTime;

@Data
public class DriverProfileRequest {
    private Long driverId;
    private String profilePicture;
    private String licenseInfo;
    private String vehicleInfo;
    private String insuranceInfo;
    private String otherInfo;
}
