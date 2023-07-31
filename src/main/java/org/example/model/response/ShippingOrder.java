package org.example.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.enums.ShipmentStatus;
import org.example.model.enums.ShipmentType;

import java.time.LocalDateTime;

/**
 * Represents a single shipping order in the response.
 */
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null fields when serializing to JSON
public class ShippingOrder {

    /**
     * The date the order was created.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("order_date")
    private final LocalDateTime orderDate;

    /**
     * The status of the shipment.
     */
    @JsonProperty("status")
    private final ShipmentStatus status;

    /**
     * The tracking number for the shipment.
     */
    @JsonProperty("tracking_number")
    private final String trackingNumber;

    /**
     * The type of the shipment.
     */
    @JsonProperty("shipment_type")
    private final ShipmentType shipmentType;
}
