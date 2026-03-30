package mizael.jackeline.api.mapper.usuario;

import mizael.jackeline.api.dto.usuario.request.UsuarioRequest;
import mizael.jackeline.api.dto.usuario.response.UsuarioResponse;
import mizael.jackeline.api.model.Usuario;

import java.time.LocalDateTime;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest dto) {

        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRole(dto.getRole());

        usuario.setDataCriacao(LocalDateTime.now());

        return usuario;
    }

    public static UsuarioResponse toResponse(Usuario usuario) {

        UsuarioResponse dto = new UsuarioResponse();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setRole(usuario.getRole());
        dto.setDataCriacao(usuario.getDataCriacao());

        return dto;
    }
}
