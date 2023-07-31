package org.example.resource;

import org.example.exception.DriverRegistrationException;
import org.example.model.request.DriverCreationRequest;
import org.example.model.response.Driver;
import org.example.resource.DriverResource;
import org.example.service.interfaces.IDriverService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DriverResourceTest {

    private IDriverService driverService;
    private DriverResource driverResource;

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.setProperty("javax.ws.rs.ext.RuntimeDelegate", "org.glassfish.jersey.internal.RuntimeDelegateImpl");
    }


    @Before
    public void setUp() {
        driverService = Mockito.mock(IDriverService.class);
        driverResource = new DriverResource(driverService);
    }

    @Test
    public void testRegisterDriver() throws DriverRegistrationException {
        DriverCreationRequest driverRequest = new DriverCreationRequest();
        driverRequest.setName("Test name");
        driverRequest.setAddress("Test address");
        driverRequest.setEmail("test@example.com");
        driverRequest.setPhoneNumber("1234567890");

        Driver driver = new Driver();
        driver.setDriverId(1L);
        driver.setName(driverRequest.getName());
        driver.setAddress(driverRequest.getAddress());
        driver.setEmail(driverRequest.getEmail());
        driver.setPhoneNumber(driverRequest.getPhoneNumber());

        when(driverService.registerDriver(any(DriverCreationRequest.class))).thenReturn(driver);

        Response response = driverResource.registerDriver(driverRequest);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(driver, response.getEntity());

        verify(driverService, times(1)).registerDriver(any(DriverCreationRequest.class));
    }

    @Test
    public void testRegisterDriver_BadRequest() throws DriverRegistrationException {
        DriverCreationRequest driverRequest = new DriverCreationRequest();
        driverRequest.setName("Test name");
        driverRequest.setAddress("Test address");
        driverRequest.setEmail("test@example.com");
        driverRequest.setPhoneNumber("1234567890");

        when(driverService.registerDriver(any(DriverCreationRequest.class)))
                .thenThrow(new DriverRegistrationException("Invalid data"));

        Response response = driverResource.registerDriver(driverRequest);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("Invalid data", response.getEntity());

        verify(driverService, times(1)).registerDriver(any(DriverCreationRequest.class));
    }

    @Test
    public void testRegisterDriver_UnexpectedError() throws DriverRegistrationException {
        DriverCreationRequest driverRequest = new DriverCreationRequest();
        driverRequest.setName("Test name");
        driverRequest.setAddress("Test address");
        driverRequest.setEmail("test@example.com");
        driverRequest.setPhoneNumber("1234567890");

        when(driverService.registerDriver(any(DriverCreationRequest.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        Response response = driverResource.registerDriver(driverRequest);

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("Unexpected error", response.getEntity());

        verify(driverService, times(1)).registerDriver(any(DriverCreationRequest.class));
    }

}
