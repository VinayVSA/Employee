package com.cg.in.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

@Configuration
public class EmpopenApiConfig {
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee application API")
                        .version("1.0")
                        .description("API documentation for Application Employee"));
    }

}
