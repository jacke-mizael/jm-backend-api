package mizael.jackeline.api.dto.usuario.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String mensagem;
    private Long usuarioId;
    private String nome;
    private String email;
    private String role;
    private String token;
    private String tokenType;
}
