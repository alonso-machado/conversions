package com.alonso.conversion.integration.service;

import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.repository.PurchaseRepository;
import com.alonso.conversion.service.PurchaseService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/*
@Profile("prod")
@SpringBootTest
public class PurchaseServiceIntegrationTest {

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private PurchaseRepository purchaseRepository;


	@BeforeEach
	public void setup() {
		purchaseRepository.deleteAll();
	}

	@Test
	public void whenSave_thenReturnPurchase() {
		//Arrange
		String descriptionTest = "RepositoryTestName";
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
	public void whenSaveNegativeAmountInvalid_thenReturnPurchase() {
		String descriptionTest = "RepositoryTestNegativeAmount";
		Double amount = -5.787543;

		assertThat(purchaseService.addPurchase(descriptionTest, amount, LocalDate.now())).isInstanceOfAny(Exception.class);
	}

	@Test // More than 50 Characters on Description should be Invalid
	public void whenSaveDescriptionLongInvalid_thenConstraintViolationException() {
		String descriptionTest = "RepositoryTestDescription1234567890123456789012345678901234567890123456789012345678901234567890";
		Double amount = 67.787543;

		assertThat(purchaseService.addPurchase(descriptionTest, amount, LocalDate.now())).isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	public void whenFindById_thenReturnPurchaseDTO() {
		//Arrange
		String descriptionTest = "RepositoryTestName678";
		Double amount = 10.1678;
		LocalDate purchaseDate = LocalDate.now();
		Long idPurchaseTest = 1L;
		//Save in the DB
		purchaseService.addPurchase(descriptionTest, amount, purchaseDate);

		//Act
		PurchaseDTO found = purchaseService.findById(idPurchaseTest);

		//Assert
		assertThat(found.getDescription()).isEqualTo(descriptionTest);
	}

	@Test
	public void whenFindByDescriptionLikeIgnoreCase_thenReturnPurchase() {
		//Arrange
		String descriptionTest = "RepositoryTestLike";
		Double amount = 1863.96724;

		//Act
		purchaseService.addPurchase(descriptionTest, amount, LocalDate.now());
		PurchaseDTO found = purchaseService.findByDescriptionLikeIgnoreCase(descriptionTest);

		assertThat(found.getDescription()).isEqualTo(descriptionTest);
		assertThat(found.getAmount()).isEqualTo(amount);
	}

	@Test
	public void whenFindByDescriptionLikeIgnoreCaseInexistent_thenReturnException() {
		String descriptionTest = "RepositoryTestNameInexistent";

		assertThat(purchaseService.findByDescriptionLikeIgnoreCase(descriptionTest)).isNull();
	}

}
*/
