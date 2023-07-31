package org.example.transformer;

import org.example.dataModel.ShipmentEntity;
import org.example.model.enums.ShipmentStatus;
import org.example.model.request.ShipmentCreationRequest;
import org.example.model.response.ShippingOrder;
import org.example.model.response.ShippingOrders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ShipmentTransformer {
    /**
     * Transforms a ShipmentCreationRequest object to a ShipmentEntity object.
     *
     * @param shipmentRequest The input ShipmentCreationRequest object.
     * @return A new ShipmentEntity object with data from the request.
     */
    public static ShipmentEntity transformRequestToEntity(ShipmentCreationRequest shipmentRequest) {
        ShipmentEntity shipment = new ShipmentEntity();
        shipment.setShipmentType(shipmentRequest.getShipmentType());
        shipment.setShipmentStatus(ShipmentStatus.ORDERED);
        shipment.setOrderDate(LocalDateTime.now());
        shipment.setTrackingNumber(shipmentRequest.getTrackingNumber());
        shipment.setCreatedAt(LocalDateTime.now());
        shipment.setUpdatedAt(LocalDateTime.now());
        return shipment;
    }

    /**
     * Converts a ShipmentEntity object to a ShippingOrder object.
     *
     * @param shipment The input ShipmentEntity object.
     * @return A new ShippingOrder object with data from the entity.
     * @throws IllegalArgumentException if the input ShipmentEntity is null.
     */
    public static ShippingOrder toShippingOrderResponse(ShipmentEntity shipment) {
        if (shipment == null) {
            throw new IllegalArgumentException("Shipment entity cannot be null");
        }

        return new ShippingOrder(
                shipment.getOrderDate(),
                shipment.getShipmentStatus(),
                shipment.getTrackingNumber(),
                shipment.getShipmentType()
        );
    }

    /**
     * Transforms a list of ShipmentEntity objects to a ShippingOrders object.
     *
     * @param entities The list of ShipmentEntity objects to be transformed.
     * @return A new ShippingOrders object containing the transformed ShippingOrder objects.
     */
    public static ShippingOrders fromEntities(List<ShipmentEntity> entities) {
        List<ShippingOrder> shippingOrders = entities.stream()
                .map(ShipmentTransformer::toShippingOrderResponse)
                .collect(Collectors.toList());
        return new ShippingOrders(shippingOrders);
    }
}
