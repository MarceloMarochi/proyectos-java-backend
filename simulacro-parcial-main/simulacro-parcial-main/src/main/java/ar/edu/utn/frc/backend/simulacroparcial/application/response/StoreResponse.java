package ar.edu.utn.frc.backend.simulacroparcial.application.response;

import ar.edu.utn.frc.backend.simulacroparcial.model.Store;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreResponse {
	Integer id;
	AddressResponse address;
	StaffResponse manager;

	public static StoreResponse from(Store aStore) {
		return StoreResponse.builder()
			.id(aStore.getId())
			.address(AddressResponse.from(aStore.getAddress()))
			.manager(StaffResponse.from(aStore.getManager()))
			.build();
	}
}
