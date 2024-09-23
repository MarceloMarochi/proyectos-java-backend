package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.List;
import java.util.Optional;

import ar.edu.utn.frc.backend.simulacroparcial.model.Customer;

public interface CustomerService {

	Customer create(String firstName, String lastName, String email, String address, Integer cityId, String postalCode);

	List<Customer> findAll();

	Optional<Customer> findById(Integer id);

	void delete(Integer id);

	void update(Integer id, String firstName, String lastName, String email, String address, String postalCode);
}
