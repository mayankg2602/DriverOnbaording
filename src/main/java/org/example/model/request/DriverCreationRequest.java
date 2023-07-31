package org.example.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * This class is used to capture request data for creating a new driver.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverCreationRequest {

    @JsonProperty("name")
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot be more than 100 characters")
    private String name;

    @JsonProperty("email")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;

    @JsonProperty("password")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @JsonProperty("address")
    @NotBlank(message = "Address cannot be blank")
    private String address;
}
