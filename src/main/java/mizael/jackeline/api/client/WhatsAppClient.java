package mizael.jackeline.api.client;

import mizael.jackeline.api.dto.WhatsAppMessageResponse;
import mizael.jackeline.api.dto.WhatsAppSessionStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@Component
public class WhatsAppClient {

    private final RestClient restClient;

    public WhatsAppClient(RestClient whatsAppRestClient) {
        this.restClient = whatsAppRestClient;
    }

    /**
     * Envia uma mensagem de texto para o número informado via gateway Node.js
     */
    public WhatsAppMessageResponse sendText(String phone, String message) {
        try {
            return restClient.post()
                    .uri("/send-text")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("phone", phone, "message", message))
                    .retrieve()
                    .body(WhatsAppMessageResponse.class);
        } catch (RestClientException e) {
            return new WhatsAppMessageResponse(false, "Erro ao comunicar com gateway WhatsApp: " + e.getMessage(), null);
        }
    }

    /**
     * Consulta o status da sessão do WhatsApp no gateway Node.js
     */
    public WhatsAppSessionStatus getSessionStatus() {
        try {
            return restClient.get()
                    .uri("/status-session")
                    .retrieve()
                    .body(WhatsAppSessionStatus.class);
        } catch (RestClientException e) {
            return new WhatsAppSessionStatus("unavailable", false);
        }
    }
}
