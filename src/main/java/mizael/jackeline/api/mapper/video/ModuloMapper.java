package mizael.jackeline.api.mapper.video;

import mizael.jackeline.api.dto.video.request.ModuloRequest;
import mizael.jackeline.api.dto.video.response.ModuloResponse;
import mizael.jackeline.api.model.Curso;
import mizael.jackeline.api.model.Modulo;
import org.springframework.stereotype.Component;

@Component
public class ModuloMapper {

    public Modulo toEntity(ModuloRequest request, Curso curso) {
        Modulo modulo = new Modulo();
        modulo.setCurso(curso);
        modulo.setTitulo(request.titulo());
        modulo.setOrdem(request.ordem());
        return modulo;
    }

    public void updateEntity(Modulo modulo, ModuloRequest request, Curso curso) {
        modulo.setCurso(curso);
        modulo.setTitulo(request.titulo());
        modulo.setOrdem(request.ordem());
    }

    public ModuloResponse toResponse(Modulo modulo) {
        return new ModuloResponse(
                modulo.getId(),
                modulo.getCurso() != null ? modulo.getCurso().getId() : null,
                modulo.getCurso() != null ? modulo.getCurso().getNome() : null,
                modulo.getTitulo(),
                modulo.getOrdem()
        );
    }
}

