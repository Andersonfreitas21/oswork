package com.algawork.oswork.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {

	public static final List<String> PATHS = Arrays.asList("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html",
			"/webjars/**");

	@Bean
	public Docket swaggerConfigurationsCustom() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("OSWorks").description("API Rest Ordem de Serviço.")
				.license("Apache Licence Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.version("0.0.1-SNAPSHOT").build();
	}

}
