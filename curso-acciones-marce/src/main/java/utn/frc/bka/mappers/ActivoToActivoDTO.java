package utn.frc.bka.mappers;

import utn.frc.bka.dtos.ActivoDTO;
import utn.frc.bka.entidades.Activo;
import java.util.function.Function;

public class ActivoToActivoDTO implements Function<Activo, ActivoDTO>{

    @Override
    public ActivoDTO apply(Activo activo) {
        ActivoDTO activoDTO = new ActivoDTO();
        activoDTO.setActivoId(activo.getActivo_id());
        activoDTO.setNombreActivo(activo.getNombreActivo());
        return null;
    }
}
