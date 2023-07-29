package org.example.validator;

import org.apache.commons.lang3.StringUtils;
import org.example.exception.AvailabilityException;
import org.example.exception.DriverRegistrationException;
import org.example.model.request.AvailabilityRequest;
import org.example.model.request.DriverCreationRequest;
import org.example.util.Util;
import org.example.validator.interfaces.IDriverValidator;

public class DriverValidator extends BaseValidator implements IDriverValidator {

    @Override
    public DriverCreationRequest validateRegistrationRequest(DriverCreationRequest registrationRequest) {
        if(StringUtils.isBlank(registrationRequest.getName())) {
            throw new DriverRegistrationException("Name can't be null or empty");
        }
        if(StringUtils.isBlank(registrationRequest.getPassword())) {
            throw new DriverRegistrationException("Password can't be null or empty");
        }
        if(StringUtils.isBlank(registrationRequest.getEmail())) {
            throw new DriverRegistrationException("Email can't be null or empty");
        }
        if(registrationRequest.getPhoneNumber() != null && !Util.isValidPhoneNumber(registrationRequest.getPhoneNumber())) {
            throw new DriverRegistrationException("Phone number is not valid");
        }
        return registrationRequest;
    }

    /**
     * @param availabilityRequest
     * @return
     */
    @Override
    public AvailabilityRequest validateAvailabilityRequest(AvailabilityRequest availabilityRequest) {
        if(availabilityRequest != null && availabilityRequest.getDriverId() != null) {
            return availabilityRequest;
        }
        throw new AvailabilityException("Either availabilityRequest or driverId is null");
    }


}
