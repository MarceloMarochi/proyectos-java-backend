package utn.frc.backend.tutor.sac.web.api;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import utn.frc.backend.tutor.sac.dal.AlumnoRepository;
import utn.frc.backend.tutor.sac.dal.PersonaRepository;
import utn.frc.backend.tutor.sac.domain.AlumnoEntity;
import utn.frc.backend.tutor.sac.domain.PersonaEntity;
import utn.frc.backend.tutor.sac.web.api.AlumnosAPI;
import utn.frc.backend.tutor.sac.web.api.dto.AlumnoDTO;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;
import utn.frc.backend.tutor.sac.web.service.AlumnosService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
public class AlumnosAPITests {
    private PersonaRepository pRep;
    private AlumnoRepository aRep;
    private AlumnosService service;
    private AlumnosAPI api;
    private final AlumnoEntity ALUMNO = new AlumnoEntity(new PersonaEntity(1, "dni", "ape", "nom"), "leg");

    @BeforeEach
    public void setup() {
        pRep = Mockito.mock(PersonaRepository.class);
        aRep = Mockito.mock(AlumnoRepository.class);
        service = new AlumnosService(pRep, aRep);
        api = new AlumnosAPI(service);
    }

    @Test
    void testFindAll() {
        List<AlumnoEntity> alumnos = new ArrayList<>();
        alumnos.add(ALUMNO);
        Mockito.when(aRep.findAll()).thenReturn(alumnos);

        Assertions.assertEquals(
                HttpStatus.OK,
                api.findAlumnos().getStatusCode()
        );
    }

    @Test
    void testFindAllEmpty() {
        Mockito.when(aRep.findAll()).thenReturn(new ArrayList<>());

        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                api.findAlumnos().getStatusCode()
        );
    }

    @Test
    void testFindById() {
        Mockito.when(aRep.findById(anyInt())).thenReturn(Optional.of(ALUMNO));

        Assertions.assertEquals(
                HttpStatus.OK,
                api.findAlumno(999).getStatusCode()
        );
    }

    @Test
    void testFindByIdNotFound() {
        Mockito.when(aRep.findById(anyInt())).thenReturn(Optional.empty());

        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                api.findAlumno(999).getStatusCode()
        );
    }

    @Test
    void testAdd() {
        Mockito.when(pRep.existsById(anyInt())).thenReturn(true);

        Mockito.when(pRep.save(any(PersonaEntity.class))).thenReturn(ALUMNO.getPersona());
        Mockito.when(pRep.saveAndFlush(any(PersonaEntity.class))).thenReturn(ALUMNO.getPersona());
        //Mockito.when(pRep.saveAndRefresh(any(PersonaEntity.class))).thenReturn(ALUMNO.getPersona());

        Mockito.when(aRep.save(any(AlumnoEntity.class))).thenReturn(ALUMNO);
        Mockito.when(aRep.saveAndFlush(any(AlumnoEntity.class))).thenReturn(ALUMNO);
        //Mockito.when(aRep.saveAndRefresh(any(AlumnoEntity.class))).thenReturn(ALUMNO);

        Assertions.assertEquals(
                HttpStatus.CREATED,
                api.addAlumno(new AlumnoDTO()).getStatusCode()
        );
    }

    @Test
    void testValidate() {
        AlumnoDTO notValid = new AlumnoDTO(new AlumnoEntity(new PersonaEntity(1, "dni", "ape", "nom"), ""));
        Set<ConstraintViolation<AlumnoDTO>> violations =
                Validation.buildDefaultValidatorFactory().getValidator().validate(notValid);

        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    void testUpdate() {
        Mockito.when(aRep.findById(anyInt())).thenReturn(Optional.of(ALUMNO));

        Mockito.when(aRep.save(any(AlumnoEntity.class))).thenReturn(ALUMNO);
        Mockito.when(aRep.saveAndFlush(any(AlumnoEntity.class))).thenReturn(ALUMNO);
        //Mockito.when(aRep.saveAndRefresh(any(AlumnoEntity.class))).thenReturn(ALUMNO);

        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                api.updateAlumno(ALUMNO.getPid(), new AlumnoDTO()).getStatusCode()
        );
    }

    @Test
    void testUpdateNotFound() {
        Mockito.when(aRep.findById(anyInt())).thenReturn(Optional.empty());

        Mockito.when(aRep.save(any(AlumnoEntity.class))).thenReturn(ALUMNO);
        Mockito.when(aRep.saveAndFlush(any(AlumnoEntity.class))).thenReturn(ALUMNO);
        //Mockito.when(aRep.saveAndRefresh(any(AlumnoEntity.class))).thenReturn(ALUMNO);

        ResourceNotFoundException thrown =  Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> api.updateAlumno(999, new AlumnoDTO()),
                "Se espera ResourceNotFoundException");

        Assertions.assertEquals(thrown.getMessage(), "Alumno [999] inexistente");
    }

    @Test
    void testDelete() {
        Mockito.when(aRep.existsById(anyInt())).thenReturn(true);

        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                api.deleteAlumno(999).getStatusCode()
        );
    }

    @Test
    void testDeleteNotFound() {
        Mockito.when(aRep.existsById(anyInt())).thenReturn(false);

        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                api.deleteAlumno(999).getStatusCode()
        );
    }
}
