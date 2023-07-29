package org.example.model.request;

import lombok.Data;

@Data
public class AvailabilityRequest {
    private Long driverId;
    private boolean availabilityStatus;
}
