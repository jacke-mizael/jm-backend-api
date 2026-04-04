package mizael.jackeline.api.service.usuario;

import mizael.jackeline.api.dto.usuario.request.UsuarioRequest;
import mizael.jackeline.api.model.Usuario;
import mizael.jackeline.api.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }


    public Usuario cadastrar(Usuario usuario) {
        if (repository.existsByEmailIgnoreCase(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = repository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        return usuario;
    }

    public Usuario atualizar(Long id, UsuarioRequest dto) {

        Usuario usuario = buscarPorId(id);

        usuario.setNome(dto.getNome());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRole(dto.getRole());

        return repository.save(usuario);
    }

    public void deletar(Long id) {

        Usuario usuario = buscarPorId(id);

        repository.delete(usuario);
    }
}


