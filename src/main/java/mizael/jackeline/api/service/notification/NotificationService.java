package mizael.jackeline.api.service.notification;

import mizael.jackeline.api.client.notification.WhatsAppClient;
import mizael.jackeline.api.dto.notification.request.WhatsAppMessageRequest;
import mizael.jackeline.api.dto.notification.response.WhatsAppMessageResponse;
import mizael.jackeline.api.dto.notification.response.WhatsAppSessionStatus;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final WhatsAppClient whatsAppClient;

    public NotificationService(WhatsAppClient whatsAppClient) {
        this.whatsAppClient = whatsAppClient;
    }

    /**
     * Valida os dados e envia mensagem via WhatsApp
     */
    public WhatsAppMessageResponse sendWhatsAppMessage(WhatsAppMessageRequest request) {
        // ValidaÃ§Ã£o de formato do telefone (apenas dÃ­gitos, mÃ­nimo 10 caracteres)
        String phone = request.phone().replaceAll("[^\\d]", "");
        if (phone.length() < 10 || phone.length() > 15) {
            return new WhatsAppMessageResponse(false, "NÃºmero de telefone invÃ¡lido. Use o formato: 5511999999999", null);
        }

        return whatsAppClient.sendText(phone, request.message());
    }

    /**
     * Retorna o status da sessÃ£o do WhatsApp
     */
    public WhatsAppSessionStatus getWhatsAppStatus() {
        return whatsAppClient.getSessionStatus();
    }
}

