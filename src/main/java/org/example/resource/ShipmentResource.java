package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.exception.ShipmentException;
import org.example.manager.interfaces.IShipmentService;
import org.example.model.ShippingOrder;
import org.example.model.ShippingOrders;
import org.example.model.request.ShipmentCreationRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/drivers/deviceShipments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShipmentResource {

    private final IShipmentService shipmentService;

    @Inject
    public ShipmentResource(IShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @POST
    @UnitOfWork
    public Response createShipment(ShipmentCreationRequest shipmentRequest) {
        try {
            ShippingOrder shipment = shipmentService.createShipment(shipmentRequest);
            return Response.status(Response.Status.CREATED).entity(shipment).build();
        } catch (ShipmentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @UnitOfWork
    @Path("/shipments/{id}")
    public Response getShipment(@PathParam("id") Long driverId) {
        ShippingOrders shippingOrders = shipmentService.getShipmentsForDriver(driverId);
        if (shippingOrders != null) {
            return Response.ok(shippingOrders).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/shipments/track/{number}")
    @UnitOfWork
    public Response getShipmentByTrackingNumber(@PathParam("number") String trackingNumber) {
        ShippingOrder shipment = shipmentService.getShipmentByTrackingNumber(trackingNumber);
        if (shipment != null) {
            return Response.ok(shipment).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
