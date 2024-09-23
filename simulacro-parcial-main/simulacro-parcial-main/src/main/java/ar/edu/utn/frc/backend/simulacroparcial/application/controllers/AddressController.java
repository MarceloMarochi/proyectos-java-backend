package ar.edu.utn.frc.backend.simulacroparcial.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.utn.frc.backend.simulacroparcial.application.ResponseHandler;
import ar.edu.utn.frc.backend.simulacroparcial.application.request.CreateAddressRequest;
import ar.edu.utn.frc.backend.simulacroparcial.application.request.UpdateAddressRequest;
import ar.edu.utn.frc.backend.simulacroparcial.application.response.AddressResponse;
import ar.edu.utn.frc.backend.simulacroparcial.services.AddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

@RequestMapping("/api/addresses")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AddressController {
	AddressService addressService;


	@GetMapping
	public ResponseEntity<Object> findAll() {

		try {
			val addresses = addressService.findAll()
				.stream()
				.map(AddressResponse::from)
				.toList();
			return ResponseHandler.success(addresses);
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody CreateAddressRequest aRequest) {
		try {
			val address = addressService.create(aRequest.getAddress(), aRequest.getPostalCode(), aRequest.getCityId());
			return ResponseHandler.success(AddressResponse.from(address));
		} catch (IllegalArgumentException e) {
			return ResponseHandler.badRequest(e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findOne(@PathVariable Integer id) {
		try {
			return addressService.findById(id)
				.map(AddressResponse::from)
				.map(ResponseHandler::success)
				.orElseGet(ResponseHandler::notFound);
		} catch (IllegalArgumentException e) {
			return ResponseHandler.notFound();
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		try {
			addressService.delete(id);
			return ResponseHandler.noContent();
		} catch (IllegalArgumentException e) {
			//Ya fue borrado, asiq devuelvo 204
			return ResponseHandler.noContent();
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateAddressRequest aRequest) {
		try {
			addressService.update(id, aRequest.getAddress(), aRequest.getPostalCode(), aRequest.getCityId());
			return ResponseHandler.noContent();
		} catch (IllegalArgumentException e) {
			return ResponseHandler.badRequest(e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}
}
