package org.example.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailabilityRequest {

    @NotNull(message = "Availability status must not be null.")
    @JsonProperty("availability_status")
    private Boolean availabilityStatus;
}
