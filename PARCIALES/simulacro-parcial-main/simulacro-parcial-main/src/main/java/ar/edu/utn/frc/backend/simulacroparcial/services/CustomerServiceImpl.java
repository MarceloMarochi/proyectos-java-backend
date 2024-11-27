package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.utn.frc.backend.simulacroparcial.model.Address;
import ar.edu.utn.frc.backend.simulacroparcial.model.Customer;
import ar.edu.utn.frc.backend.simulacroparcial.repositories.CustomerRepository;
import ar.edu.utn.frc.backend.simulacroparcial.repositories.IdentifierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	CityService cityService;
	CustomerRepository customerRepository;
	StoreService storeService;
	IdentifierRepository identifierRepository;

	@Override
	@Transactional
	public Customer create(final String firstName,
						   final String lastName,
						   final String email,
						   final String address,
						   final Integer cityId,
						   final String postalCode) {

		val city = cityService.findById(cityId).orElseThrow(() -> new IllegalArgumentException("City not found"));
		val store = storeService.findByCityId(cityId).orElseThrow(() -> new IllegalArgumentException("No store found in the selected city"));
		val addressId = identifierRepository.nextValue(Address.TABLE_NAME);
		val addressEntity = new Address(addressId, address, city, postalCode);
		val customerId = identifierRepository.nextValue(Customer.TABLE_NAME);
		val customer = new Customer(customerId, firstName, lastName, email, addressEntity, store.getId());

		return customerRepository.save(customer);
	}

	@Override public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override public Optional<Customer> findById(final Integer id) {
		return customerRepository.findById(id);
	}

	@Override
	@Transactional
	public void delete(final Integer id) {
		try {
			customerRepository.deleteById(id);
		} catch (Exception e) {
			throw new IllegalArgumentException("Customer not found");
		}
	}

	@Override
	@Transactional
	public void update(final Integer id, final String firstName, final String lastName, final String email, final String address, final String postalCode) {
		val customer = customerRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Customer not found"));

		customer.update(firstName, lastName, email, address, postalCode);

		customerRepository.save(customer);
	}
}
