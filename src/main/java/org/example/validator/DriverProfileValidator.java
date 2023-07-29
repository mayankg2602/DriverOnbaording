package org.example.validator;

import org.apache.commons.lang3.StringUtils;
import org.example.exception.ProfileManagementException;
import org.example.model.request.DriverProfileRequest;
import org.example.validator.interfaces.IDriverProfileValidator;

public class DriverProfileValidator implements IDriverProfileValidator {

    /**
     * @param driverId
     * @return
     */
    @Override
    public Long validateDriverId(Long driverId) {
        if(driverId == null) {
            throw new ProfileManagementException("driver id is null");
        }
        return driverId;
    }

    /**
     * @param driverProfileRequest
     * @return
     */
    @Override
    public DriverProfileRequest validateDriverProfileRequest(DriverProfileRequest driverProfileRequest) {
        if(driverProfileRequest == null) {
            throw new ProfileManagementException("request is null");
        }
        if(driverProfileRequest.getDriverId() == null) {
            throw new ProfileManagementException("driver id is null");
        }
        if(StringUtils.isBlank(driverProfileRequest.getVehicleInfo())) {
            throw new ProfileManagementException("vehicle info is null");
        }
        if(StringUtils.isBlank(driverProfileRequest.getLicenseInfo())) {
            throw new ProfileManagementException("license info is null");
        }
        if(StringUtils.isBlank(driverProfileRequest.getInsuranceInfo())) {
            throw new ProfileManagementException("insurance info is null");
        }
        return driverProfileRequest;
    }
}
