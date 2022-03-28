package com.controller.farmerservice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableSwagger2
public class FarmerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmerServiceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getTemplate() 
	{
		return new RestTemplate();
	}
	
	private ApiKey apiKey() 
	{
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext() 
	{
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() 
	{
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	@Bean
	public Docket api() 
	{
		return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(apiInfo())
		.securityContexts(Arrays.asList(securityContext()))
		.securitySchemes(Arrays.asList(apiKey()))
		.select()
		.apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.any())
		.build();
	}

	private ApiInfo apiInfo() 
	{
		return new ApiInfo(
		"Dealer Service REST API",
		"This Service can be used by only authorized users",
		"1.0",
		"Terms of service",
		new Contact("Subham Satpathy", "subhamssatpathy@gmail.com", "SDE - I"),
		"License of API",
		"API license URL",
		Collections.emptyList());
	}
}
