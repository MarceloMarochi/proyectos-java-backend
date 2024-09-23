package ar.edu.utn.frc.backend.simulacroparcial.application.response;


import ar.edu.utn.frc.backend.simulacroparcial.model.Customer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
	Integer id;
	String firstName;
	String lastName;
	String email;
	AddressResponse address;

	public static CustomerResponse from(Customer aCustomer) {
		return CustomerResponse.builder()
			.id(aCustomer.getId())
			.email(aCustomer.getEmail())
			.firstName(aCustomer.getFirstName())
			.lastName(aCustomer.getLastName())
			.address(AddressResponse.from(aCustomer.getAddress()))
			.build();
	}

}
