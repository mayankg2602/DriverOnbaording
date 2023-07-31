package org.example.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverProfileRequest {

    @JsonProperty("profile_picture")
    @NotNull(message = "Profile picture cannot be null")
    private String profilePicture;

    @JsonProperty("license_info")
    @NotNull(message = "License info cannot be null")
    private String licenseInfo;

    @JsonProperty("vehicle_info")
    @NotNull(message = "Vehicle info cannot be null")
    private String vehicleInfo;

    @JsonProperty("insurance_info")
    @NotNull(message = "Insurance info cannot be null")
    private String insuranceInfo;

    @JsonProperty("other_info")
    private String otherInfo;
}
