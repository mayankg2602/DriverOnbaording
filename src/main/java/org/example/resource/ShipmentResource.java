package org.example.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.exception.DriverRegistrationException;
import org.example.exception.ShipmentException;
import org.example.service.interfaces.IShipmentService;
import org.example.model.response.ShippingOrder;
import org.example.model.response.ShippingOrders;
import org.example.model.request.ShipmentCreationRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/drivers/{driverId}/shipments")
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
    public Response createShipment(@PathParam("driverId") Long driverId, ShipmentCreationRequest shipmentRequest) {
        try {
            ShippingOrder shipment = shipmentService.createShipment(driverId, shipmentRequest);
            return Response.status(Response.Status.CREATED).entity(shipment).build();
        } catch (DriverRegistrationException | ShipmentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to create shipment due to an internal error").build();
        }
    }

    @GET
    @UnitOfWork
    public Response getDriverShipments(@PathParam("driverId") Long driverId) {
        try {
            ShippingOrders shippingOrders = shipmentService.getShipmentsForDriver(driverId);
            if (shippingOrders != null) {
                return Response.ok(shippingOrders).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No shipments found for the driver with id " + driverId).build();
            }
        } catch (DriverRegistrationException | ShipmentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch shipments due to an internal error").build();
        }
    }

    @GET
    @Path("/tracking/{trackingNumber}")
    @UnitOfWork
    public Response getShipmentByTrackingNumber(@PathParam("driverId") Long driverId, @PathParam("trackingNumber") String trackingNumber) {
        try {
            ShippingOrder shipment = shipmentService.getShipmentByTrackingNumber(driverId, trackingNumber);
            if (shipment != null) {
                return Response.ok(shipment).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No order found with tracking number " + trackingNumber).build();
            }
        } catch (ShipmentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch shipment by tracking number due to an internal error").build();
        }
    }
}
