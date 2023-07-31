package org.example.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"driver_info", "documents", "shipments", "driver_profile", "available"})
@JsonInclude(JsonInclude.Include.NON_NULL) // Excludes the fields with null values from the JSON output
public class Dashboard {

    @JsonProperty("driver_info") // Formats JSON property name as 'driver_info'
    private Driver driverInfo;

    @JsonProperty("documents")
    private DriverDocuments documents;

    @JsonProperty("shipments")
    private ShippingOrders shipments;

    @JsonProperty("driver_profile") // Formats JSON property name as 'driver_profile'
    private DriverProfile driverProfile;

    @JsonProperty("available")
    private boolean available;
}
