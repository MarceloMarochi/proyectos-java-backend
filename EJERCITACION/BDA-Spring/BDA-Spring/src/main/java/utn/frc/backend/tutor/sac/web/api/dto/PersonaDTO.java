package utn.frc.backend.tutor.sac.web.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.frc.backend.tutor.sac.domain.PersonaEntity;

@Data @NoArgsConstructor
public class PersonaDTO {
    protected Integer pid;
    @NotBlank(message = "DNI es obligatorio")
    protected String dni;
    @NotBlank(message = "Apellido es obligatorio")
    protected String apellido;
    @NotBlank(message = "Nombre es obligatorio")
    protected String nombre;

    public PersonaDTO(PersonaEntity entity) {
        pid = entity.getPid();
        dni = entity.getDni();
        apellido = entity.getApellido();
        nombre = entity.getNombre();
    }

    public PersonaEntity toPersonaEntity() {
        return new PersonaEntity(pid, dni, apellido, nombre);
    }
}
