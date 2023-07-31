package org.example.validator;

import org.apache.commons.lang3.StringUtils;
import org.example.exception.ProfileManagementException;
import org.example.model.request.DriverProfileRequest;
import org.example.validator.interfaces.IDriverProfileValidator;

public class DriverProfileValidator implements IDriverProfileValidator {

    /**
     * This method validates the DriverProfileRequest object.
     * @param driverProfileRequest DriverProfileRequest object to validate.
     * @return The validated DriverProfileRequest object.
     * @throws ProfileManagementException if any of the required field information is missing.
     */
    @Override
    public DriverProfileRequest validateDriverProfileRequest(DriverProfileRequest driverProfileRequest) throws ProfileManagementException {
        // Validate if the driver profile request is not null
        if (driverProfileRequest == null) {
            throw new ProfileManagementException("Driver profile request cannot be null.");
        }

        // Validate vehicle info
        validateInfo(driverProfileRequest.getVehicleInfo(), "Vehicle info cannot be null or blank.");

        // Validate license info
        validateInfo(driverProfileRequest.getLicenseInfo(), "License info cannot be null or blank.");

        // Validate insurance info
        validateInfo(driverProfileRequest.getInsuranceInfo(), "Insurance info cannot be null or blank.");

        return driverProfileRequest;
    }

    /**
     * This method validates if the given info is not blank.
     * @param info String to validate.
     * @param errorMessage The error message in case the validation fails.
     * @throws ProfileManagementException if the info is blank.
     */
    private void validateInfo(String info, String errorMessage) throws ProfileManagementException {
        if (StringUtils.isBlank(info)) {
            throw new ProfileManagementException(errorMessage);
        }
    }
}
