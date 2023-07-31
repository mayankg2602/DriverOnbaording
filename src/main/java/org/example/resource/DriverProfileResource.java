package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.exception.DriverRegistrationException;
import org.example.exception.ProfileManagementException;
import org.example.service.interfaces.IDriverProfileService;
import org.example.model.response.DriverProfile;
import org.example.model.request.DriverProfileRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Resource for Driver Profile related operations.
 * This class handles incoming HTTP requests and produce responses.
 */
@Path("/api/v1/drivers/{driverId}/profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DriverProfileResource {

    private final IDriverProfileService driverProfileService;

    /**
     * Constructor for dependency injection
     *
     * @param driverProfileService Service to handle business logic
     */
    @Inject
    public DriverProfileResource(IDriverProfileService driverProfileService) {
        this.driverProfileService = driverProfileService;
    }

    /**
     * API to fetch a driver's profile
     *
     * @param driverId The ID of the driver
     * @return The driver's profile or error response
     */
    @GET
    @UnitOfWork
    public Response getDriverProfile(@PathParam("driverId") Long driverId) {
        try {
            DriverProfile driverProfile = driverProfileService.getDriverProfileById(driverId);
            if (driverProfile != null) {
                return Response.ok(driverProfile).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Profile not found for given driver id").build();
            }
        } catch (DriverRegistrationException | ProfileManagementException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * API to update a driver's profile
     *
     * @param driverId The ID of the driver
     * @param driverProfileRequest The updated profile information
     * @return The updated driver's profile or error response
     */
    @PUT
    @UnitOfWork
    public Response updateDriverProfile(@PathParam("driverId") Long driverId, DriverProfileRequest driverProfileRequest) {
        try {
            DriverProfile updatedProfile = driverProfileService.updateDriverProfile(driverId, driverProfileRequest);
            return Response.ok(updatedProfile).build();
        } catch (DriverRegistrationException | ProfileManagementException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
