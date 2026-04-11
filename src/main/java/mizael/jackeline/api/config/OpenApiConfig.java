package mizael.jackeline.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Jackeline Mizael API - Plataforma de Cursos de Nail Designer",
                version = "v1",
                description = "API REST em camadas para autenticação e gestão de usuários, cursos, módulos e aulas. " +
                        "A plataforma apoia a capacitacão profissional de mulheres em situacão de vulnerabilidade por meio " +
                        "de trilhas de aprendizado em Nail Design.",
                contact = @Contact(
                        name = "Equipe Jackeline Mizael",
                        email = "grupo06jacke@gmail.com"
                ),
                license = @License(
                        name = "Uso educacional e social"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8081", description = "Ambiente local de desenvolvimento")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
