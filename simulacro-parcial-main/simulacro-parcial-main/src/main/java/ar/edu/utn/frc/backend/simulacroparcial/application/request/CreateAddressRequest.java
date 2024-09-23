package ar.edu.utn.frc.backend.simulacroparcial.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAddressRequest {
	@NotBlank(message = "Address is required")
	String address;
	@NotNull(message = "City is required")
	Integer cityId;
	@NotBlank(message = "Postal code is required")
	String postalCode;
}
