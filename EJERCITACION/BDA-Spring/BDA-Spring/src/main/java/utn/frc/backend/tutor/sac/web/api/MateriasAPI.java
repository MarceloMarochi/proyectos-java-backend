package utn.frc.backend.tutor.sac.web.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.tutor.sac.web.api.dto.DocenteDTO;
import utn.frc.backend.tutor.sac.web.api.dto.MateriaDTO;
import utn.frc.backend.tutor.sac.web.service.MateriasService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materias")
public class MateriasAPI {

    private MateriasService service;

    @Autowired
    public MateriasAPI(MateriasService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> findMaterias() {
        List<MateriaDTO> materias = service.findAll();

        return materias.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(materias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> findMateria(@PathVariable Integer id) {
        Optional<MateriaDTO> materia = service.findById(id);

        return materia.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(materia.get());
    }

    @GetMapping("/{id}/docentes")
    public ResponseEntity<List<DocenteDTO>> findDocentes(@PathVariable Integer id) {
        List<DocenteDTO> docentes = service.findDocentes(id);

        return docentes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(docentes);
    }

    @PostMapping
    public ResponseEntity<MateriaDTO> addMateria(@RequestBody @Valid MateriaDTO materia) {
        return new ResponseEntity<>(service.add(materia), HttpStatus.CREATED);
    }

    @PostMapping("/{mid}/docentes/{pid}")
    public ResponseEntity<DocenteDTO> addDocente(@PathVariable Integer mid, @PathVariable Integer pid) {
        return new ResponseEntity<>(service.addDocente(mid, pid), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> updateMateria(
            @PathVariable Integer id,
            @Valid @RequestBody MateriaDTO materia
    ) {
        return new ResponseEntity<>(service.update(id, materia), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMateria(@PathVariable Integer id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{mid}/docentes/{pid}")
    public ResponseEntity deleteMateria(@PathVariable Integer mid, @PathVariable Integer pid) {
        return service.deleteDocente(mid, pid)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
