package mizael.jackeline.api.controller.notification;

import jakarta.validation.Valid;
import mizael.jackeline.api.dto.notification.request.WhatsAppMessageRequest;
import mizael.jackeline.api.dto.notification.response.WhatsAppMessageResponse;
import mizael.jackeline.api.dto.notification.response.WhatsAppSessionStatus;
import mizael.jackeline.api.service.notification.NotificationService;
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
     * Retorna o status da sessÃ£o do WhatsApp
     */
    @GetMapping("/whatsapp/status")
    public ResponseEntity<WhatsAppSessionStatus> getWhatsAppStatus() {
        return ResponseEntity.ok(notificationService.getWhatsAppStatus());
    }
}

