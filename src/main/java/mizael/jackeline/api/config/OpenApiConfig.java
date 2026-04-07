package mizael.jackeline.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Jackeline Mizael API - Plataforma de Cursos de Manicure",
                version = "v1",
                description = "API REST em camadas para autenticacao e gestao de usuarios, cursos, modulos e aulas. " +
                        "A plataforma apoia a capacitacao profissional de mulheres em situacao de vulnerabilidade por meio " +
                        "de trilhas de aprendizado em Nail Design.",
                contact = @Contact(
                        name = "Equipe Jackeline Mizael",
                        email = "contato@jackelinemizael.com"
                ),
                license = @License(
                        name = "Uso educacional e social"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Ambiente local de desenvolvimento")
        }
)
public class OpenApiConfig {
}
