package com.alonso.conversion.controller;


import com.alonso.conversion.model.dto.ConversionPurchaseDTO;
import com.alonso.conversion.service.ConversionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/conversion")
public class ConversionController {

	private ConversionService conversionService;

	@GetMapping("/{id}")
	@Operation(summary = "Get all Conversions for Purchase with that ID in the Valid Date Range (6 months)")
	public Mono<ConversionPurchaseDTO> getPurchaseConversions(@NotNull @Valid @PathVariable Long id) {
		return conversionService.findById(id);
	}


}
