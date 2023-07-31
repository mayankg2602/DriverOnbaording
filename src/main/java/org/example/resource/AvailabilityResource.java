package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.exception.DriverRegistrationException;
import org.example.model.request.AvailabilityRequest;
import org.example.service.interfaces.IDriverService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.example.constants.Constants.AN_ERROR_OCCURRED_WHILE_UPDATING_THE_DRIVER_S_AVAILABILITY_STATUS;

@Path("/api/v1/drivers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvailabilityResource {

    private final IDriverService driverService;

    @Inject
    public AvailabilityResource(IDriverService driverService) {
        this.driverService = driverService;
    }

    /**
     * API to update the availability status of a driver.
     *
     * @param driverId The ID of the driver whose availability status needs to be updated.
     * @param availabilityRequest The availability status request containing the new status.
     * @return A response indicating the result of the update operation.
     */
    @PUT
    @Path("/{driverId}/availability")
    @UnitOfWork
    public Response updateAvailability(@PathParam("driverId") Long driverId, AvailabilityRequest availabilityRequest) {
        try {
            driverService.updateAvailability(driverId, availabilityRequest);
            return Response.ok().build();
        } catch (DriverRegistrationException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(AN_ERROR_OCCURRED_WHILE_UPDATING_THE_DRIVER_S_AVAILABILITY_STATUS).build();
        }
    }
}
