package ar.edu.utn.frc.backend.simulacroparcial.application.response;

import ar.edu.utn.frc.backend.simulacroparcial.model.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponse {
	Integer id;
	String address;
	String cityName;
	String cityId;
	String countryName;
	String countryId;
	String postalCode;

	public static AddressResponse from(Address aAddress) {
		return AddressResponse.builder()
			.id(aAddress.getId())
			.address(aAddress.getAddress())
			.cityName(aAddress.getCity().getName())
			.cityId(aAddress.getCity().getId().toString())
			.countryName(aAddress.getCity().getCountry().getName())
			.countryId(aAddress.getCity().getCountry().getId().toString())
			.postalCode(aAddress.getPostalCode())
			.build();
	}
}
