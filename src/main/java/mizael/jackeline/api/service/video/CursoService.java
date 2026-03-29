package mizael.jackeline.api.service.video;

import mizael.jackeline.api.dto.video.request.CursoRequest;
import mizael.jackeline.api.dto.video.response.CursoResponse;
import mizael.jackeline.api.mapper.video.CursoMapper;
import mizael.jackeline.api.model.Curso;
import mizael.jackeline.api.repository.video.CursoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    public List<CursoResponse> listarTodos() {
        return cursoRepository.findAll().stream().map(cursoMapper::toResponse).toList();
    }

    public CursoResponse buscarPorId(Long id) {
        return cursoMapper.toResponse(buscaEntidade(id));
    }

    @Transactional
    public CursoResponse criar(CursoRequest request) {
        Curso curso = cursoMapper.toEntity(request);
        curso.setAtivo(curso.getAtivo() != null ? curso.getAtivo() : Boolean.TRUE);
        curso.setDataCriacao(LocalDateTime.now());
        return cursoMapper.toResponse(cursoRepository.save(curso));
    }

    @Transactional
    public CursoResponse atualizar(Long id, CursoRequest request) {
        Curso curso = buscaEntidade(id);
        cursoMapper.updateEntity(curso, request);
        return cursoMapper.toResponse(cursoRepository.save(curso));
    }

    @Transactional
    public void deletar(Long id) {
        cursoRepository.delete(buscaEntidade(id));
    }

    public Curso buscaEntidade(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso nao encontrado"));
    }
}

