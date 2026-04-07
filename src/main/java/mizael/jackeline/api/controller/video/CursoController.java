package mizael.jackeline.api.controller.video;

import jakarta.validation.Valid;
import mizael.jackeline.api.controller.docs.CursoApiDoc;
import mizael.jackeline.api.dto.video.request.CursoRequest;
import mizael.jackeline.api.dto.video.response.CursoResponse;
import mizael.jackeline.api.service.video.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController implements CursoApiDoc {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<CursoResponse>> listarTodos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(cursoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CursoResponse> criar(
            @Valid @RequestBody CursoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CursoRequest request
    ) {
        return ResponseEntity.ok(cursoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}