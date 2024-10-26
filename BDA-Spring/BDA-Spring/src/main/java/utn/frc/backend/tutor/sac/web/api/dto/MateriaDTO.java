package utn.frc.backend.tutor.sac.web.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.frc.backend.tutor.sac.domain.MateriaEntity;

import java.util.List;

@Data @NoArgsConstructor
public class MateriaDTO {
    private Integer mid;
    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;
    private String descripcion;
    //private List<DocenteDTO> docentes;

    public MateriaDTO(MateriaEntity entity) {
        mid = entity.getMid();
        nombre = entity.getNombre();
        descripcion = entity.getDescripcion();
        //docentes = entity.getDocentes().stream().map(docente -> new DocenteDTO(docente)).toList();
    }

    public MateriaEntity toEntity() {
        return new MateriaEntity(mid, nombre, descripcion);
    }
}
