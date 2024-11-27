package utn.frc.backend.tutor.sac.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frc.backend.tutor.sac.domain.PersonaEntity;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
}
