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
import ar.edu.utn.frc.backend.simulacroparcial.application.request.CreateStoreRequest;
import ar.edu.utn.frc.backend.simulacroparcial.application.request.UpdateStoreRequest;
import ar.edu.utn.frc.backend.simulacroparcial.application.response.StoreResponse;
import ar.edu.utn.frc.backend.simulacroparcial.services.StoreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

@RequestMapping("/api/stores")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StoreController {
	StoreService storeService;

	@GetMapping
	public ResponseEntity<Object> findAll() {

		try {
			val stores = storeService.findAll()
				.stream()
				.map(StoreResponse::from)
				.toList();
			return ResponseHandler.success(stores);
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody CreateStoreRequest aRequest) {
		try {
			val store = storeService.create(aRequest.getManagerId(), aRequest.getAddress(), aRequest.getCityId(), aRequest.getPostalCode());
			return ResponseHandler.success(StoreResponse.from(store));
		} catch (IllegalArgumentException e) {
			return ResponseHandler.badRequest(e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findOne(@PathVariable Integer id) {
		try {
			return storeService.findById(id)
				.map(StoreResponse::from)
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
			storeService.delete(id);
			return ResponseHandler.noContent();
		} catch (IllegalArgumentException e) {
			//Ya fue borrado, asiq devuelvo 204
			return ResponseHandler.noContent();
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateStoreRequest aRequest) {
		try {
			storeService.update(id, aRequest.getManagerId());
			return ResponseHandler.noContent();
		} catch (IllegalArgumentException e) {
			return ResponseHandler.badRequest(e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.internalError();
		}
	}
}
