package mizael.jackeline.api.controller.usuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import mizael.jackeline.api.dto.usuario.request.LoginRequest;
import mizael.jackeline.api.dto.usuario.response.LoginResponse;
import mizael.jackeline.api.model.Usuario;
import mizael.jackeline.api.service.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final String USUARIO_LOGADO = "USUARIO_LOGADO";

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest, HttpSession session) {
        try {
            Usuario usuario = usuarioService.autenticar(loginRequest.getEmail(), loginRequest.getSenha());

            session.setAttribute(USUARIO_LOGADO, usuario.getId());

            return ResponseEntity.ok(new LoginResponse(
                    "Login realizado com sucesso",
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getRole()
            ));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("mensagem", ex.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("mensagem", "Logout realizado com sucesso"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Object usuarioId = session.getAttribute(USUARIO_LOGADO);

        if (usuarioId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("mensagem", "Nenhum usuário autenticado"));
        }

        Usuario usuario = usuarioService.buscarPorId((Long) usuarioId);

        return ResponseEntity.ok(new LoginResponse(
                "Usuário autenticado",
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        ));
    }
}
