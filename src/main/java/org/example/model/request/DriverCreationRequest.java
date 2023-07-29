package org.example.model.request;

import lombok.Data;

@Data
public class DriverCreationRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
}
