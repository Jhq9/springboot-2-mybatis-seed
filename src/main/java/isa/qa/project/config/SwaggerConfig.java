package isa.qa.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *  Swagger Config
 *
 *  @author    May
 *  @date      2018/11/21 9:13
 *  @version   1.0
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("isa.qa.project"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        //TODO 项目描述替换
        return new ApiInfoBuilder()
                .title("Springboot-2-mybatis-seed Api Document")
                .description("Springboot-2-mybatis-seed Api Document")
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0")
                .build();
    }
}
