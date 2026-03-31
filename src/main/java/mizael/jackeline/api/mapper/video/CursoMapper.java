package mizael.jackeline.api.mapper.video;

import mizael.jackeline.api.dto.video.request.CursoRequest;
import mizael.jackeline.api.dto.video.response.CursoResponse;
import mizael.jackeline.api.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public Curso toEntity(CursoRequest request) {
        Curso curso = new Curso();
        curso.setNome(request.nome());
        curso.setDescricao(request.descricao());
        curso.setValor(request.valor());
        curso.setAtivo(request.ativo());
        return curso;
    }

    public void updateEntity(Curso curso, CursoRequest request) {
        curso.setNome(request.nome());
        curso.setDescricao(request.descricao());
        curso.setValor(request.valor());
        if (request.ativo() != null) {
            curso.setAtivo(request.ativo());
        }
    }

    public CursoResponse toResponse(Curso curso) {
        return new CursoResponse(
                curso.getId(),
                curso.getNome(),
                curso.getDescricao(),
                curso.getValor(),
                curso.getAtivo(),
                curso.getDataCriacao()
        );
    }
}

