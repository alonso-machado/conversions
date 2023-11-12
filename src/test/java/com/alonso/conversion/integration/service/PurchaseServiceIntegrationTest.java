package com.alonso.conversion.integration.service;

import com.alonso.conversion.exceptions.ConversionNotFoundInPrevious6MonthsException;
import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.repository.PurchaseRepository;
import com.alonso.conversion.service.PurchaseService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@Profile("test")
@SpringBootTest
class PurchaseServiceIntegrationTest {

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private PurchaseRepository purchaseRepository;


	@BeforeEach
	public void setup() {
		purchaseRepository.deleteAll();
	}

	@Test
	void whenSave_thenReturnPurchase() {
		//Arrange
		String descriptionTest = "ServiceSaveTestName";
		Double amount = 12.787543;
		Double amountRounded = 12.79;
		LocalDate purchaseDate = LocalDate.parse("2023-11-08");
		Long idPurchaseTest = 1L;
		PurchaseDTO expectedPurchase = new PurchaseDTO().builder().id(idPurchaseTest).description(descriptionTest).amount(amountRounded).dateTransaction(purchaseDate).build();

		//Act
		PurchaseDTO found = purchaseService.addPurchase(descriptionTest, amount, purchaseDate);

		//Assert
		assertThat(found.getId()).isEqualTo(expectedPurchase.getId());
		assertThat(found.getAmount()).isEqualTo(expectedPurchase.getAmount()); //Using the ROUNDED value
		assertThat(found.getDateTransaction()).isEqualTo(expectedPurchase.getDateTransaction());
	}

	@Test
	void whenSaveNegativeAmountInvalid_thenReturnConstraintViolationException() {
		String descriptionTest = "ServiceTestNegativeAmount";
		Double amount = -5.787543;
		LocalDate purchaseDate = LocalDate.now();

		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			purchaseService.addPurchase(descriptionTest, amount, purchaseDate);
		});
	}

	@Test // More than 50 Characters on Description should be Invalid
	void whenSaveDescriptionLongInvalid_thenReturnConstraintViolationException() {
		String descriptionTest = "ServiceTestDescription1234567890123456789012345678901234567890123456789012345678901234567890";
		Double amount = 67.787543;
		LocalDate purchaseDate = LocalDate.now();

		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			purchaseService.addPurchase(descriptionTest, amount, purchaseDate);
		});

	}

	@Test
	void whenFindById_thenReturnPurchaseDTO() {
		//Arrange
		String descriptionTest = "ServiceTestName678";
		Double amount = 10.1678;
		LocalDate purchaseDate = LocalDate.now();
		//Save in the DB
		PurchaseDTO saved = purchaseService.addPurchase(descriptionTest, amount, purchaseDate);

		//Act
		PurchaseDTO found = purchaseService.findById(saved.getId());

		//Assert
		assertThat(found.getDescription()).isEqualTo(descriptionTest);
	}

	@Test
	void whenFindByDescriptionLikeIgnoreCase_thenReturnPurchase() {
		//Arrange
		String descriptionTest = "PurchaseServiceTestLike";
		Double amount = 1863.96724;
		Double amountRounded = 1863.97;
		LocalDate purchaseDateTest = LocalDate.now();

		//Act
		purchaseService.addPurchase(descriptionTest, amount, purchaseDateTest);
		PurchaseDTO found = purchaseService.findByDescriptionLikeIgnoreCase(descriptionTest);

		assertThat(found.getDescription()).isEqualTo(descriptionTest);
		assertThat(found.getAmount()).isEqualTo(amountRounded);
		assertThat(found.getDateTransaction()).isEqualTo(purchaseDateTest);
	}

	@Test
	void whenFindByDescriptionLikeIgnoreCaseInexistent_thenReturnException() {
		String descriptionTest = "PurchaseServiceTestNameInexistent";

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			purchaseService.findByDescriptionLikeIgnoreCase(descriptionTest);
		});
	}

}

