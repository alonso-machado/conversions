package com.alonso.conversion.annotation;

import com.alonso.conversion.model.dto.ConversionPurchaseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperation(path = "/api/v1/conversion-functional/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
		beanClass = ConversionPurchaseDTO.class, method = RequestMethod.GET, beanMethod = "findOneFunctional",
		operation = @Operation(operationId = "findOneFunctional", description = "Find a Purchase Transaction with Conversions",
				parameters = {@Parameter(in = ParameterIn.PATH, name = "id")},
				responses = {
						@ApiResponse(responseCode = "200", description = "Successful Operation",
								content = @Content(schema = @Schema(implementation = ConversionPurchaseDTO.class))),
						@ApiResponse(responseCode = "404", description = "Purchase Transaction Not Found")
				}))
public @interface ConversionRouterApiInfo {
}
