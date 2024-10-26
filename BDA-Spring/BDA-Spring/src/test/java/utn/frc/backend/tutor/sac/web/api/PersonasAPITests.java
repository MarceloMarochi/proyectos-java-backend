package utn.frc.backend.tutor.sac.web.api;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import utn.frc.backend.tutor.sac.dal.PersonaRepository;
import utn.frc.backend.tutor.sac.domain.PersonaEntity;
import utn.frc.backend.tutor.sac.web.api.PersonasAPI;
import utn.frc.backend.tutor.sac.web.api.dto.PersonaDTO;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;
import utn.frc.backend.tutor.sac.web.service.PersonasService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
public class PersonasAPITests {
    private PersonaRepository rep;
    private PersonasService service;
    private PersonasAPI api;
    private final PersonaEntity PERSONA = new PersonaEntity(1, "dni", "ape", "nom");

    @BeforeEach
    private void setup() {
        rep = Mockito.mock(PersonaRepository.class);
        service = new PersonasService(rep);
        api = new PersonasAPI(service);
    }

    @Test
    void testFindAll() {
        List<PersonaEntity> personas = new ArrayList<>();
        personas.add(PERSONA);
        Mockito.when(rep.findAll()).thenReturn(personas);

        Assertions.assertEquals(
                HttpStatus.OK,
                api.findPersonas().getStatusCode()
        );
    }

    @Test
    void testFindAllEmpty() {
        Mockito.when(rep.findAll()).thenReturn(new ArrayList<>());

        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                api.findPersonas().getStatusCode()
        );
    }

    @Test
    void testFindById() {
        Mockito.when(rep.findById(anyInt())).thenReturn(Optional.of(PERSONA));

        Assertions.assertEquals(
                HttpStatus.OK,
                api.findPersona(999).getStatusCode()
        );
    }

    @Test
    void testFindByIdNotFound() {
        Mockito.when(rep.findById(anyInt())).thenReturn(Optional.empty());

        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                api.findPersona(999).getStatusCode()
        );
    }

    @Test
    void testAdd() {
        Mockito.when(rep.save(any(PersonaEntity.class))).thenReturn(PERSONA);
        Mockito.when(rep.saveAndFlush(any(PersonaEntity.class))).thenReturn(PERSONA);
        //Mockito.when(rep.saveAndRefresh(any(Persona.class))).thenReturn(PERSONA);

        Assertions.assertEquals(
                HttpStatus.CREATED,
                api.addPersona(new PersonaDTO()).getStatusCode()
        );
    }

    @Test
    void testValidate() {
        PersonaDTO notValid = new PersonaDTO(new PersonaEntity(1, "dni", "", "nombre"));
        Set<ConstraintViolation<PersonaDTO>> violations =
                Validation.buildDefaultValidatorFactory().getValidator().validate(notValid);

        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    void testUpdate() {
        Mockito.when(rep.findById(anyInt())).thenReturn(Optional.of(PERSONA));

        Mockito.when(rep.save(any(PersonaEntity.class))).thenReturn(PERSONA);
        Mockito.when(rep.saveAndFlush(any(PersonaEntity.class))).thenReturn(PERSONA);
        //Mockito.when(rep.saveAndRefresh(any(Persona.class))).thenReturn(PERSONA);

        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                api.updatePersona(PERSONA.getPid(), new PersonaDTO()).getStatusCode()
        );
    }

    @Test
    void testUpdateNotFound() {
        Mockito.when(rep.findById(anyInt())).thenReturn(Optional.empty());

        Mockito.when(rep.save(any(PersonaEntity.class))).thenReturn(PERSONA);
        Mockito.when(rep.saveAndFlush(any(PersonaEntity.class))).thenReturn(PERSONA);
        //Mockito.when(rep.saveAndRefresh(any(Persona.class))).thenReturn(PERSONA);

        ResourceNotFoundException thrown =  Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> api.updatePersona(999, new PersonaDTO()),
                "Se espera ResourceNotFoundException");

        Assertions.assertEquals(thrown.getMessage(), "Persona [999] inexistente");
    }

    @Test
    void testDelete() {
        Mockito.when(rep.existsById(anyInt())).thenReturn(true);

        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                api.deletePersona(999).getStatusCode()
        );
    }

    @Test
    void testDeleteNotFound() {
        Mockito.when(rep.existsById(anyInt())).thenReturn(false);

        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                api.deletePersona(999).getStatusCode()
        );
    }
}
