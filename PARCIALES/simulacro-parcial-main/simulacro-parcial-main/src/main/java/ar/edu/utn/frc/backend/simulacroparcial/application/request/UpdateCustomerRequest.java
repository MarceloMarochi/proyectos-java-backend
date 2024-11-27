package ar.edu.utn.frc.backend.simulacroparcial.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCustomerRequest {
	@NotBlank(message = "First name is mandatory")
	String firstName;
	@NotBlank(message = "Last name is mandatory")
	String lastName;
	@NotBlank(message = "Email is mandatory")
	@Pattern(regexp = "^(.+)@(.+)$", message = "Email is invalid")
	String email;
	@NotBlank(message = "Address is mandatory")
	String address;
	@NotBlank(message = "City is mandatory")
	String postalCode;
}
