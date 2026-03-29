package mizael.jackeline.api.dto.video.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CursoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        Boolean ativo,
        LocalDateTime dataCriacao
) {
}

