package mizael.jackeline.api.dto.video.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModuloRequest(
        @NotNull(message = "Id do curso e obrigatorio")
        Long cursoId,
        @NotBlank(message = "Titulo do modulo e obrigatorio")
        String titulo,
        Integer ordem
) {
}

