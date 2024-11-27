package utn.frc.backend.tutor.sac.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frc.backend.tutor.sac.domain.AlumnoEntity;

//public interface AlumnoRepository extends EMRepository<AlumnoEntity, Integer> {
public interface AlumnoRepository extends JpaRepository<AlumnoEntity, Integer> {
}
