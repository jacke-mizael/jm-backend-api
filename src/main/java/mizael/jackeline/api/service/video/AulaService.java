package mizael.jackeline.api.service.video;

import mizael.jackeline.api.dto.video.request.AulaRequest;
import mizael.jackeline.api.dto.video.response.AulaResponse;
import mizael.jackeline.api.mapper.video.AulaMapper;
import mizael.jackeline.api.model.Aula;
import mizael.jackeline.api.model.Modulo;
import mizael.jackeline.api.repository.video.AulaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final ModuloService moduloService;
    private final AulaMapper aulaMapper;

    public AulaService(AulaRepository aulaRepository, ModuloService moduloService, AulaMapper aulaMapper) {
        this.aulaRepository = aulaRepository;
        this.moduloService = moduloService;
        this.aulaMapper = aulaMapper;
    }

    public List<AulaResponse> listarTodos() {
        return aulaRepository.findAll().stream().map(aulaMapper::toResponse).toList();
    }

    public AulaResponse buscarPorId(Long id) {
        return aulaMapper.toResponse(buscaEntidade(id));
    }

    @Transactional
    public AulaResponse criar(AulaRequest request) {
        Modulo modulo = moduloService.buscaEntidade(request.moduloId());
        Aula aula = aulaMapper.toEntity(request, modulo);
        return aulaMapper.toResponse(aulaRepository.save(aula));
    }

    @Transactional
    public AulaResponse atualizar(Long id, AulaRequest request) {
        Modulo modulo = moduloService.buscaEntidade(request.moduloId());
        Aula aula = buscaEntidade(id);
        aulaMapper.updateEntity(aula, request, modulo);
        return aulaMapper.toResponse(aulaRepository.save(aula));
    }

    @Transactional
    public void deletar(Long id) {
        aulaRepository.delete(buscaEntidade(id));
    }

    public Aula buscaEntidade(Long id) {
        return aulaRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "Aula nao encontrada"
                ));
    }
}

