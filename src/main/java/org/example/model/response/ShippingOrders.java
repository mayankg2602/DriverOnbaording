package org.example.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Represents the response containing a list of shipping orders.
 */
@Getter
@AllArgsConstructor
@JsonPropertyOrder({"shippingOrders"})
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null fields when serializing to JSON
public class ShippingOrders {

    /**
     * The list of shipping orders.
     */
    @JsonProperty("shipping_orders")
    private final List<ShippingOrder> shippingOrders;
}
