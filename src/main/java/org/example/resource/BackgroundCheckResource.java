package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.exception.DocumentValidationException;
import org.example.exception.DriverRegistrationException;
import org.example.service.interfaces.IBackgroundCheckService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1/background-checks")
@Produces(MediaType.APPLICATION_JSON)
public class BackgroundCheckResource {

    private final IBackgroundCheckService backgroundCheckService;

    @Inject
    public BackgroundCheckResource(IBackgroundCheckService backgroundCheckService) {
        this.backgroundCheckService = backgroundCheckService;
    }

    /**
     * API to approve a document after background check.
     *
     * @param documentId The ID of the document to be approved.
     * @return A response indicating the result of the approval operation.
     */
    @POST
    @Path("/documents/{documentId}/approve")
    @UnitOfWork
    public Response approveDocument(@PathParam("documentId") Long documentId) {
        try {
            backgroundCheckService.approveDocument(documentId);
            return Response.ok().build();
        } catch (DocumentValidationException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while approving the document.")
                    .build();
        }
    }

    /**
     * API to approve a driver after background check.
     *
     * @param driverId The ID of the driver to be approved.
     * @return A response indicating the result of the approval operation.
     */
    @POST
    @Path("/drivers/{driverId}/approve")
    @UnitOfWork
    public Response approveDriver(@PathParam("driverId") Long driverId) {
        try {
            backgroundCheckService.approveDriver(driverId);
            return Response.ok().build();
        } catch (DriverRegistrationException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while approving the driver.")
                    .build();
        }
    }
}
