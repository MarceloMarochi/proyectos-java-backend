package ar.edu.utn.frc.backend.simulacroparcial.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.edu.utn.frc.backend.simulacroparcial.model.Inventory;
import ar.edu.utn.frc.backend.simulacroparcial.repositories.InventoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

	InventoryRepository inventoryRepository;

	@Override
	public Inventory create(final Integer storeId, final Integer filmId) {
		return null;
	}

	@Override
	public List<Inventory> findAll() {
		return null;
	}

	@Override
	public Optional<Inventory> findById(final Long id) {
		return Optional.empty();
	}

	@Override
	public void deleteById(final Long id) {

	}
}
