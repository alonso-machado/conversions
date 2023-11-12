package com.alonso.conversion.controller;

import com.alonso.conversion.handler.ConversionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

// This class name could be also ConversionFunctionalController
@Configuration
public class ConversionRouter {

	private ConversionHandler conversionHandler;

	//@ConversionRouterApiInfo
	// SpringDoc Annotation is breaking the SpringDoc/Swagger at v3-docs with REST and Router endpoints, so it is disabled

	@Bean
	@CrossOrigin
	RouterFunction<ServerResponse> ConversionRouter(ConversionHandler conversionHandler) {
		return RouterFunctions.route(RequestPredicates.GET("/api/v1/conversion-functional/{id}"), conversionHandler::findOneFunctional);

	}
}