package mizael.jackeline.api.dto.common.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ApiErrorResponse", description = "Estrutura padrao para respostas de erro da API")
public record ApiErrorResponse(
        @Schema(description = "Data e hora do erro", example = "2026-04-07T14:15:22")
        String timestamp,
        @Schema(description = "Codigo HTTP do erro", example = "404")
        Integer status,
        @Schema(description = "Descricao curta do erro", example = "Not Found")
        String error,
        @Schema(description = "Mensagem detalhada para apoio na correcao", example = "Curso nao encontrado")
        String mensagem,
        @Schema(description = "Caminho do endpoint que gerou o erro", example = "/cursos/99")
        String path
) {
}
