package mizael.jackeline.api.dto.video.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AulaRequest(
        @NotNull(message = "Id do modulo e obrigatorio")
        Long moduloId,
        @NotBlank(message = "Titulo da aula e obrigatorio")
        String titulo,
        String descricao,
        @NotBlank(message = "Link da aula e obrigatorio")
        String linkYoutube,
        @NotNull(message = "Ordem da aula e obrigatoria")
        Integer ordem
) {
}

