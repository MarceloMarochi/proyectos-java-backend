package utn.frc.bka.mappers;



import utn.frc.bka.dtos.UsuarioDTO;
import utn.frc.bka.entidades.Usuario;

import java.util.function.Function;

public class UsuarioToUsuarioDTO implements Function<Usuario, UsuarioDTO> {

    @Override
    public UsuarioDTO apply(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuarioId(usuario.getUsuario_id());
        usuarioDTO.setNombre(usuario.getNombre());

        return usuarioDTO;
    }
}
