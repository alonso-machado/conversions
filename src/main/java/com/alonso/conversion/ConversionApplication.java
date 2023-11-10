package com.alonso.conversion;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Conversion API", version = "1.0", description = "Documentation Conversion API v1.0"))
public class ConversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConversionApplication.class, args);
	}

}
