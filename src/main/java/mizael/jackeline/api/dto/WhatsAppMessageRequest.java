package mizael.jackeline.api.dto;

import jakarta.validation.constraints.NotBlank;

public record WhatsAppMessageRequest(
        @NotBlank(message = "O campo 'phone' é obrigatório")
        String phone,

        @NotBlank(message = "O campo 'message' é obrigatório")
        String message
) {}
