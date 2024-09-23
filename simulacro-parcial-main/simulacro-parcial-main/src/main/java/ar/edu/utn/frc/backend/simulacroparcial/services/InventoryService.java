package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.List;
import java.util.Optional;

import ar.edu.utn.frc.backend.simulacroparcial.model.Inventory;

public interface InventoryService {

	Inventory create(Integer storeId, Integer filmId);

	List<Inventory> findAll();

	Optional<Inventory> findById(Long id);

	void deleteById(Long id);
}
