package ar.edu.utn.frc.backend.simulacroparcial.application.request;

import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateStoreRequest {
	@NotNull(message = "Manager is mandatory")
	Integer managerId;
}
