package mizael.jackeline.api.config.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WhatsAppConfig {

    @Value("${whatsapp.gateway.base-url}")
    private String gatewayBaseUrl;

    @Bean
    public RestClient whatsAppRestClient() {
        return RestClient.builder()
                .baseUrl(gatewayBaseUrl)
                .build();
    }
}

