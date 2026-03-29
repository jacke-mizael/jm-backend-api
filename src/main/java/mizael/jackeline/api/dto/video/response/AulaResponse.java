package mizael.jackeline.api.dto.video.response;

public record AulaResponse(
        Long id,
        Long moduloId,
        String moduloTitulo,
        String titulo,
        String descricao,
        String linkYoutube,
        Integer ordem
) {
}

