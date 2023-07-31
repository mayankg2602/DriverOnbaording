package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.exception.DriverRegistrationException;
import org.example.service.interfaces.IDashboardService;
import org.example.model.response.Dashboard;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A resource class representing Dashboard APIs.
 * Base path for APIs in this class: /api/driver/{driverId}/dashboard.
 */
@Path("/api/driver/{driverId}/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {

    private final IDashboardService dashboardService;

    /**
     * Constructor injection for IDashboardService dependency.
     */
    @Inject
    public DashboardResource(IDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    /**
     * Fetch dashboard details for a given driver.
     * HTTP GET method to /api/driver/{driverId}/dashboard.
     *
     * @param driverId Id of the driver.
     * @return HTTP Response containing Dashboard details.
     */
    @GET
    @UnitOfWork
    public Response getDashboard(@PathParam("driverId") Long driverId) {
        try {
            Dashboard dashboard = dashboardService.getDashboard(driverId);
            return Response.ok(dashboard).build();
        } catch (DriverRegistrationException e) {
            // Handle specific exceptions before general ones for better error response
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            // Catch general exception to handle unexpected errors
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
