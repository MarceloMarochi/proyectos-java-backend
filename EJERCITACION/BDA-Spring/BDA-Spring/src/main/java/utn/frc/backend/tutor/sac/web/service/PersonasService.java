package utn.frc.backend.tutor.sac.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.backend.tutor.sac.dal.PersonaRepository;
import utn.frc.backend.tutor.sac.domain.PersonaEntity;
import utn.frc.backend.tutor.sac.web.api.dto.PersonaDTO;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonasService {
    private PersonaRepository rep;

    @Autowired
    public PersonasService(PersonaRepository rep) {
        this.rep = rep;
    }

    public List<PersonaDTO> findAll() {
        return rep.findAll().stream().map(persona -> new PersonaDTO(persona)).toList();
    }

    public Optional<PersonaDTO> findById(Integer pid) {
        Optional<PersonaEntity> persona = rep.findById(pid);

        return persona.isEmpty()
                ? Optional.empty()
                : Optional.of(new PersonaDTO(persona.get()));
    }

    public PersonaDTO add(PersonaDTO personaDTO) {
        PersonaEntity persona = rep.save(personaDTO.toPersonaEntity());
        return new PersonaDTO(persona);
    }

    public List<PersonaDTO> addAll(List<PersonaDTO> personaDTOS) {
        List<PersonaEntity> personas = rep.saveAll(personaDTOS.stream().map(PersonaDTO::toPersonaEntity).toList());
        return personas.stream().map(persona -> new PersonaDTO(persona)).toList();
    }

    public PersonaDTO update(Integer pid, PersonaDTO personaDTO) {
        PersonaEntity persona = rep.findById(pid).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Persona [%d] inexistente", pid))
        );

        persona = rep.save(persona.update(personaDTO.toPersonaEntity()));

        return new PersonaDTO(persona);
    }

    public boolean deleteById(Integer pid) {
        if (!rep.existsById(pid)) {
            return false;
        }

        rep.deleteById(pid);
        return true;
    }
}
