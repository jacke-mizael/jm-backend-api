package mizael.jackeline.api.controller.video;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mizael.jackeline.api.dto.video.request.ModuloRequest;
import mizael.jackeline.api.dto.video.response.ModuloResponse;
import mizael.jackeline.api.service.video.ModuloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Módulos", description = "Endpoints responsáveis pelo gerenciamento de módulos")
@RestController
@RequestMapping("/modulos")
public class ModuloController {

    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @Operation(
            summary = "Listar todos os módulos",
            description = "Retorna uma lista contendo todos os módulos cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de módulos retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<ModuloResponse>> listarTodos() {
        return ResponseEntity.ok(moduloService.listarTodos());
    }

    @Operation(
            summary = "Buscar módulo por ID",
            description = "Busca e retorna um módulo específico com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Módulo encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Módulo não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModuloResponse> buscarPorId(
            @Parameter(description = "ID do módulo que será buscado", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(moduloService.buscarPorId(id));
    }

    @Operation(
            summary = "Criar um novo módulo",
            description = "Cria um novo módulo no sistema com base nos dados informados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Módulo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição")
    })
    @PostMapping
    public ResponseEntity<ModuloResponse> criar(
            @Valid @RequestBody ModuloRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(moduloService.criar(request));
    }

    @Operation(
            summary = "Atualizar módulo",
            description = "Atualiza os dados de um módulo existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Módulo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Módulo não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModuloResponse> atualizar(
            @Parameter(description = "ID do módulo que será atualizado", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ModuloRequest request
    ) {
        return ResponseEntity.ok(moduloService.atualizar(id, request));
    }

    @Operation(
            summary = "Deletar módulo",
            description = "Remove um módulo do sistema com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Módulo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Módulo não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do módulo que será deletado", example = "1")
            @PathVariable Long id
    ) {
        moduloService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}