package org.example.mappers;

import org.example.dtos.UsuarioDTO;
import org.example.entities.Usuario;

import java.util.function.Function;

public class UsuarioToUsuarioDTO implements Function<Usuario, UsuarioDTO> {

    @Override
    public UsuarioDTO apply(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuarioId(usuario.getUsuarioId());
        usuarioDTO.setNombre(usuario.getNombre());

        return usuarioDTO;
    }
}
