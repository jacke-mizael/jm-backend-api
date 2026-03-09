package mizael.jackeline.api.controller;

import jakarta.validation.Valid;
import mizael.jackeline.api.dto.WhatsAppMessageRequest;
import mizael.jackeline.api.dto.WhatsAppMessageResponse;
import mizael.jackeline.api.dto.WhatsAppSessionStatus;
import mizael.jackeline.api.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * POST /notifications/whatsapp
     * Envia uma mensagem via WhatsApp
     */
    @PostMapping("/whatsapp")
    public ResponseEntity<WhatsAppMessageResponse> sendWhatsAppMessage(
            @Valid @RequestBody WhatsAppMessageRequest request) {

        WhatsAppMessageResponse response = notificationService.sendWhatsAppMessage(request);

        if (response.success()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * GET /notifications/whatsapp/status
     * Retorna o status da sessão do WhatsApp
     */
    @GetMapping("/whatsapp/status")
    public ResponseEntity<WhatsAppSessionStatus> getWhatsAppStatus() {
        return ResponseEntity.ok(notificationService.getWhatsAppStatus());
    }
}
