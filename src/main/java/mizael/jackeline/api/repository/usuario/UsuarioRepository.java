package mizael.jackeline.api.repository.usuario;

import mizael.jackeline.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Boolean existsByEmailIgnoreCase(String email);
}
