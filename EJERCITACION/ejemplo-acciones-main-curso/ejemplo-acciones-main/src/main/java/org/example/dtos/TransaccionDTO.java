package org.example.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionDTO {
    private Long transaccionId;
    private UsuarioDTO usuarioDTO;
    private ActivoDTO activoDTO;
    private String tipoTransaccion;
    private int cantidad;
    private LocalDate fecha;

    @Override
    public String toString() {
        return "TransaccionDTO{" +
                "transaccionId=" + transaccionId +
                ", usuarioDTO=" + usuarioDTO.toString() +
                ", activoDTO=" + activoDTO.toString() +
                ", tipoTransaccion='" + tipoTransaccion + '\'' +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}';
    }
}
