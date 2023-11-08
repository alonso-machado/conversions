package com.alonso.conversion.unit.controller;

import com.alonso.conversion.controller.PurchaseController;
import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PurchaseControllerUnitTest {

    private PurchaseController purchaseController;
    private PurchaseService purchaseService;

    @BeforeEach
    public void setup() {
        purchaseService = mock(PurchaseService.class);
        purchaseController = new PurchaseController(purchaseService);
    }

    @Test
    public void testGetAll() {
        // Arrange
        Pageable pageable = Pageable.ofSize(20);
        Page<PurchaseDTO> expectedPage = new PageImpl<>(Collections.emptyList(), pageable, 0);
        when(purchaseService.findAll(pageable)).thenReturn(expectedPage);

        // Act
        ResponseEntity<Page<PurchaseDTO>> response = purchaseController.getAll(pageable);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPage, response.getBody());
    }

    @Test
    public void testGetById() {
        // Arrange
        long purchaseId = 1L;
        PurchaseDTO expectedPurchase = new PurchaseDTO().builder().id(purchaseId).description("Product 1").amount(10.0).build();
        when(purchaseService.findOne(purchaseId)).thenReturn(expectedPurchase);

        // Act
        ResponseEntity<PurchaseDTO> response = purchaseController.getPurchase(purchaseId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPurchase, response.getBody());
    }

    @Test
    public void testAddPurchase() {
        // Arrange
        String descriptionTest = "Test Add Purchase";
        Double amount = 12.787543;
        Double amountRounded = 12.79;
        LocalDate purchaseDate = LocalDate.now();
        Long idPurchaseTest = 1L;
        PurchaseDTO expectedPurchase = PurchaseDTO.builder().id(idPurchaseTest).description(descriptionTest).amount(amountRounded).dateTransaction(purchaseDate).build();
        
        when(purchaseService.addPurchase(descriptionTest, amount, purchaseDate)).thenReturn(expectedPurchase);

        // Act
        ResponseEntity<PurchaseDTO> response = purchaseController.addPurchase(descriptionTest, amount, purchaseDate);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPurchase, response.getBody());
        assertEquals(response.getBody().getAmount(), amountRounded);
    }

}
