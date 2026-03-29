package mizael.jackeline.api.service.video;

import mizael.jackeline.api.dto.video.request.ModuloRequest;
import mizael.jackeline.api.dto.video.response.ModuloResponse;
import mizael.jackeline.api.mapper.video.ModuloMapper;
import mizael.jackeline.api.model.Curso;
import mizael.jackeline.api.model.Modulo;
import mizael.jackeline.api.repository.video.ModuloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModuloService {

    private final ModuloRepository moduloRepository;
    private final CursoService cursoService;
    private final ModuloMapper moduloMapper;

    public ModuloService(ModuloRepository moduloRepository, CursoService cursoService, ModuloMapper moduloMapper) {
        this.moduloRepository = moduloRepository;
        this.cursoService = cursoService;
        this.moduloMapper = moduloMapper;
    }

    public List<ModuloResponse> listarTodos() {
        return moduloRepository.findAll().stream().map(moduloMapper::toResponse).toList();
    }

    public ModuloResponse buscarPorId(Long id) {
        return moduloMapper.toResponse(buscaEntidade(id));
    }

    @Transactional
    public ModuloResponse criar(ModuloRequest request) {
        Curso curso = cursoService.buscaEntidade(request.cursoId());
        Modulo modulo = moduloMapper.toEntity(request, curso);
        return moduloMapper.toResponse(moduloRepository.save(modulo));
    }

    @Transactional
    public ModuloResponse atualizar(Long id, ModuloRequest request) {
        Curso curso = cursoService.buscaEntidade(request.cursoId());
        Modulo modulo = buscaEntidade(id);
        moduloMapper.updateEntity(modulo, request, curso);
        return moduloMapper.toResponse(moduloRepository.save(modulo));
    }

    @Transactional
    public void deletar(Long id) {
        moduloRepository.delete(buscaEntidade(id));
    }

    public Modulo buscaEntidade(Long id) {
        return moduloRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "Modulo nao encontrado"
                ));
    }
}

