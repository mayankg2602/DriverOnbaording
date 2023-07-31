package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.exception.DocumentValidationException;
import org.example.exception.DriverRegistrationException;
import org.example.model.request.DocumentUploadRequest;
import org.example.model.response.DriverDocuments;
import org.example.service.interfaces.IDriverDocumentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource to manage Driver's documents.
 */
@Path("/api/v1/drivers/{driverId}/documents")
@Produces(MediaType.APPLICATION_JSON)
public class DriverDocumentResource {

    private final IDriverDocumentService documentService;

    @Inject
    public DriverDocumentResource(IDriverDocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Endpoint to upload documents for a driver.
     *
     * @param driverId the driver id.
     * @param document the document to be uploaded.
     * @return the Response containing the uploaded document or appropriate error message.
     */
    @POST
    @UnitOfWork
    public Response uploadDocument(@PathParam("driverId") Long driverId, DocumentUploadRequest document) {
        try {
            DriverDocuments driverDocument = documentService.uploadDocument(driverId, document);
            if (driverDocument != null) {
                return Response.status(Response.Status.CREATED).entity(driverDocument).build();
            }
        } catch (DriverRegistrationException | DocumentValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).entity("Unable to upload document").build();
    }

    /**
     * Endpoint to retrieve documents by driver id.
     *
     * @param driverId the driver id.
     * @return the Response containing the retrieved documents or appropriate error message.
     */
    @GET
    @UnitOfWork
    public Response getDocumentsByDriverId(@PathParam("driverId") Long driverId) {
        try {
            DriverDocuments documents = documentService.getDocumentsByDriverId(driverId);
            if (documents != null) {
                return Response.ok(documents).build();
            }
        } catch (DriverRegistrationException | DocumentValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
