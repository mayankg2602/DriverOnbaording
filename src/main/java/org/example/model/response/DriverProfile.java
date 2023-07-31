package org.example.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverProfile {

    @JsonProperty("profile_id")
    private Long profileId;

    @JsonProperty("profile_picture")
    private String profilePicture;

    @JsonProperty("license_info")
    private String licenseInfo;

    @JsonProperty("vehicle_info")
    private String vehicleInfo;

    @JsonProperty("insurance_info")
    private String insuranceInfo;

    @JsonProperty("other_info")
    private String otherInfo;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}
