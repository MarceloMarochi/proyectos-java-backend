package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.List;
import java.util.Optional;

import ar.edu.utn.frc.backend.simulacroparcial.model.Address;

public interface AddressService {

	List<Address> findAll();

	Address create(String address, String postalCode, Integer cityId);

	void update(Integer id, String address, String postalCode, Integer cityId);

	void delete(Integer id);

	Optional<Address> findById(Integer id);
}
