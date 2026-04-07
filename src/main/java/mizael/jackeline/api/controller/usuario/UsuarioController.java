package mizael.jackeline.api.controller.usuario;

import jakarta.validation.Valid;
import mizael.jackeline.api.controller.docs.UsuarioApiDoc;
import mizael.jackeline.api.dto.usuario.request.UsuarioRequest;
import mizael.jackeline.api.dto.usuario.response.UsuarioResponse;
import mizael.jackeline.api.mapper.usuario.UsuarioMapper;
import mizael.jackeline.api.model.Usuario;
import mizael.jackeline.api.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioApiDoc {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(
            @RequestBody @Valid UsuarioRequest dto) {

        Usuario usuario = UsuarioMapper.toEntity(dto);

        Usuario salvo = service.cadastrar(usuario);

        return ResponseEntity.status(201)
                .body(UsuarioMapper.toResponse(salvo));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {

        List<Usuario> usuarios = service.listar();

        List<UsuarioResponse> resposta = usuarios.stream()
                .map(UsuarioMapper::toResponse)
                .toList();

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {

        Usuario usuario = service.buscarPorId(id);

        return ResponseEntity.ok(UsuarioMapper.toResponse(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioRequest dto) {

        Usuario atualizado = service.atualizar(id, dto);

        return ResponseEntity.ok(UsuarioMapper.toResponse(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}