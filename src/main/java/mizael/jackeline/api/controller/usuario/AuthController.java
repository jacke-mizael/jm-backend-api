package mizael.jackeline.api.controller.usuario;

import jakarta.validation.Valid;
import mizael.jackeline.api.controller.docs.AuthApiDoc;
import mizael.jackeline.api.dto.usuario.request.LoginRequest;
import mizael.jackeline.api.dto.usuario.response.LoginResponse;
import mizael.jackeline.api.security.JwtService;
import mizael.jackeline.api.security.TokenBlocklistService;
import mizael.jackeline.api.model.Usuario;
import mizael.jackeline.api.service.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthApiDoc {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final TokenBlocklistService tokenBlocklistService;

    public AuthController(
            UsuarioService usuarioService,
            JwtService jwtService,
            TokenBlocklistService tokenBlocklistService
    ) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
        this.tokenBlocklistService = tokenBlocklistService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Usuario usuario = usuarioService.autenticar(loginRequest.getEmail(), loginRequest.getSenha());
            String token = jwtService.gerarToken(usuario);

            return ResponseEntity.ok(new LoginResponse(
                    "Login realizado com sucesso",
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getRole(),
                    token,
                    "Bearer"
            ));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("mensagem", ex.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader
    ) {
        String token = extrairToken(authorizationHeader);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("mensagem", "Token ausente ou inválido"));
        }

        tokenBlocklistService.revogar(token);
        return ResponseEntity.ok(Map.of("mensagem", "Logout realizado com sucesso"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("mensagem", "Token ausente ou inválido"));
        }

        Usuario usuario = usuarioService.buscarPorEmail(authentication.getName());

        return ResponseEntity.ok(new LoginResponse(
                "Usuário autenticado",
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                null,
                "Bearer"
        ));
    }

    private String extrairToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }
        return authorizationHeader.substring(7);
    }
}
