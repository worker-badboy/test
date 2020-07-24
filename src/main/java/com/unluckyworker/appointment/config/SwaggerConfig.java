package com.unluckyworker.appointment.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootConfiguration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo()).groupName("unluckyworker").enable(true).select().apis(RequestHandlerSelectors.basePackage("com.unluckyworker.appointment.controller")).build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("unluckyworker", "http://183.62.25.243:9080.com", "");
        return new ApiInfo("unluckyworker的swaggerAPI文档", "这两个后端有点菜", "1.0", "http://183.62.25.243:9080.com", contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }
}
