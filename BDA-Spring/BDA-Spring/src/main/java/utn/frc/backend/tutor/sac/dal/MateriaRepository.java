package utn.frc.backend.tutor.sac.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frc.backend.tutor.sac.domain.MateriaEntity;

import java.util.List;

//public interface MateriaRepository extends EMRepository<MateriaEntity, Integer> {
public interface MateriaRepository extends JpaRepository<MateriaEntity, Integer> {

    List<MateriaEntity> findMateriasByDocentesPid(Integer pid);

}
