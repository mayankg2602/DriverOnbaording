package org.example.transformer;

import org.example.dataModel.DriverProfileEntity;
import org.example.model.request.DriverProfileRequest;
import org.example.model.response.DriverProfile;

import java.time.LocalDateTime;

public class DriverProfileTransformer {
    /**
     * Transforms a DriverProfileRequest object to a DriverProfileEntity object.
     *
     * @param driverProfileRequest The input DriverProfileRequest object.
     * @return A new DriverProfileEntity object with data from the request.
     */
    public static DriverProfileEntity transformRequestToEntity(DriverProfileRequest driverProfileRequest) {
        DriverProfileEntity driverProfile = new DriverProfileEntity();
        driverProfile.setCreatedAt(LocalDateTime.now());
        setProfileFromRequest(driverProfileRequest, driverProfile);
        return driverProfile;
    }

    /**
     * Updates a DriverProfileEntity object with data from a DriverProfileRequest object.
     *
     * @param driverProfileRequest The input DriverProfileRequest object.
     * @param profile              The existing DriverProfileEntity object to be updated.
     */
    public static void setProfileFromRequest(DriverProfileRequest driverProfileRequest, DriverProfileEntity profile) {
        profile.setProfilePicture(driverProfileRequest.getProfilePicture());
        profile.setLicenseInfo(driverProfileRequest.getLicenseInfo());
        profile.setVehicleInfo(driverProfileRequest.getVehicleInfo());
        profile.setInsuranceInfo(driverProfileRequest.getInsuranceInfo());
        profile.setOtherInfo(driverProfileRequest.getOtherInfo());
        profile.setUpdatedAt(LocalDateTime.now());
    }

    /**
     * Transforms a DriverProfileEntity object to a DriverProfile object.
     *
     * @param profileEntity The input DriverProfileEntity object.
     * @return A new DriverProfile object with data from the entity.
     */
    public static DriverProfile fromEntity(DriverProfileEntity profileEntity) {
        DriverProfile profile = new DriverProfile();
        if (profileEntity != null) {
            profile.setProfileId(profileEntity.getProfileId());
            profile.setProfilePicture(profileEntity.getProfilePicture());
            profile.setLicenseInfo(profileEntity.getLicenseInfo());
            profile.setVehicleInfo(profileEntity.getVehicleInfo());
            profile.setInsuranceInfo(profileEntity.getInsuranceInfo());
            profile.setOtherInfo(profileEntity.getOtherInfo());
            profile.setCreatedAt(profileEntity.getCreatedAt());
            profile.setUpdatedAt(profileEntity.getUpdatedAt());
        }
        return profile;
    }
}
