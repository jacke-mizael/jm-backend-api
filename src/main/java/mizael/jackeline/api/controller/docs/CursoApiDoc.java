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
import mizael.jackeline.api.dto.video.request.CursoRequest;
import mizael.jackeline.api.dto.video.response.CursoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Cursos",
        description = "Gestao dos cursos de manicure e nail design oferecidos na plataforma"
)
public interface CursoApiDoc {

    @Operation(
            summary = "Listar cursos",
            description = "Retorna todos os cursos cadastrados para exibicao no catalogo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CursoResponse.class))))
    })
    ResponseEntity<List<CursoResponse>> listarTodos();

    @Operation(
            summary = "Buscar curso por ID",
            description = "Consulta os detalhes de um curso especifico."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado", content = @Content(schema = @Schema(implementation = CursoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Curso nao encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<CursoResponse> buscarPorId(
            @Parameter(description = "ID do curso", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Criar curso",
            description = "Cria um novo curso com titulo, descricao, valor e status de publicacao."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso", content = @Content(schema = @Schema(implementation = CursoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos para criacao", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<CursoResponse> criar(
            @RequestBody(
                    required = true,
                    description = "Dados para criacao do curso",
                    content = @Content(schema = @Schema(implementation = CursoRequest.class))
            )
            CursoRequest request
    );

    @Operation(
            summary = "Atualizar curso",
            description = "Atualiza os dados de um curso existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso", content = @Content(schema = @Schema(implementation = CursoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos para atualizacao", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Curso nao encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<CursoResponse> atualizar(
            @Parameter(description = "ID do curso", example = "1", required = true)
            Long id,
            @RequestBody(
                    required = true,
                    description = "Dados para atualizacao do curso",
                    content = @Content(schema = @Schema(implementation = CursoRequest.class))
            )
            CursoRequest request
    );

    @Operation(
            summary = "Excluir curso",
            description = "Remove um curso do catalogo da plataforma."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso nao encontrado", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<Void> deletar(
            @Parameter(description = "ID do curso", example = "1", required = true)
            Long id
    );
}
