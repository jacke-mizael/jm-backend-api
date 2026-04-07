package mizael.jackeline.api.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import mizael.jackeline.api.dto.common.response.ApiErrorResponse;
import mizael.jackeline.api.dto.usuario.request.LoginRequest;
import mizael.jackeline.api.dto.usuario.response.LoginResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Tag(
        name = "Autenticacao",
        description = "Operacoes de login, logout e validacao de sessao do usuario"
)
public interface AuthApiDoc {

    @Operation(
            summary = "Realizar login",
            description = "Autentica a usuaria pelo email e senha, iniciando sessao no servidor."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos de entrada", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Email ou senha invalidos", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<?> login(
            @RequestBody(
                    required = true,
                    description = "Credenciais para autenticacao da usuaria",
                    content = @Content(schema = @Schema(implementation = LoginRequest.class))
            )
            LoginRequest loginRequest,
            HttpSession session
    );

    @Operation(
            summary = "Realizar logout",
            description = "Encerra a sessao atual da usuaria autenticada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logout realizado com sucesso")
    })
    ResponseEntity<Map<String, String>> logout(HttpSession session);

    @Operation(
            summary = "Consultar usuario autenticado",
            description = "Retorna os dados basicos da usuaria vinculada a sessao atual."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario autenticado recuperado com sucesso", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Nenhuma sessao autenticada", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario da sessao nao encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<?> me(HttpSession session);
}
