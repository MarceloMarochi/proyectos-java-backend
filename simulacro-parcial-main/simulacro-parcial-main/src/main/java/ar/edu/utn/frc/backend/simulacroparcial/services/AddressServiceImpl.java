package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.edu.utn.frc.backend.simulacroparcial.model.Address;
import ar.edu.utn.frc.backend.simulacroparcial.repositories.AddressRepository;
import ar.edu.utn.frc.backend.simulacroparcial.repositories.IdentifierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	IdentifierRepository identifierRepository;
	AddressRepository addressRepository;
	CityService cityService;

	@Override public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override public Address create(final String address, final String postalCode, final Integer cityId) {

		val city = cityService.findById(cityId).orElseThrow(() -> new IllegalArgumentException("City not found"));
		val addressId = identifierRepository.nextValue(Address.TABLE_NAME);
		val newAddress = new Address(addressId, address, city, postalCode);
		return addressRepository.save(newAddress);
	}

	@Override public void update(final Integer id, final String address, final String postalCode, final Integer cityId) {
		val existingAddress = addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Address not found"));
		val city = cityService.findById(cityId).orElseThrow(() -> new IllegalArgumentException("City not found"));
		existingAddress.update(address, city, postalCode);
		addressRepository.save(existingAddress);
	}

	@Override public void delete(final Integer id) {
		addressRepository.deleteById(id);
	}

	@Override public Optional<Address> findById(final Integer id) {
		return addressRepository.findById(id);
	}
}
