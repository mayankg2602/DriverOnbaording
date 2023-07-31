package org.example.service;

import org.example.dataModel.AvailabilityEntity;
import org.example.dataModel.DriverEntity;
import org.example.dataModel.DriverProfileEntity;
import org.example.exception.DriverRegistrationException;
import org.example.model.request.AvailabilityRequest;
import org.example.model.request.DriverCreationRequest;
import org.example.model.response.Driver;
import org.example.repository.interfaces.IDriverRepository;
import org.example.service.DriverService;
import org.example.validator.interfaces.IDriverValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class DriverServiceTest {

    private IDriverRepository iDriverRepository;
    private IDriverValidator driverRegistrationValidator;
    private DriverService driverService;

    @Before
    public void setUp() {
        iDriverRepository = Mockito.mock(IDriverRepository.class);
        driverRegistrationValidator = Mockito.mock(IDriverValidator.class);
        driverService = new DriverService(iDriverRepository, driverRegistrationValidator);
    }

    @Test
    public void testRegisterDriver() throws DriverRegistrationException {
        DriverCreationRequest request = new DriverCreationRequest();
        request.setName("Test name");
        request.setAddress("Test address");
        request.setEmail("test@example.com");
        request.setPhoneNumber("1234567890");

        when(driverRegistrationValidator.validateRegistrationRequest(any(DriverCreationRequest.class)))
                .thenReturn(request);

        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setDriverId(1L);
        driverEntity.setName(request.getName());
        driverEntity.setAddress(request.getAddress());
        driverEntity.setEmail(request.getEmail());
        driverEntity.setPhoneNumber(request.getPhoneNumber());
        driverEntity.setProfile(new DriverProfileEntity());

        when(iDriverRepository.save(any(DriverEntity.class))).thenReturn(driverEntity);

        Driver driver = driverService.registerDriver(request);

        assertEquals(driverEntity.getName(), driver.getName());
        assertEquals(driverEntity.getAddress(), driver.getAddress());
        assertEquals(driverEntity.getEmail(), driver.getEmail());

        verify(driverRegistrationValidator, times(1)).validateRegistrationRequest(any(DriverCreationRequest.class));
        verify(iDriverRepository, times(1)).save(any(DriverEntity.class));
    }

    @Test
    public void testUpdateAvailability() throws DriverRegistrationException {
        Long driverId = 1L;
        AvailabilityRequest availabilityRequest = new AvailabilityRequest();
        availabilityRequest.setAvailabilityStatus(true);

        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setDriverId(driverId);
        driverEntity.setAvailability(new AvailabilityEntity());

        when(iDriverRepository.findById(anyLong())).thenReturn(Optional.of(driverEntity));
        when(iDriverRepository.save(any(DriverEntity.class))).thenReturn(driverEntity);

        driverService.updateAvailability(driverId, availabilityRequest);

        assertTrue(driverEntity.getAvailability().isAvailabilityStatus());

        verify(iDriverRepository, times(1)).findById(anyLong());
        verify(iDriverRepository, times(1)).save(any(DriverEntity.class));
    }

    @Test(expected = DriverRegistrationException.class)
    public void testUpdateAvailability_DriverNotFound() throws DriverRegistrationException {
        Long driverId = 1L;
        AvailabilityRequest availabilityRequest = new AvailabilityRequest();
        availabilityRequest.setAvailabilityStatus(true);

        when(iDriverRepository.findById(anyLong())).thenReturn(Optional.empty());

        driverService.updateAvailability(driverId, availabilityRequest);

        verify(iDriverRepository, times(1)).findById(anyLong());
        verify(iDriverRepository, times(0)).save(any(DriverEntity.class));
    }
}
