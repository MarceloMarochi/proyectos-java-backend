package org.example.mappers;

import org.example.dtos.TransaccionDTO;
import org.example.entities.Transaccion;

import java.util.function.Function;

public class TransaccionToTransaccionDTO implements Function<Transaccion, TransaccionDTO> {
    @Override
    public TransaccionDTO apply(Transaccion transaccion) {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setTransaccionId(transaccion.getTransaccionId());
        transaccionDTO.setUsuarioDTO(new UsuarioToUsuarioDTO().apply(transaccion.getUsuario()));
        transaccionDTO.setActivoDTO(new ActivoToActivoDTO().apply(transaccion.getActivo()));
        transaccionDTO.setTipoTransaccion(transaccion.getTipoTransaccion());
        transaccionDTO.setCantidad(transaccion.getCantidad());
        transaccionDTO.setFecha(transaccion.getFecha());

        return transaccionDTO;
    }
}
