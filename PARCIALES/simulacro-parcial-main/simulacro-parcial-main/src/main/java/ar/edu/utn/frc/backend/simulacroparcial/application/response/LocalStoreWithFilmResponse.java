package ar.edu.utn.frc.backend.simulacroparcial.application.response;

import ar.edu.utn.frc.backend.simulacroparcial.model.Store;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocalStoreWithFilmResponse {
	Integer storeId;
	AddressResponse address;
	String filmTitle;
	int availableCopies;

	public static LocalStoreWithFilmResponse from(Store aStore) {
		return LocalStoreWithFilmResponse.builder()
			.storeId(aStore.getId())
			.address(AddressResponse.from(aStore.getAddress()))
			.filmTitle(aStore.getInventories().get(0).getFilm().getTitle())
			.availableCopies(aStore.getInventories().size())
			.build();
	}
}
