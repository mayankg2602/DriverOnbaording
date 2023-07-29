package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.manager.interfaces.IDriverService;
import org.example.model.Driver;
import org.example.model.request.AvailabilityRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/drivers/{driver_id}/availability")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvailabilityResource {

    private final IDriverService driverService;

    @Inject
    public AvailabilityResource(IDriverService driverService) {
        this.driverService = driverService;
    }

    @PUT
    @UnitOfWork
    public Response updateAvailability(AvailabilityRequest availabilityRequest) {
        try {
            driverService.updateAvailability(availabilityRequest);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
