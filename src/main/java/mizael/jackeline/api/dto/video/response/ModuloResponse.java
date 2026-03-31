package mizael.jackeline.api.dto.video.response;

public record ModuloResponse(
        Long id,
        Long cursoId,
        String cursoNome,
        String titulo,
        Integer ordem
) {
}

