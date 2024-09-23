package ar.edu.utn.frc.backend.simulacroparcial.application.response;

import ar.edu.utn.frc.backend.simulacroparcial.model.Staff;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffResponse {
	Integer id;
	String firstName;
	String lastName;
	String email;
	boolean active;
	AddressResponse address;


	public static StaffResponse from(Staff aStaff) {
		return StaffResponse.builder()
			.id(aStaff.getId())
			.firstName(aStaff.getFirstName())
			.lastName(aStaff.getLastName())
			.email(aStaff.getEmail())
			.active(aStaff.isActive())
			.address(AddressResponse.from(aStaff.getAddress()))
			.build();
	}
}
