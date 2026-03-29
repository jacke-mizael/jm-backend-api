package mizael.jackeline.api.controller.video;

import jakarta.validation.Valid;
import mizael.jackeline.api.dto.video.request.ModuloRequest;
import mizael.jackeline.api.dto.video.response.ModuloResponse;
import mizael.jackeline.api.service.video.ModuloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @GetMapping
    public ResponseEntity<List<ModuloResponse>> listarTodos() {
        return ResponseEntity.ok(moduloService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuloResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(moduloService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ModuloResponse> criar(@Valid @RequestBody ModuloRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(moduloService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuloResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ModuloRequest request) {
        return ResponseEntity.ok(moduloService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        moduloService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

