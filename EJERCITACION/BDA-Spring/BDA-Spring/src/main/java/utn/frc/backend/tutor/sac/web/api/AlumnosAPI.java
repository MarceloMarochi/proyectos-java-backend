package utn.frc.backend.tutor.sac.web.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.tutor.sac.web.api.dto.AlumnoDTO;
import utn.frc.backend.tutor.sac.web.service.AlumnosService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnosAPI {
    private AlumnosService service;

    @Autowired
    public AlumnosAPI(AlumnosService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> findAlumnos() {
        List<AlumnoDTO> alumnos = service.findAll();

        return alumnos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(alumnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> findAlumno(@PathVariable Integer id) {
        Optional<AlumnoDTO> alumno = service.findById(id);

        return alumno.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(alumno.get());
    }

    @PostMapping
    public ResponseEntity<AlumnoDTO> addAlumno(@RequestBody @Valid AlumnoDTO alumno) {
        return new ResponseEntity<>(service.add(alumno), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDTO> updateAlumno(
            @PathVariable Integer id,
            @Valid @RequestBody AlumnoDTO alumno
    ) {
        return new ResponseEntity<>(service.update(id, alumno), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAlumno(@PathVariable Integer id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
