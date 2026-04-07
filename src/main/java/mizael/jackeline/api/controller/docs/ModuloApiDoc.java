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
import mizael.jackeline.api.dto.video.request.ModuloRequest;
import mizael.jackeline.api.dto.video.response.ModuloResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Modulos",
        description = "Gestao dos modulos que estruturam cada curso"
)
public interface ModuloApiDoc {

    @Operation(
            summary = "Listar modulos",
            description = "Retorna todos os modulos cadastrados na plataforma."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de modulos retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ModuloResponse.class))))
    })
    ResponseEntity<List<ModuloResponse>> listarTodos();

    @Operation(
            summary = "Buscar modulo por ID",
            description = "Consulta os dados de um modulo especifico."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modulo encontrado", content = @Content(schema = @Schema(implementation = ModuloResponse.class))),
            @ApiResponse(responseCode = "404", description = "Modulo nao encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<ModuloResponse> buscarPorId(
            @Parameter(description = "ID do modulo", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Criar modulo",
            description = "Cria um novo modulo associado a um curso existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Modulo criado com sucesso", content = @Content(schema = @Schema(implementation = ModuloResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos para criacao", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Curso vinculado nao encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<ModuloResponse> criar(
            @RequestBody(
                    required = true,
                    description = "Dados para criacao do modulo",
                    content = @Content(schema = @Schema(implementation = ModuloRequest.class))
            )
            ModuloRequest request
    );

    @Operation(
            summary = "Atualizar modulo",
            description = "Atualiza titulo, ordem e curso vinculado de um modulo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modulo atualizado com sucesso", content = @Content(schema = @Schema(implementation = ModuloResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos para atualizacao", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Modulo ou curso vinculado nao encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<ModuloResponse> atualizar(
            @Parameter(description = "ID do modulo", example = "1", required = true)
            Long id,
            @RequestBody(
                    required = true,
                    description = "Dados para atualizacao do modulo",
                    content = @Content(schema = @Schema(implementation = ModuloRequest.class))
            )
            ModuloRequest request
    );

    @Operation(
            summary = "Excluir modulo",
            description = "Remove um modulo da estrutura do curso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modulo removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Modulo nao encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<Void> deletar(
            @Parameter(description = "ID do modulo", example = "1", required = true)
            Long id
    );
}
