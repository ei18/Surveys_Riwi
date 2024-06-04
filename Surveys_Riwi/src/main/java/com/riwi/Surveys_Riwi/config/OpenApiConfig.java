package com.riwi.Surveys_Riwi.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**Configuración de Swagger*/
@Configuration
@OpenAPIDefinition(info = @Info(title = "Api for the management of surveys", version = "1.0", description = "Store and efficiently manage the survey question bank."))
public class OpenApiConfig {
}
