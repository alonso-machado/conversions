package com.alonso.conversion.unit.service;

import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.model.entity.Purchase;
import com.alonso.conversion.repository.PurchaseRepository;
import com.alonso.conversion.service.PurchaseService;
import com.alonso.conversion.service.PurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseServiceImplTest {

    private PurchaseService purchaseService;

    @Mock
    private PurchaseRepository purchaseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        purchaseService = new PurchaseServiceImpl(purchaseRepository);
    }

    @Test
    void whenFindById_ShouldReturnPurchase() {
        // Arrange
        Long purchaseId = 1L;
        Purchase expectedPurchase = Purchase.builder().id(purchaseId).description("Test Purchase").amount(10.0).dateTransaction(LocalDate.now()).dateCreated(LocalDateTime.now()).build();
        PurchaseDTO expectedPurchaseDTO = PurchaseDTO.builder().id(purchaseId).description("Test Purchase").amount(10.0).dateTransaction(LocalDate.now()).build();
        when(purchaseRepository.findById(purchaseId)).thenReturn(Optional.of(expectedPurchase));

        // Act
        PurchaseDTO result = purchaseService.findById(purchaseId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPurchaseDTO.getAmount(), result.getAmount());
        assertEquals(expectedPurchaseDTO.getDescription(), result.getDescription());
        assertEquals(expectedPurchaseDTO.getDateTransaction(), result.getDateTransaction());
        verify(purchaseRepository, times(1)).findById(purchaseId);
    }

    @Test
    void whenFindById_ShouldThrowException_WhenPurchaseNotFound() {
        // Arrange
        Long purchaseId = 1L;
        when(purchaseRepository.findById(purchaseId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> purchaseService.findById(purchaseId));
        verify(purchaseRepository, times(1)).findById(purchaseId);
    }

    @Test
    void whenAddPurchase_ShouldReturnPurchaseDTO() {
        // Arrange
        String description = "Test Purchase";
        Double amount = 10.0;
        LocalDate purchaseDate = LocalDate.now();
        Purchase newPurchase = Purchase.builder().description(description).amount(amount).dateTransaction(purchaseDate).build();
        PurchaseDTO expectedPurchaseDTO = PurchaseDTO.builder().description(description).amount(amount).dateTransaction(purchaseDate).build();

        // Mock the repository method
        when(purchaseRepository.save(any(Purchase.class))).thenReturn(newPurchase);

        // Act
        PurchaseDTO result = purchaseService.addPurchase(description, amount, purchaseDate);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPurchaseDTO.getAmount(), result.getAmount());
        assertEquals(expectedPurchaseDTO.getDescription(), result.getDescription());
        assertEquals(expectedPurchaseDTO.getDateTransaction(), result.getDateTransaction());
        verify(purchaseRepository, times(1)).save(any(Purchase.class));
    }

    @Test
    void whenUpdate_ShouldReturnUpdatedPurchaseDTO() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        Long purchaseId = 1L;
        Map<String, Object> updatingFields = new HashMap<>();
        updatingFields.put("description", "Updated Purchase");
        updatingFields.put("amount", 20.0);
        updatingFields.put("dateTransaction", LocalDate.of(2022, 1, 1));

        Purchase existingPurchase = Purchase.builder().id(purchaseId).description("Test Purchase").amount(10.0).dateTransaction(LocalDate.now()).build();

        Purchase updatedPurchase = Purchase.builder().id(purchaseId).description("Updated Purchase").amount(20.0).dateTransaction(LocalDate.of(2022, 1, 1)).build();

        PurchaseDTO expectedPurchaseDTO = PurchaseDTO.builder().id(purchaseId).description("Updated Purchase").amount(20.0).dateTransaction(LocalDate.of(2022, 1, 1)).build();

        // Mock the repository method
        when(purchaseRepository.findById(purchaseId)).thenReturn(Optional.of(existingPurchase));
        when(purchaseRepository.save(any(Purchase.class))).thenReturn(updatedPurchase);

        // Act
        PurchaseDTO result = purchaseService.update(purchaseId, updatingFields);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPurchaseDTO.getAmount(), result.getAmount());
        assertEquals(expectedPurchaseDTO.getDescription(), result.getDescription());
        assertEquals(expectedPurchaseDTO.getDateTransaction(), result.getDateTransaction());
        verify(purchaseRepository, times(1)).findById(purchaseId);
        verify(purchaseRepository, times(1)).save(any(Purchase.class));
    }

    @Test
    void whenDelete_ShouldDeletePurchase() {
        // Arrange
        Long purchaseId = 1L;

        Purchase existingPurchase = Purchase.builder().id(purchaseId).description("Test Purchase").amount(10.0).dateTransaction(LocalDate.now()).build();

        when(purchaseRepository.findById(purchaseId)).thenReturn(Optional.of(existingPurchase));

        // Act
        purchaseService.delete(purchaseId);

        // Assert
        verify(purchaseRepository, times(1)).findById(purchaseId);
        verify(purchaseRepository, times(1)).delete(existingPurchase);
    }

}