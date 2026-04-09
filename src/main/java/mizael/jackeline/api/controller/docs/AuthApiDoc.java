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
        name = "Autenticação",
        description = "Operações de login, logout e validação de sessão do usuário"
)
public interface AuthApiDoc {

    @Operation(
            summary = "Realizar login",
            description = "Autentica a usuária pelo e-mail e senha, iniciando sessão no servidor."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos de entrada", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "E-mail ou senha inválidos", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<?> login(
            @RequestBody(
                    required = true,
                    description = "Credenciais para autenticação da usuária",
                    content = @Content(schema = @Schema(implementation = LoginRequest.class))
            )
            LoginRequest loginRequest,
            HttpSession session
    );

    @Operation(
            summary = "Realizar logout",
            description = "Encerra a sessão atual da usuária autenticada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logout realizado com sucesso")
    })
    ResponseEntity<Map<String, String>> logout(HttpSession session);

    @Operation(
            summary = "Consultar usuário autenticado",
            description = "Retorna os dados básicos da usuária vinculada à sessão atual."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado recuperado com sucesso", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Nenhuma sessão autenticada", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário da sessão não encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<?> me(HttpSession session);
}