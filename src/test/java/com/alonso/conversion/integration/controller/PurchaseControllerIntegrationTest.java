package com.alonso.conversion.integration.controller;

import com.alonso.conversion.model.dto.ConversionPurchaseDTO;
import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureWebClient
class PurchaseControllerIntegrationTest {

	@Autowired
	private WebClient webClient;

	@Autowired
	PurchaseService purchaseService;

	@Test
	void testGetPurchaseConversions() throws Exception {
		String purchaseDescription = "IntegrationFull";
		PurchaseDTO savedPurchase = purchaseService.addPurchase(purchaseDescription, 5.0, LocalDate.now());

		webClient.get().uri("/api/v1/transaction{id}", savedPurchase.getId()).accept(MediaType.APPLICATION_JSON)
				.exchangeToMono(response -> response.bodyToMono(ConversionPurchaseDTO.class))
				.as(StepVerifier::create)
				.expectNextMatches(dto -> dto.getId().equals(savedPurchase.getId()))
				.expectNextMatches(dto -> dto.getDescription().equals(purchaseDescription))
				.expectComplete();
	}
}