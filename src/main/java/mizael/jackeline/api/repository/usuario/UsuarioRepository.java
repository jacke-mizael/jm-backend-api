package mizael.jackeline.api.repository.usuario;

public interface UsuarioRepository {

    Boolean existsByEmailIgnoreCase(String email);
}
