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
import mizael.jackeline.api.dto.video.request.AulaRequest;
import mizael.jackeline.api.dto.video.response.AulaResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Aulas",
        description = "Gestão das aulas em vídeo dos módulos de cada curso"
)
public interface AulaApiDoc {

    @Operation(
            summary = "Listar aulas",
            description = "Retorna todas as aulas cadastradas na plataforma."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de aulas retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AulaResponse.class))))
    })
    ResponseEntity<List<AulaResponse>> listarTodos();

    @Operation(
            summary = "Buscar aula por ID",
            description = "Consulta os dados de uma aula específica."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula encontrada", content = @Content(schema = @Schema(implementation = AulaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<AulaResponse> buscarPorId(
            @Parameter(description = "ID da aula", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Criar aula",
            description = "Cria uma nova aula vinculada a um módulo existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aula criada com sucesso", content = @Content(schema = @Schema(implementation = AulaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Módulo vinculado não encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<AulaResponse> criar(
            @RequestBody(
                    required = true,
                    description = "Dados para criação da aula",
                    content = @Content(schema = @Schema(implementation = AulaRequest.class))
            )
            AulaRequest request
    );

    @Operation(
            summary = "Atualizar aula",
            description = "Atualiza os dados de uma aula existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula atualizada com sucesso", content = @Content(schema = @Schema(implementation = AulaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Aula ou módulo vinculado não encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<AulaResponse> atualizar(
            @Parameter(description = "ID da aula", example = "1", required = true)
            Long id,
            @RequestBody(
                    required = true,
                    description = "Dados para atualização da aula",
                    content = @Content(schema = @Schema(implementation = AulaRequest.class))
            )
            AulaRequest request
    );

    @Operation(
            summary = "Excluir aula",
            description = "Remove uma aula do módulo correspondente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aula removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<Void> deletar(
            @Parameter(description = "ID da aula", example = "1", required = true)
            Long id
    );
}