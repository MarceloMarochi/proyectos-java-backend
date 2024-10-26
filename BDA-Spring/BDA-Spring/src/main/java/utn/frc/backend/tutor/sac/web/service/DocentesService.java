package utn.frc.backend.tutor.sac.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.backend.tutor.sac.dal.DocenteRepository;
import utn.frc.backend.tutor.sac.dal.MateriaRepository;
import utn.frc.backend.tutor.sac.dal.PersonaRepository;
import utn.frc.backend.tutor.sac.domain.DocenteEntity;
import utn.frc.backend.tutor.sac.domain.PersonaEntity;
import utn.frc.backend.tutor.sac.web.api.dto.DocenteDTO;
import utn.frc.backend.tutor.sac.web.api.dto.MateriaDTO;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class DocentesService {

    private PersonaRepository pRep;
    private DocenteRepository dRep;
    private MateriaRepository mRep;

    @Autowired
    public DocentesService(PersonaRepository pRep, DocenteRepository dRep, MateriaRepository mRep) {
        this.pRep = pRep;
        this.dRep = dRep;
        this.mRep = mRep;
    }

    public List<DocenteDTO> findAll() {
        return dRep.findAll().stream().map(docente -> new DocenteDTO(docente)).toList();
    }

    public Optional<DocenteDTO> findById(Integer pid) {
        Optional<DocenteEntity> docente = dRep.findById(pid);

        return docente.isEmpty()
                ? Optional.empty()
                : Optional.of(new DocenteDTO(docente.get()));
    }

    public List<MateriaDTO> findMaterias(Integer pid) {
        if (!dRep.existsById(pid)) {
            throw new ResourceNotFoundException(String.format("Docente [%d] inexistente", pid));
        }

        return mRep.findMateriasByDocentesPid(pid).stream().map(materia -> new MateriaDTO(materia)).toList();
    }

    public DocenteDTO add(DocenteDTO docenteDTO) {
        DocenteEntity docente = docenteDTO.toEntity();
        PersonaEntity persona;

        if (docenteDTO.getPid() == null) {
            persona = pRep.saveAndFlush(docente.getPersona());
            docente.setPid(persona.getPid());
        } else {
            persona = pRep.findById(docenteDTO.getPid()).orElseThrow(
                    () -> new ResourceNotFoundException(String.format("Persona [%d] inexistente", docenteDTO.getPid()))
            );
            pRep.saveAndFlush(persona.update(docente.getPersona()));
        }

        docente.setPersona(null); // mandatorio según one to one cascade type
        //docente = dRep.saveAndRefresh(docente); // levanta datos persona

        return new DocenteDTO(docente);
    }

    public DocenteDTO update(Integer pid, DocenteDTO docenteDTO) {
        DocenteEntity docente = dRep.findById(pid).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Docente [%d] inexistente", pid))
        );

        pRep.saveAndFlush(docente.getPersona().update(docenteDTO.toPersonaEntity()));

        return new DocenteDTO(dRep.save(docente.update(docenteDTO.toEntity())));
    }

    public boolean deleteById(Integer pid) {
        if (!dRep.existsById(pid)) {
            return false;
        }

        dRep.deleteById(pid);
        return true;
    }
}
