package org.example.model;

import lombok.Data;
import org.example.dataModel.DriverProfileEntity;

import java.time.LocalDateTime;

@Data
public class DriverProfile {
    private Long profileId;
    private String profilePicture;
    private String licenseInfo;
    private String vehicleInfo;
    private String insuranceInfo;
    private String otherInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DriverProfile(DriverProfileEntity profileEntity) {
        this.profileId = profileEntity.getProfileId();
        this.profilePicture = profileEntity.getProfilePicture();
        this.licenseInfo = profileEntity.getLicenseInfo();
        this.vehicleInfo = profileEntity.getVehicleInfo();
        this.insuranceInfo = profileEntity.getInsuranceInfo();
        this.otherInfo = profileEntity.getOtherInfo();
        this.createdAt = profileEntity.getCreatedAt();
        this.updatedAt = profileEntity.getUpdatedAt();
    }
}
