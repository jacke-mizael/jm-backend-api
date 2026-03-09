package mizael.jackeline.api.service;

import mizael.jackeline.api.client.WhatsAppClient;
import mizael.jackeline.api.dto.WhatsAppMessageRequest;
import mizael.jackeline.api.dto.WhatsAppMessageResponse;
import mizael.jackeline.api.dto.WhatsAppSessionStatus;
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
        // Validação de formato do telefone (apenas dígitos, mínimo 10 caracteres)
        String phone = request.phone().replaceAll("[^\\d]", "");
        if (phone.length() < 10 || phone.length() > 15) {
            return new WhatsAppMessageResponse(false, "Número de telefone inválido. Use o formato: 5511999999999", null);
        }

        return whatsAppClient.sendText(phone, request.message());
    }

    /**
     * Retorna o status da sessão do WhatsApp
     */
    public WhatsAppSessionStatus getWhatsAppStatus() {
        return whatsAppClient.getSessionStatus();
    }
}
