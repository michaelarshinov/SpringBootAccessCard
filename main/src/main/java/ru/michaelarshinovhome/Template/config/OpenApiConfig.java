package ru.michaelarshinovhome.Template.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Value(value = "${module.server.url}")
	private String moduleServerUrl;
	
	@Value(value = "${server.port}")
	private String serverPort;
	
	@Value(value = "${module.version}")
	private String moduleVersion;
	
	@Value(value = "${server.servlet.context-path}")
	private String serverServletContextPath;
	
    @Bean
    public OpenAPI customOpenAPI() {
    	Server server = new Server().url(moduleServerUrl);
    	Server localServer = new Server().url("http://localhost:" + serverPort + serverServletContextPath);
        return new OpenAPI()
        		.servers(List.of(server, localServer))
                .components(new Components().addSecuritySchemes("Authorization",
                        new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                ))
                .security(List.of(new SecurityRequirement().addList("Authorization")))
                .info(new Info().title("Module AccessControl").description(
                        "java AccessControl module for Insyres [2022]")
                		.version(moduleVersion));
    }
    /*@Bean
    public OpenAPI customOpenAPI(@Value("${openapi.service.title}") String serviceTitle, @Value("${openapi.service.version}") String serviceVersion) {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
                .info(new Info().title(serviceTitle).version(serviceVersion));
    }*/
}
