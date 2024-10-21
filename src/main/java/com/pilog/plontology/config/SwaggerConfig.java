package com.pilog.plontology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ListVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket api() {
        Map<String, Object> sapIntegrationMap = new LinkedHashMap<>();
        Map<String, Map<String, Object>> templatesMap = new LinkedHashMap<>();
        Map<String, Object> hostTemplate = new LinkedHashMap<>();
        hostTemplate.put("default", "");
        hostTemplate.put("description", "hostname of the end point");
        templatesMap.put("host", hostTemplate);

        Map<String, Object> regionTemplate = new HashMap<>();
        regionTemplate.put("default", "");
        regionTemplate.put("description", "The SAP Ariba region where the SAP Ariba APIs are used.");
        templatesMap.put("region", regionTemplate);

        Map<String, Object> basepathTemplate = new HashMap<>();
        basepathTemplate.put("default", "");
        basepathTemplate.put("description", "Basepath of the API endpoint");
        templatesMap.put("basepath", basepathTemplate);

        sapIntegrationMap.put("url", "https://{host}.{region}.hana.ondemand.com/{basepath}");
        sapIntegrationMap.put("description", "The integration of PiLog Preferred Records (PPR) with SAP S/4HANA Cloud brings Standardized Material and Service Master Records into your organization");
        sapIntegrationMap.put("templates", templatesMap);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pilog.plontology"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData())
    //            .securitySchemes(Arrays.asList(apiKey()))
                .extensions(Arrays.asList(
                new ListVendorExtension<>("x-sap-shortText", Arrays.asList("Uniquely defined pre-catalogued and standardized Material, Service Master Records.")),
                new ListVendorExtension<>("x-servers", Arrays.asList(sapIntegrationMap))
        ))
                ;

    }

    private ApiInfo metaData() {

        Contact contact = new Contact("Pilog Group", "https://www.piloggroup.com",
                " supportsap@piloggroup.com");

        return new ApiInfo("Pilog Group",
                "Restful APIs for Pilog Webservices",
                "1.0",
                "Terms of Service: Need to Update",
                contact,
                "Copyright © 2024 PiLog Group",
                "Pilog Repository",
                new ArrayList<>());
    }

    private ApiKey apiKey() {
        return new ApiKey("Api-Key", "API Key", "header");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}


