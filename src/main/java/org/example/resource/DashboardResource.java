package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.manager.interfaces.IDashboardService;
import org.example.model.Dashboard;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/drivers/{driver_id}/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {

    private final IDashboardService dashboardService;

    @Inject
    public DashboardResource(IDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GET
    @UnitOfWork
    public Response getDashboard(@PathParam("driver_id") Long driverId) {
        try {
            Dashboard dashboard = dashboardService.getDashboard(driverId);
            return Response.ok(dashboard).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
