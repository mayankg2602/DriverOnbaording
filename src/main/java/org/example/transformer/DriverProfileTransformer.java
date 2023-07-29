package org.example.transformer;

import org.example.dataModel.DriverProfileEntity;
import org.example.model.request.DriverProfileRequest;

import java.time.LocalDateTime;

public class DriverProfileTransformer {
    public static DriverProfileEntity transformRequestToEntity(DriverProfileRequest driverProfileRequest) {
        DriverProfileEntity driverProfile = new DriverProfileEntity();
        driverProfile.setCreatedAt(LocalDateTime.now());
        driverProfile.setProfilePicture(driverProfileRequest.getProfilePicture());
        driverProfile.setUpdatedAt(LocalDateTime.now());
        driverProfile.setLicenseInfo(driverProfileRequest.getLicenseInfo());
        driverProfile.setInsuranceInfo(driverProfileRequest.getInsuranceInfo());
        driverProfile.setVehicleInfo(driverProfileRequest.getVehicleInfo());
        driverProfile.setOtherInfo(driverProfileRequest.getOtherInfo());
        return driverProfile;
    }

    public static void setProfileFromRequest(DriverProfileRequest driverProfileRequest, DriverProfileEntity profile) {
        profile.setProfilePicture(driverProfileRequest.getProfilePicture());
        profile.setLicenseInfo(driverProfileRequest.getLicenseInfo());
        profile.setVehicleInfo(driverProfileRequest.getVehicleInfo());
        profile.setInsuranceInfo(driverProfileRequest.getInsuranceInfo());
        profile.setOtherInfo(driverProfileRequest.getOtherInfo());
        profile.setUpdatedAt(LocalDateTime.now());
    }
}
