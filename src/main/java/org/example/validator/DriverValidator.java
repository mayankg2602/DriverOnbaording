package org.example.validator;

import org.apache.commons.lang3.StringUtils;
import org.example.exception.DriverRegistrationException;
import org.example.model.request.DriverCreationRequest;
import org.example.util.Util;
import org.example.validator.interfaces.IDriverValidator;

public class DriverValidator implements IDriverValidator {

    /**
     * This method validates the DriverCreationRequest object.
     * @param registrationRequest DriverCreationRequest object to validate.
     * @return The validated DriverCreationRequest object.
     * @throws DriverRegistrationException if any of the required driver information is missing or invalid.
     */
    @Override
    public DriverCreationRequest validateRegistrationRequest(DriverCreationRequest registrationRequest) throws DriverRegistrationException {
        // Check if the name is not null or empty
        if (StringUtils.isBlank(registrationRequest.getName())) {
            throw new DriverRegistrationException("Name can't be null or empty.");
        }

        // Check if the password is not null or empty
        if (StringUtils.isBlank(registrationRequest.getPassword())) {
            throw new DriverRegistrationException("Password can't be null or empty.");
        }

        // Check if the email is not null or empty
        if (StringUtils.isBlank(registrationRequest.getEmail())) {
            throw new DriverRegistrationException("Email can't be null or empty.");
        }

        // Check if the phone number is valid
        validatePhoneNumber(registrationRequest.getPhoneNumber());

        return registrationRequest;
    }

    /**
     * This method validates the phone number.
     * @param phoneNumber The phone number string to validate.
     * @throws DriverRegistrationException if the phone number is invalid.
     */
    private void validatePhoneNumber(String phoneNumber) throws DriverRegistrationException {
        if (phoneNumber != null && !Util.isValidPhoneNumber(phoneNumber)) {
            throw new DriverRegistrationException("Phone number is not valid.");
        }
    }
}
