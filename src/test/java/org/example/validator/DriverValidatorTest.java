package org.example.validator;

import org.example.exception.DriverRegistrationException;
import org.example.model.request.DriverCreationRequest;
import org.example.validator.DriverValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DriverValidatorTest {

    private DriverValidator driverValidator;

    @Before
    public void setUp() {
        driverValidator = new DriverValidator();
    }

    @Test
    public void testValidateRegistrationRequest() throws DriverRegistrationException {
        DriverCreationRequest request = new DriverCreationRequest();
        request.setName("Test name");
        request.setPassword("Test password");
        request.setEmail("test@example.com");
        request.setPhoneNumber("9686429935");

        DriverCreationRequest result = driverValidator.validateRegistrationRequest(request);

        assertEquals(request.getName(), result.getName());
        assertEquals(request.getPassword(), result.getPassword());
        assertEquals(request.getEmail(), result.getEmail());
        assertEquals(request.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test(expected = DriverRegistrationException.class)
    public void testValidateRegistrationRequest_EmptyName() throws DriverRegistrationException {
        DriverCreationRequest request = new DriverCreationRequest();
        request.setName("");
        request.setPassword("Test password");
        request.setEmail("test@example.com");
        request.setPhoneNumber("1234567890");

        driverValidator.validateRegistrationRequest(request);
    }

    @Test(expected = DriverRegistrationException.class)
    public void testValidateRegistrationRequest_EmptyPassword() throws DriverRegistrationException {
        DriverCreationRequest request = new DriverCreationRequest();
        request.setName("Test name");
        request.setPassword("");
        request.setEmail("test@example.com");
        request.setPhoneNumber("1234567890");

        driverValidator.validateRegistrationRequest(request);
    }

    @Test(expected = DriverRegistrationException.class)
    public void testValidateRegistrationRequest_EmptyEmail() throws DriverRegistrationException {
        DriverCreationRequest request = new DriverCreationRequest();
        request.setName("Test name");
        request.setPassword("Test password");
        request.setEmail("");
        request.setPhoneNumber("1234567890");

        driverValidator.validateRegistrationRequest(request);
    }

    @Test(expected = DriverRegistrationException.class)
    public void testValidateRegistrationRequest_InvalidPhoneNumber() throws DriverRegistrationException {
        DriverCreationRequest request = new DriverCreationRequest();
        request.setName("Test name");
        request.setPassword("Test password");
        request.setEmail("test@example.com");
        request.setPhoneNumber("12345");

        driverValidator.validateRegistrationRequest(request);
    }
}
