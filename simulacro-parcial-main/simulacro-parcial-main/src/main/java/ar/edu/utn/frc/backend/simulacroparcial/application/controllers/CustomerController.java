package ar.edu.utn.frc.backend.simulacroparcial.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.utn.frc.backend.simulacroparcial.application.ResponseHandler;
import ar.edu.utn.frc.backend.simulacroparcial.application.request.CreateCustomerRequest;
import ar.edu.utn.frc.backend.simulacroparcial.application.request.UpdateCustomerRequest;
import ar.edu.utn.frc.backend.simulacroparcial.application.response.CustomerResponse;
import ar.edu.utn.frc.backend.simulacroparcial.application.response.LocalStoreWithFilmResponse;
import ar.edu.utn.frc.backend.simulacroparcial.application.response.StoreResponse;
import ar.edu.utn.frc.backend.simulacroparcial.services.CustomerService;
import ar.edu.utn.frc.backend.simulacroparcial.services.StoreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

@RequestMapping("/api/customers")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerController {
	CustomerService customerService;
	StoreService storeService;

	@GetMapping
	public ResponseEntity<Object> findAll() {
		try {
			val customers = customerService.findAll()
				.stream()
				.map(CustomerResponse::from)
				.toList();

			return ResponseHandler.success(customers);
		} catch (IllegalArgumentException e) {
			return ResponseHandler.badRequest(e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody CreateCustomerRequest aRequest) {
		try {
			val customer = customerService.create(
				aRequest.getFirstName(),
				aRequest.getLastName(),
				aRequest.getEmail(),
				aRequest.getAddress(),
				aRequest.getCityId(),
				aRequest.getPostalCode());

			return ResponseHandler.success(CustomerResponse.from(customer));
		} catch (IllegalArgumentException e) {
			return ResponseHandler.badRequest(e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findOne(@PathVariable Integer id) {
		try {
			return customerService.findById(id)
				.map(aCustomer -> ResponseHandler.success(CustomerResponse.from(aCustomer)))
				.orElseGet(ResponseHandler::notFound);
		} catch (IllegalArgumentException e) {
			return ResponseHandler.badRequest(e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		try {
			customerService.delete(id);
			return ResponseHandler.noContent();
		} catch (IllegalArgumentException e) {
			//Ya fue borrado, asiq devuelvo 204
			return ResponseHandler.noContent();
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@GetMapping("/{id}/local_stores")
	public ResponseEntity<Object> localStores(@PathVariable Integer id) {

		try {
			val stores = storeService.findCustomerLocalStores(id)
				.stream()
				.map(StoreResponse::from)
				.toList();

			return ResponseHandler.success(stores);
		} catch (IllegalArgumentException e) {
			return ResponseHandler.badRequest(e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@GetMapping(value = "/{id}/local_stores", params = { "filmId" })
	public ResponseEntity<Object> localStoresWithFilm(@PathVariable Integer id, @RequestParam Integer filmId) {

		try {
			val stores = storeService.findCustomerLocalStoresWithFilm(id, filmId)
				.stream()
				.map(LocalStoreWithFilmResponse::from)
				.toList();

			return ResponseHandler.success(stores);
		} catch (IllegalArgumentException e) {
			return ResponseHandler.badRequest(e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateCustomerRequest aRequest) {
		try {
			customerService.update(id, aRequest.getFirstName(), aRequest.getLastName(), aRequest.getEmail(), aRequest.getAddress(), aRequest.getPostalCode());
			return ResponseHandler.noContent();
		} catch (IllegalArgumentException e) {
			return ResponseHandler.badRequest(e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}
}
