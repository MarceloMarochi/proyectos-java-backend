package ar.edu.utn.frc.backend.simulacroparcial.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateStoreRequest {
	@NotNull(message = "Manager is mandatory")
	Integer managerId;
	@NotBlank(message = "Address is mandatory")
	String address;
	@NotNull(message = "City is mandatory")
	Integer cityId;
	@NotBlank(message = "Postal code is mandatory")
	String postalCode;
}
