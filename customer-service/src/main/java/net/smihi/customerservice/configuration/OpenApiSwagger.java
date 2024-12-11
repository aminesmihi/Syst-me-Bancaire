package net.smihi.customerservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
public class OpenApiSwagger {
    @Value("${api.common.version}")
    String apiVersion;
    @Value("${api.common.title}")
    private String apiTitle;
    @Value("${api.common.description}")
    String apiDescription;
    @Value("${api.common.termsOfService}")
    String apiTermsOfService;
    @Value("${api.common.license}")
    String apiLicense;
    @Value("${api.common.licenseUrl}")
    String apiLicenseUrl;
    @Value("${api.common.externalDocDesc}")
    String apiExternalDocDesc;
    @Value("${api.common.externalDocUrl}")
    String apiExternalDocUrl;
    @Value("${api.common.contact.name}")
    String apiContactName;
    @Value("${api.common.contact.url}")
    String apiContactUrl;
    @Value("${api.common.contact.email}")
    String apiContactEmail;

    @Bean
    OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title(apiTitle)
                .description(apiDescription)
                        .version(apiVersion)
                        .contact(new Contact()
                                .name(apiContactName)
                                .email(apiContactEmail)
                                .url(apiContactUrl)
                        ));
    };

}
