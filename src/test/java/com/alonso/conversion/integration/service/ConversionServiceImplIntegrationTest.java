package com.alonso.conversion.integration.service;

import com.alonso.conversion.model.dto.ConversionPurchaseDTO;
import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.model.entity.Purchase;
import com.alonso.conversion.service.ConversionService;
import com.alonso.conversion.service.ConversionServiceImpl;
import com.alonso.conversion.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
class ConversionServiceImplIntegrationTest {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private PurchaseService purchaseService;

    @Test
    void whenFindById_thenTestFetchDataConversionFromFiscalAPI() {
        // Arrange
        PurchaseDTO savedPurchase = purchaseService.addPurchase("descriptionTest", 5.0, LocalDate.now());

        // Act
        Mono<ConversionPurchaseDTO> result = conversionService.findById(savedPurchase.getId());

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(dto -> dto.getId().equals(savedPurchase.getId()))
                .verifyComplete();
    }

}