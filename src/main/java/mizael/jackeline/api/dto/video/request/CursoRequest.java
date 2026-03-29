package mizael.jackeline.api.dto.video.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CursoRequest(
        @NotBlank(message = "Nome do curso e obrigatorio")
        String nome,
        String descricao,
        @NotNull(message = "Valor do curso e obrigatorio")
        BigDecimal valor,
        Boolean ativo
) {
}

