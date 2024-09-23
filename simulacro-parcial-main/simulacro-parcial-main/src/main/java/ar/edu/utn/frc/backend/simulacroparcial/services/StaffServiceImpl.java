package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.edu.utn.frc.backend.simulacroparcial.model.Staff;
import ar.edu.utn.frc.backend.simulacroparcial.repositories.StaffRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

	StaffRepository staffRepository;

	@Override public Optional<Staff> findById(final Integer id) {
		return staffRepository.findById(id);
	}
}
