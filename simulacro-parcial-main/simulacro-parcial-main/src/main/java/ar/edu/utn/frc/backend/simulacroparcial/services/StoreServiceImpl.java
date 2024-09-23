package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.utn.frc.backend.simulacroparcial.model.Address;
import ar.edu.utn.frc.backend.simulacroparcial.model.Store;
import ar.edu.utn.frc.backend.simulacroparcial.repositories.IdentifierRepository;
import ar.edu.utn.frc.backend.simulacroparcial.repositories.StoreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

	StoreRepository storeRepository;
	StaffService staffService;
	IdentifierRepository identifierRepository;
	CityService cityService;

	@Override
	@Transactional
	public Store create(final Integer managerId, final String address, final Integer cityId, final String postalCode) {
		val manager = staffService.findById(managerId).orElseThrow(() -> new IllegalArgumentException("Manager not found"));
		val storeId = identifierRepository.nextValue(Store.TABLE_NAME);
		val city = cityService.findById(cityId).orElseThrow(() -> new IllegalArgumentException("City not found"));

		val addressId = identifierRepository.nextValue(Address.TABLE_NAME);
		val newAddress = new Address(addressId, address, city, postalCode);

		val store = new Store(storeId, manager, newAddress);

		return storeRepository.save(store);
	}

	@Override public List<Store> findAll() {
		return storeRepository.findAll();
	}

	@Override public Optional<Store> findById(final Integer id) {
		return storeRepository.findById(id);
	}

	@Override
	@Transactional
	public void delete(final Integer id) {
		storeRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void update(final Integer id, final Integer managerId) {
		val newManager = staffService.findById(managerId).orElseThrow(() -> new IllegalArgumentException("Manager not found"));
		val store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Store not found"));
		store.changeManager(newManager);
		storeRepository.save(store);
	}

	@Override public Optional<Store> findByCityId(final Integer cityId) {
		return storeRepository.findByCityId(cityId);
	}

	@Override public List<Store> findCustomerLocalStores(final Integer customerId) {
		return storeRepository.findCustomerLocalStores(customerId);
	}

	@Override public List<Store> findCustomerLocalStoresWithFilm(final Integer customerId, final Integer filmId) {
		return storeRepository.findCustomerLocalStoresWithFilm(customerId, filmId);
	}
}
