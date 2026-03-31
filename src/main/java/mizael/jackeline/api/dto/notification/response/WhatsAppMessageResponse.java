package mizael.jackeline.api.dto.notification.response;

public record WhatsAppMessageResponse(
        boolean success,
        String message,
        Object details
) {}

