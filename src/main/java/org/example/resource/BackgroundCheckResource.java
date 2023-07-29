package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.manager.interfaces.IBackgroundCheckService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/backgroundCheck")
@Produces(MediaType.APPLICATION_JSON)
public class BackgroundCheckResource {

    private final IBackgroundCheckService backgroundCheckService;

    @Inject
    public BackgroundCheckResource(IBackgroundCheckService backgroundCheckService) {
        this.backgroundCheckService = backgroundCheckService;
    }

    @POST
    @UnitOfWork
    @Path("/documents/{document_id}/approve")
    public Response approveDocument(@PathParam("document_id") Long documentId) {
        try {
            backgroundCheckService.approveDocument(documentId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @UnitOfWork
    @Path("/drivers/{driver_id}/approve")
    public Response approveDriver(@PathParam("driver_id") Long driverId) {
        try {
            backgroundCheckService.approveDriver(driverId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
