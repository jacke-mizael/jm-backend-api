package mizael.jackeline.api.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mizael.jackeline.api.dto.common.response.ApiErrorResponse;
import mizael.jackeline.api.dto.usuario.request.UsuarioRequest;
import mizael.jackeline.api.dto.usuario.response.UsuarioResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Usuários",
        description = "Cadastro e administração de usuárias da plataforma de capacitação"
)
public interface UsuarioApiDoc {

    @Operation(
            summary = "Cadastrar usuário",
            description = "Registra uma nova usuária na plataforma com perfil de acesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Falha inesperada ao cadastrar usuário", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<UsuarioResponse> cadastrar(
            @RequestBody(
                    required = true,
                    description = "Dados obrigatórios para criação da usuária",
                    content = @Content(schema = @Schema(implementation = UsuarioRequest.class))
            )
            UsuarioRequest dto
    );

    @Operation(
            summary = "Listar usuários",
            description = "Retorna todas as usuárias cadastradas na plataforma."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioResponse.class))))
    })
    ResponseEntity<List<UsuarioResponse>> listar();

    @Operation(
            summary = "Buscar usuário por ID",
            description = "Consulta uma usuária específica usando o identificador único."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Falha inesperada na consulta", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<UsuarioResponse> buscarPorId(
            @Parameter(description = "ID da usuária", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza os dados de uma usuária cadastrada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Falha inesperada na atualização", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<UsuarioResponse> atualizar(
            @Parameter(description = "ID da usuária", example = "1", required = true)
            Long id,
            @RequestBody(
                    required = true,
                    description = "Novos dados da usuária",
                    content = @Content(schema = @Schema(implementation = UsuarioRequest.class))
            )
            UsuarioRequest dto
    );

    @Operation(
            summary = "Excluir usuário",
            description = "Remove permanentemente uma usuária da plataforma."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Falha inesperada na exclusão", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<Void> deletar(
            @Parameter(description = "ID da usuária", example = "1", required = true)
            Long id
    );
}