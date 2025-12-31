package com.foodies.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customConfig(){
        return  new OpenAPI().info(
                new Info().title("Foodies App APIs")
                        .description("Find All the apis listed below")
        ).tags(Arrays.asList(new Tag().name("Register, Login and users APIs")));
    }
}
