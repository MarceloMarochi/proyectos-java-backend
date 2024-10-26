package utn.frc.backend.tutor.sac.web.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.frc.backend.tutor.sac.domain.AlumnoEntity;

@Data
@NoArgsConstructor
public class AlumnoDTO extends PersonaDTO {
    @NotBlank(message = "Legajo es obligatorio")
    protected String legajo;

    public AlumnoDTO(AlumnoEntity entity) {
        super(entity.getPersona());
        legajo = entity.getLegajo();
    }

    public AlumnoEntity toEntity() {
        return new AlumnoEntity(toPersonaEntity(), legajo);
    }
}
