package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.exception.DriverRegistrationException;
import org.example.model.response.Driver;
import org.example.service.interfaces.IDriverService;
import org.example.model.request.DriverCreationRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The DriverResource class provides RESTful APIs to handle driver-related operations.
 */
@Path("/api/v1/drivers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DriverResource {

    private final IDriverService driverService;

    /**
     * Inject DriverService through constructor.
     *
     * @param driverService the driver service to use.
     */
    @Inject
    public DriverResource(IDriverService driverService) {
        this.driverService = driverService;
    }

    /**
     * API endpoint to register a new driver.
     *
     * @param driverRequest the new driver's details.
     * @return the response containing the registered driver's details or an error message.
     */
    @POST
    @UnitOfWork
    public Response registerDriver(DriverCreationRequest driverRequest) {
        try {
            Driver registeredDriver = driverService.registerDriver(driverRequest);
            return Response.status(Response.Status.CREATED).entity(registeredDriver).build();
        } catch (DriverRegistrationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
