package utn.frc.backend.tutor.sac.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.backend.tutor.sac.dal.AlumnoRepository;
import utn.frc.backend.tutor.sac.dal.PersonaRepository;
import utn.frc.backend.tutor.sac.domain.AlumnoEntity;
import utn.frc.backend.tutor.sac.domain.PersonaEntity;
import utn.frc.backend.tutor.sac.web.api.dto.AlumnoDTO;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnosService {
    private PersonaRepository pRep;
    private AlumnoRepository aRep;

    @Autowired
    public AlumnosService(PersonaRepository pRep, AlumnoRepository aRep) {
        this.pRep = pRep;
        this.aRep = aRep;
    }

    public List<AlumnoDTO> findAll() {
        return aRep.findAll().stream().map(alumno -> new AlumnoDTO(alumno)).toList();
    }

    public Optional<AlumnoDTO> findById(Integer pid) {
        Optional<AlumnoEntity> alumno = aRep.findById(pid);

        return alumno.isEmpty()
                ? Optional.empty()
                : Optional.of(new AlumnoDTO(alumno.get()));
    }

    public AlumnoDTO add(AlumnoDTO alumnoDTO) {
        AlumnoEntity alumno = alumnoDTO.toEntity();
        PersonaEntity persona;

        if (alumnoDTO.getPid() == null) {
            persona = pRep.saveAndFlush(alumno.getPersona());
            alumno.setPid(persona.getPid());
        } else {
            persona = pRep.findById(alumnoDTO.getPid()).orElseThrow(
                    () -> new ResourceNotFoundException(String.format("Persona [%d] inexistente", alumnoDTO.getPid()))
            );
            pRep.saveAndFlush(persona.update(alumno.getPersona()));
        }

        alumno.setPersona(null); // mandatorio según one to one cascade type
        //alumno = aRep.saveAndRefresh(alumno);
        alumno = aRep.saveAndFlush(alumno);

        return new AlumnoDTO(alumno);
    }

    public AlumnoDTO update(Integer pid, AlumnoDTO alumnoDTO) {
        AlumnoEntity alumno = aRep.findById(pid).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Alumno [%d] inexistente", pid))
        );

        pRep.saveAndFlush(alumno.getPersona().update(alumnoDTO.toPersonaEntity()));

        return new AlumnoDTO(aRep.save(alumno.update(alumnoDTO.toEntity())));
    }

    public boolean deleteById(Integer pid) {
        if (!aRep.existsById(pid)) {
            return false;
        }

        aRep.deleteById(pid);
        return true;
    }
}
