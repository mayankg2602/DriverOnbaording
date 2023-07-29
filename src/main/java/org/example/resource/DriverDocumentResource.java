package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.dataModel.DocumentEntity;
import org.example.manager.interfaces.IDocumentService;
import org.example.model.DriverDocument;
import org.example.model.DriverDocuments;
import org.example.model.request.DocumentUploadRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/drivers/documents")
@Produces(MediaType.APPLICATION_JSON)
public class DriverDocumentResource {

    private final IDocumentService documentService;

    @Inject
    public DriverDocumentResource(IDocumentService documentService) {
        this.documentService = documentService;
    }

    @POST
    @UnitOfWork
    public Response uploadDocument(DocumentUploadRequest document) {
        DriverDocuments driverDocument = documentService.uploadDocument(document);
        if (driverDocument != null) {
            return Response.status(Response.Status.CREATED).entity(driverDocument).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @UnitOfWork
    @Path("/{driver_id}")
    public Response getDocumentsByDriverId(@PathParam("driver_id") Long driverId) {
        DriverDocuments documents = documentService.getDocumentsByDriverId(driverId);
        if (documents != null) {
            return Response.ok(documents).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
