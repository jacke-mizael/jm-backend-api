package mizael.jackeline.api.mapper.video;

import mizael.jackeline.api.dto.video.request.AulaRequest;
import mizael.jackeline.api.dto.video.response.AulaResponse;
import mizael.jackeline.api.model.Aula;
import mizael.jackeline.api.model.Modulo;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public Aula toEntity(AulaRequest request, Modulo modulo) {
        Aula aula = new Aula();
        aula.setModulo(modulo);
        aula.setTitulo(request.titulo());
        aula.setDescricao(request.descricao());
        aula.setLinkYoutube(request.linkYoutube());
        aula.setOrdem(request.ordem());
        return aula;
    }

    public void updateEntity(Aula aula, AulaRequest request, Modulo modulo) {
        aula.setModulo(modulo);
        aula.setTitulo(request.titulo());
        aula.setDescricao(request.descricao());
        aula.setLinkYoutube(request.linkYoutube());
        aula.setOrdem(request.ordem());
    }

    public AulaResponse toResponse(Aula aula) {
        return new AulaResponse(
                aula.getId(),
                aula.getModulo() != null ? aula.getModulo().getId() : null,
                aula.getModulo() != null ? aula.getModulo().getTitulo() : null,
                aula.getTitulo(),
                aula.getDescricao(),
                aula.getLinkYoutube(),
                aula.getOrdem()
        );
    }
}

