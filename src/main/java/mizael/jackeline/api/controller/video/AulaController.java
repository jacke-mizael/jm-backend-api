package mizael.jackeline.api.controller.video;

import jakarta.validation.Valid;
import mizael.jackeline.api.dto.video.request.AulaRequest;
import mizael.jackeline.api.dto.video.response.AulaResponse;
import mizael.jackeline.api.service.video.AulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping
    public ResponseEntity<List<AulaResponse>> listarTodos() {
        return ResponseEntity.ok(aulaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(aulaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AulaResponse> criar(@Valid @RequestBody AulaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aulaService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AulaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody AulaRequest request) {
        return ResponseEntity.ok(aulaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        aulaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

