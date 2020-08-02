package com.clw.common.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: clw
 * @Description: Swagger2 配置
 * @Date: 2020/7/21 23:21
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.clw"))
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("admin api file")
                .description("api demo")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
