package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.model.DriverProfile;
import org.example.manager.interfaces.IDriverProfileService;
import org.example.model.request.DriverProfileRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/drivers/{driver_id}/profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DriverProfileResource {

    private final IDriverProfileService driverProfileService;

    @Inject
    public DriverProfileResource(IDriverProfileService driverProfileService) {
        this.driverProfileService = driverProfileService;
    }

    @GET
    @UnitOfWork
    public Response getDriverProfile(@PathParam("driver_id") Long driverId) {
        DriverProfile driverProfile = driverProfileService.getDriverProfileById(driverId);
        if (driverProfile != null) {
            return Response.ok(driverProfile).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @UnitOfWork
    public Response updateDriverProfile(DriverProfileRequest driverProfileRequest) {
        try {
            DriverProfile updatedProfile = driverProfileService.updateDriverProfile(driverProfileRequest);
            return Response.ok(updatedProfile).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
