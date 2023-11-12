package com.alonso.conversion.unit.repository;

import com.alonso.conversion.model.entity.Purchase;
import com.alonso.conversion.repository.PurchaseRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PurchaseRepositoryUnitTest {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Before
	public void setup(){
		purchaseRepository.deleteAll();
	}

	@Test
	public void whenSave_thenReturnPurchase() {
		//Arrange
		String descriptionTest = "RepositoryTestName";
		Double amount = 15.787543;
		LocalDate purchaseDate = LocalDate.parse("2023-11-08");
		Long idPurchaseTest = 1L;
		Purchase newPurchase = Purchase.builder().id(idPurchaseTest).description(descriptionTest).amount(amount).dateTransaction(purchaseDate).dateCreated(LocalDateTime.now()).build();

		//Act
		Purchase found = purchaseRepository.save(newPurchase);

		//Assert
		assertThat(found.getId()).isEqualTo(newPurchase.getId());
		assertThat(found.getAmount()).isEqualTo(amount);
		assertThat(found.getDateTransaction()).isEqualTo(purchaseDate);
	}

	@Test(expected = ConstraintViolationException.class)
	public void whenSaveNegativeAmountInvalid_thenReturnPurchase() {
		String descriptionTest = "RepositoryTestNegativeAmount";
		Double amount = -5.787543;
		Purchase newPurchase = Purchase.builder().description(descriptionTest).amount(amount).dateTransaction(LocalDate.now()).dateCreated(LocalDateTime.now()).build();

		purchaseRepository.save(newPurchase);
	}


	@Test(expected = ConstraintViolationException.class) // More than 50 Characters on Description should be Invalid
	public void whenSaveDescriptionLongInvalid_thenConstraintViolationException() {
		String descriptionTest = "RepositoryTestDescription1234567890123456789012345678901234567890123456789012345678901234567890";
		Double amount = 67.787543;
		Purchase newPurchase = Purchase.builder().description(descriptionTest).amount(amount).dateTransaction(LocalDate.now()).dateCreated(LocalDateTime.now()).build();

		assertThat(purchaseRepository.save(newPurchase)).isInstanceOf(ConstraintViolationException.class);
	}


	@Test
	public void whenFindById_thenReturnPurchase() {
		//Arrange
		String descriptionTest = "RepositoryTestName";
		Double amount = 10.1678;
		LocalDate purchaseDate = LocalDate.now();
		Long idPurchaseTest = 1L;
		Purchase newPurchase =
				Purchase.builder().id(idPurchaseTest).description(descriptionTest).amount(amount).dateTransaction(purchaseDate).dateCreated(LocalDateTime.now()).dateModified(LocalDateTime.now()).build();
		//Save in the DB
		purchaseRepository.save(newPurchase);

		//Act
		Purchase found = purchaseRepository.findById(newPurchase.getId()).get();

		//Assert
		assertThat(found.getId()).isEqualTo(newPurchase.getId());
	}

	@Test
	public void whenFindByDescriptionLikeIgnoreCase_thenReturnPurchase() {
		Double amount = 15.787543;
		Purchase purchaseTest = Purchase.builder().description("RepositoryTestName2").amount(amount).dateTransaction(LocalDate.now()).dateCreated(LocalDateTime.now()).build();
		purchaseRepository.save(purchaseTest);

		Purchase found = purchaseRepository.findByDescriptionLikeIgnoreCase(purchaseTest.getDescription()).get();

		assertThat(found.getDescription()).isEqualTo(purchaseTest.getDescription());
	}

	@Test
	public void whenFindByDescriptionLikeIgnoreCaseInexistent_thenReturnException() {
		String descriptionTest = "RepositoryTestNameInexistent";

		assertThat(purchaseRepository.findByDescriptionLikeIgnoreCase(descriptionTest)).isNull();
	}

}
