package mizael.jackeline.api.controller.video;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mizael.jackeline.api.dto.video.request.AulaRequest;
import mizael.jackeline.api.dto.video.response.AulaResponse;
import mizael.jackeline.api.service.video.AulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Aulas", description = "Endpoints responsáveis pelo gerenciamento de aulas")
@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @Operation(
            summary = "Listar todas as aulas",
            description = "Retorna uma lista contendo todas as aulas cadastradas no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de aulas retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<AulaResponse>> listarTodos() {
        return ResponseEntity.ok(aulaService.listarTodos());
    }

    @Operation(
            summary = "Buscar aula por ID",
            description = "Busca e retorna uma aula específica com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AulaResponse> buscarPorId(
            @Parameter(description = "ID da aula que será buscada", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(aulaService.buscarPorId(id));
    }

    @Operation(
            summary = "Criar uma nova aula",
            description = "Cria uma nova aula no sistema com base nos dados informados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aula criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição")
    })
    @PostMapping
    public ResponseEntity<AulaResponse> criar(
            @Valid @RequestBody AulaRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aulaService.criar(request));
    }

    @Operation(
            summary = "Atualizar aula",
            description = "Atualiza os dados de uma aula existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AulaResponse> atualizar(
            @Parameter(description = "ID da aula que será atualizada", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody AulaRequest request
    ) {
        return ResponseEntity.ok(aulaService.atualizar(id, request));
    }

    @Operation(
            summary = "Deletar aula",
            description = "Remove uma aula do sistema com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aula deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da aula que será deletada", example = "1")
            @PathVariable Long id
    ) {
        aulaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}