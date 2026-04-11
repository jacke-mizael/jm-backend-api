package mizael.jackeline.api.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mizael.jackeline.api.dto.common.response.ApiErrorResponse;
import mizael.jackeline.api.dto.usuario.request.LoginRequest;
import mizael.jackeline.api.dto.usuario.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@Tag(
        name = "Autenticação",
        description = "Operações de login, logout e validação do token JWT"
)
public interface AuthApiDoc {

    @Operation(
            summary = "Realizar login",
            description = "Autentica a usuária pelo e-mail e senha e retorna token JWT."
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
            LoginRequest loginRequest
    );

    @Operation(
            summary = "Realizar logout",
            description = "Invalida o token JWT atual da usuária autenticada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logout realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<Map<String, String>> logout(
            @Parameter(description = "Token JWT no formato Bearer {token}", required = true)
            @RequestHeader(value = "Authorization", required = false)
            String authorizationHeader
    );

    @Operation(
            summary = "Consultar usuário autenticado",
            description = "Retorna os dados básicos da usuária com base no token JWT enviado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado recuperado com sucesso", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário do token não encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<?> me(Authentication authentication);
}