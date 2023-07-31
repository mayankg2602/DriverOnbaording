package org.example.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enums.ShipmentType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

/**
 * This class represents the shipment creation request.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentCreationRequest {

    /**
     * The type of the shipment.
     */
    @NotNull(message = "Shipment type cannot be null")
    @JsonProperty("shipment_type")
    private ShipmentType shipmentType;

    /**
     * The tracking number of the shipment.
     */
    @NotEmpty(message = "Tracking number cannot be empty")
    @JsonProperty("tracking_number")
    private String trackingNumber;
}
