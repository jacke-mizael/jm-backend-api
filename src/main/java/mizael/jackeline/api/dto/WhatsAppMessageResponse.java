package mizael.jackeline.api.dto;

public record WhatsAppMessageResponse(
        boolean success,
        String message,
        Object details
) {}
