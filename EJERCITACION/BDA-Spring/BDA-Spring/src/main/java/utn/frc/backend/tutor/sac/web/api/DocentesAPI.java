package utn.frc.backend.tutor.sac.web.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.tutor.sac.web.api.dto.DocenteDTO;
import utn.frc.backend.tutor.sac.web.api.dto.MateriaDTO;
import utn.frc.backend.tutor.sac.web.service.DocentesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/docentes")
public class DocentesAPI {

    private DocentesService service;

    @Autowired
    public DocentesAPI(DocentesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DocenteDTO>> findDocentes() {
        List<DocenteDTO> docentes = service.findAll();

        return docentes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(docentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> findDocente(@PathVariable Integer id) {
        Optional<DocenteDTO> docente = service.findById(id);

        return docente.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(docente.get());
    }

    @GetMapping("/{id}/materias")
    public ResponseEntity<List<MateriaDTO>> findMaterias(@PathVariable Integer id) {
        List<MateriaDTO> materias = service.findMaterias(id);

        return materias.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(materias);
    }

    @PostMapping
    public ResponseEntity<DocenteDTO> addDocente(@RequestBody @Valid DocenteDTO docente) {
        return new ResponseEntity<>(service.add(docente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> updateDocente(
            @PathVariable Integer id,
            @Valid @RequestBody DocenteDTO docente
    ) {
        return new ResponseEntity<>(service.update(id, docente), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDocente(@PathVariable Integer id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
