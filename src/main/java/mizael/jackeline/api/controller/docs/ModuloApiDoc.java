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
        name = "Módulos",
        description = "Gestão dos módulos que estruturam cada curso"
)
public interface ModuloApiDoc {

    @Operation(
            summary = "Listar módulos",
            description = "Retorna todos os módulos cadastrados na plataforma."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de módulos retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ModuloResponse.class))))
    })
    ResponseEntity<List<ModuloResponse>> listarTodos();

    @Operation(
            summary = "Buscar módulo por ID",
            description = "Consulta os dados de um módulo específico."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Módulo encontrado", content = @Content(schema = @Schema(implementation = ModuloResponse.class))),
            @ApiResponse(responseCode = "404", description = "Módulo não encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<ModuloResponse> buscarPorId(
            @Parameter(description = "ID do módulo", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Criar módulo",
            description = "Cria um novo módulo associado a um curso existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Módulo criado com sucesso", content = @Content(schema = @Schema(implementation = ModuloResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Curso vinculado não encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<ModuloResponse> criar(
            @RequestBody(
                    required = true,
                    description = "Dados para criação do módulo",
                    content = @Content(schema = @Schema(implementation = ModuloRequest.class))
            )
            ModuloRequest request
    );

    @Operation(
            summary = "Atualizar módulo",
            description = "Atualiza título, ordem e curso vinculado de um módulo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Módulo atualizado com sucesso", content = @Content(schema = @Schema(implementation = ModuloResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Módulo ou curso vinculado não encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<ModuloResponse> atualizar(
            @Parameter(description = "ID do módulo", example = "1", required = true)
            Long id,
            @RequestBody(
                    required = true,
                    description = "Dados para atualização do módulo",
                    content = @Content(schema = @Schema(implementation = ModuloRequest.class))
            )
            ModuloRequest request
    );

    @Operation(
            summary = "Excluir módulo",
            description = "Remove um módulo da estrutura do curso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Módulo removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Módulo não encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<Void> deletar(
            @Parameter(description = "ID do módulo", example = "1", required = true)
            Long id
    );
}