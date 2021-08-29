package com.tenniscourts.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.tenniscourts"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Tennis Cour API Spring Boot",
                "This API is used for academic purposes",
                "Versão 1.0",
                "https://github.com/siqueira76/sampleomc.git",
                new Contact("José Carlos Siqueira", "https://www.linkedin.com/in/siqueira1/",
                        "josecarlos.siqueira76@gmail.com"),
                "academic purposes",
                "https://github.com/siqueira76/sampleomc.git",
                Collections.emptyList()
        );
    }
}
