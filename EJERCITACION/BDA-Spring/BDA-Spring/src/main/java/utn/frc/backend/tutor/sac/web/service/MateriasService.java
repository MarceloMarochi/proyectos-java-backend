package utn.frc.backend.tutor.sac.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.backend.tutor.sac.dal.DocenteRepository;
import utn.frc.backend.tutor.sac.dal.MateriaRepository;
import utn.frc.backend.tutor.sac.domain.DocenteEntity;
import utn.frc.backend.tutor.sac.domain.MateriaEntity;
import utn.frc.backend.tutor.sac.web.api.dto.DocenteDTO;
import utn.frc.backend.tutor.sac.web.api.dto.MateriaDTO;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class MateriasService {

    private DocenteRepository dRep;
    private MateriaRepository mRep;

    @Autowired
    public MateriasService(DocenteRepository dRep, MateriaRepository mRep) {
        this.dRep = dRep;
        this.mRep = mRep;
    }

    public List<MateriaDTO> findAll() {
        return mRep.findAll().stream().map(materia -> new MateriaDTO(materia)).toList();
    }

    public Optional<MateriaDTO> findById(Integer mid) {
        Optional<MateriaEntity> materia = mRep.findById(mid);

        return materia.isEmpty()
                ? Optional.empty()
                : Optional.of(new MateriaDTO(materia.get()));
    }

    public List<DocenteDTO> findDocentes(Integer mid) {
        if (!mRep.existsById(mid)) {
            throw new ResourceNotFoundException(String.format("Materia [%d] inexistente", mid));
        }

        return dRep.findDocentesByMateriasMid(mid).stream().map(docente -> new DocenteDTO(docente)).toList();
    }

    public MateriaDTO add(MateriaDTO materiaDTO) {
        return new MateriaDTO(mRep.save(materiaDTO.toEntity()));
    }

    public DocenteDTO addDocente(Integer mid, Integer pid) {
        MateriaEntity materia = mRep.findById(mid).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Materia %d inexistente", mid))
        );

        DocenteEntity docente = dRep.findById(pid).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Docente %d inexistente", pid))
        );

        materia.addDocente(docente);

        mRep.save(materia);

        return new DocenteDTO(docente);
    }

    public MateriaDTO update(Integer mid, MateriaDTO materiaDTO) {
        MateriaEntity materia = mRep.findById(mid).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Materia %d inexistente", mid))
        );

        return new MateriaDTO(mRep.save(materia.update(materiaDTO.toEntity())));
    }

    public boolean deleteById(Integer mid) {
        if (!mRep.existsById(mid)) {
            return false;
        }

        mRep.deleteById(mid);
        return true;
    }

    public boolean deleteDocente(Integer mid, Integer pid) {
        MateriaEntity materia = mRep.findById(mid).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Materia %d inexistente", mid))
        );

        boolean r = materia.removeDocente(pid);

        if (r) {
            mRep.save(materia);
        }

        return r;
    }

}
