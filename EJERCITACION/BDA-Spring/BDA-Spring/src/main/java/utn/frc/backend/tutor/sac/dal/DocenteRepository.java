package utn.frc.backend.tutor.sac.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frc.backend.tutor.sac.domain.DocenteEntity;

import java.util.List;

//public interface DocenteRepository extends EMRepository<DocenteEntity, Integer> {
public interface DocenteRepository extends JpaRepository<DocenteEntity, Integer> {

    List<DocenteEntity> findDocentesByMateriasMid(Integer mid);

}
