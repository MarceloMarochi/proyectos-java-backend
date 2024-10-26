package utn.frc.backend.tutor.sac.web.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.frc.backend.tutor.sac.domain.DocenteEntity;

@Data @NoArgsConstructor
public class DocenteDTO extends PersonaDTO {
    @NotBlank(message = "Legajo es obligatorio")
    protected String legajo;

    public DocenteDTO(DocenteEntity entity) {
        super(entity.getPersona());
        legajo = entity.getLegajo();
    }

    public DocenteEntity toEntity() {
        return new DocenteEntity(toPersonaEntity(), legajo);
    }
}
