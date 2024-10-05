package org.example.mappers;

import org.example.dtos.ActivoDTO;
import org.example.entities.Activo;

import java.util.function.Function;

public class ActivoToActivoDTO implements Function<Activo, ActivoDTO> {
    @Override
    public ActivoDTO apply(Activo activo) {
        ActivoDTO activoDTO = new ActivoDTO();
        activoDTO.setActivoId(activo.getActivoId());
        activoDTO.setNombreActivo(activo.getNombreActivo());

        return activoDTO;
    }
}
