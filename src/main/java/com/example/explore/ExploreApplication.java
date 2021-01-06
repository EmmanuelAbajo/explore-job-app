package com.example.explore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import static springfox.documentation.builders.PathSelectors.any;

@SpringBootApplication
//@EnableSwagger2
public class ExploreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExploreApplication.class, args);
	}
//
//	@Bean
//	public Docket docket() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.example.explore")).paths(any()).build()
//				.apiInfo(new ApiInfo("Job Scope Exploration API", "API's for the Exploration of Job Scopes Service",
//						"2.0", null, new Contact("Emmanuel Abajo", "temiabajo@gmail.com", ""), null,
//						null, new ArrayList<>()));
//	}

}
