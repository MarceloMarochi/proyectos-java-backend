package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long usuarioId;
    private String nombre;

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "usuarioId=" + usuarioId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
