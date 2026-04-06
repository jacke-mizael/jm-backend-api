package mizael.jackeline.api.controller.video;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mizael.jackeline.api.dto.video.request.CursoRequest;
import mizael.jackeline.api.dto.video.response.CursoResponse;
import mizael.jackeline.api.service.video.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cursos", description = "Endpoints responsáveis pelo gerenciamento de cursos")
@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Operation(
            summary = "Listar todos os cursos",
            description = "Retorna uma lista contendo todos os cursos cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<CursoResponse>> listarTodos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @Operation(
            summary = "Buscar curso por ID",
            description = "Busca e retorna um curso específico com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscarPorId(
            @Parameter(description = "ID do curso que será buscado", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(cursoService.buscarPorId(id));
    }

    @Operation(
            summary = "Criar um novo curso",
            description = "Cria um novo curso no sistema com base nos dados informados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição")
    })
    @PostMapping
    public ResponseEntity<CursoResponse> criar(
            @Valid @RequestBody CursoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.criar(request));
    }

    @Operation(
            summary = "Atualizar curso",
            description = "Atualiza os dados de um curso existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> atualizar(
            @Parameter(description = "ID do curso que será atualizado", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody CursoRequest request
    ) {
        return ResponseEntity.ok(cursoService.atualizar(id, request));
    }

    @Operation(
            summary = "Deletar curso",
            description = "Remove um curso do sistema com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do curso que será deletado", example = "1")
            @PathVariable Long id
    ) {
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}