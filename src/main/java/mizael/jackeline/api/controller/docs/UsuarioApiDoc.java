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
        name = "Usuarios",
        description = "Cadastro e administracao de usuarias da plataforma de capacitacao"
)
public interface UsuarioApiDoc {

    @Operation(
            summary = "Cadastrar usuario",
            description = "Registra uma nova usuaria na plataforma com perfil de acesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario cadastrado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos para cadastro", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Email ja cadastrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Falha inesperada ao cadastrar usuario", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<UsuarioResponse> cadastrar(
            @RequestBody(
                    required = true,
                    description = "Dados obrigatorios para criacao da usuaria",
                    content = @Content(schema = @Schema(implementation = UsuarioRequest.class))
            )
            UsuarioRequest dto
    );

    @Operation(
            summary = "Listar usuarios",
            description = "Retorna todas as usuarias cadastradas na plataforma."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioResponse.class))))
    })
    ResponseEntity<List<UsuarioResponse>> listar();

    @Operation(
            summary = "Buscar usuario por ID",
            description = "Consulta uma usuaria especifica usando o identificador unico."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario nao encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Falha inesperada na consulta", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<UsuarioResponse> buscarPorId(
            @Parameter(description = "ID da usuaria", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Atualizar usuario",
            description = "Atualiza os dados de uma usuaria cadastrada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos para atualizacao", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario nao encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Falha inesperada na atualizacao", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<UsuarioResponse> atualizar(
            @Parameter(description = "ID da usuaria", example = "1", required = true)
            Long id,
            @RequestBody(
                    required = true,
                    description = "Novos dados da usuaria",
                    content = @Content(schema = @Schema(implementation = UsuarioRequest.class))
            )
            UsuarioRequest dto
    );

    @Operation(
            summary = "Excluir usuario",
            description = "Remove permanentemente uma usuaria da plataforma."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario nao encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Falha inesperada na exclusao", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<Void> deletar(
            @Parameter(description = "ID da usuaria", example = "1", required = true)
            Long id
    );
}
