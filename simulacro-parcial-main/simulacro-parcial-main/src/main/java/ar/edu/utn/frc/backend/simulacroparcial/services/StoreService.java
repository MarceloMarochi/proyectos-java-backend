package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.List;
import java.util.Optional;

import ar.edu.utn.frc.backend.simulacroparcial.model.Store;

public interface StoreService {

	Store create(Integer managerId, String address, Integer cityId, String postalCode);

	List<Store> findAll();

	Optional<Store> findById(Integer id);

	void delete(Integer id);

	void update(Integer id, Integer managerId);

	Optional<Store> findByCityId(Integer cityId);

	List<Store> findCustomerLocalStores(Integer customerId);

	List<Store> findCustomerLocalStoresWithFilm(Integer customerId, Integer filmId);
}
