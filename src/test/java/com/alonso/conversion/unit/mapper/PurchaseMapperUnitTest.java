package com.alonso.conversion.unit.mapper;

import com.alonso.conversion.mappers.PurchaseMapper;
import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.model.dto.PurchaseDTOAdmin;
import com.alonso.conversion.model.entity.Purchase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class PurchaseMapperUnitTest {


	@Test
	void whenConvertDTOToEntity_thenReturnEntity() {
		//Arrange
		String descriptionTest = "MapperDTOtoEntity";
		Double amountRounded = 20.0;
		LocalDate purchaseDate = LocalDate.parse("2023-11-08");
		Long idPurchaseTest = 1L;

		Purchase expectedPurchase =
				Purchase.builder().id(idPurchaseTest).description(descriptionTest).amount(amountRounded).dateTransaction(purchaseDate).dateCreated(LocalDateTime.now()).dateModified(LocalDateTime.now()).build();
		PurchaseDTO purchaseDTO = PurchaseDTO.builder().id(idPurchaseTest).description(descriptionTest).amount(amountRounded).dateTransaction(purchaseDate).build();

		//Act
		Purchase returned = PurchaseMapper.toEntity(purchaseDTO);

		//Assert
		assertAll("Grouped Assertions of Purchase",
				() -> assertEquals(returned.getId(), expectedPurchase.getId()),
				() -> assertEquals(returned.getDescription(), expectedPurchase.getDescription()),
				() -> assertEquals(returned.getAmount(), expectedPurchase.getAmount()),
				() -> assertEquals(returned.getDateTransaction(), expectedPurchase.getDateTransaction())
		);

	}

	@Test
	void whenConvertEntityToDTO_thenReturnDTO() {
		//Arrange
		String descriptionTest = "MapperEntitytoDTO";
		Double amountRounded = 14.52;
		LocalDate purchaseDate = LocalDate.now();
		Long idPurchaseTest = 1L;

		Purchase purchase = Purchase.builder().id(idPurchaseTest).description(descriptionTest).amount(amountRounded).dateTransaction(purchaseDate).dateCreated(LocalDateTime.now()).dateModified(LocalDateTime.now()).build();
		PurchaseDTO expectedPurchaseDTO = PurchaseDTO.builder().id(idPurchaseTest).description(descriptionTest).amount(amountRounded).dateTransaction(purchaseDate).build();

		//Act
		PurchaseDTO returned = PurchaseMapper.toDto(purchase);

		//Assert
		assertAll("Grouped Assertions of PurchaseDTO",
				() -> assertEquals(returned.getId(), expectedPurchaseDTO.getId()),
				() -> assertEquals(returned.getDescription(), expectedPurchaseDTO.getDescription()),
				() -> assertEquals(returned.getAmount(), expectedPurchaseDTO.getAmount()),
				() -> assertEquals(returned.getDateTransaction(), expectedPurchaseDTO.getDateTransaction())
		);
	}


	@Test
	void whenConvertEntityToDTOAdmin_thenReturnDTOAdmin() {
		//Arrange
		String descriptionTest = "MapperEntitytoDTOAdmin";
		Double amount = 21.9965654;
		LocalDate purchaseDate = LocalDate.parse("2023-11-09");
		LocalDateTime fakeDateTime = LocalDate.parse("2023-11-10").atStartOfDay();
		Long idPurchaseTest = 2L;

		Purchase purchase = Purchase.builder().id(idPurchaseTest).description(descriptionTest).amount(amount).dateTransaction(purchaseDate).dateCreated(fakeDateTime).dateModified(fakeDateTime).build();
		PurchaseDTOAdmin expectedPurchaseDTOAdmin =
				PurchaseDTOAdmin.AdminBuilder().id(idPurchaseTest).description(descriptionTest).amount(amount).dateTransaction(purchaseDate).dateCreated(fakeDateTime).dateModified(fakeDateTime).build();
		//Act
		PurchaseDTOAdmin returned = PurchaseMapper.toAdminDto(purchase);

		//Assert
		assertAll("Grouped Assertions of PurchaseDTOAdmin",
				() -> assertEquals(returned.getId(), expectedPurchaseDTOAdmin.getId()),
				() -> assertEquals(returned.getDescription(), expectedPurchaseDTOAdmin.getDescription()),
				() -> assertEquals(returned.getAmount(), expectedPurchaseDTOAdmin.getAmount()),
				() -> assertEquals(returned.getDateTransaction(), expectedPurchaseDTOAdmin.getDateTransaction()),
				() -> assertEquals(returned.getDateCreated(), expectedPurchaseDTOAdmin.getDateCreated()),
				() -> assertEquals(returned.getDateModified(), expectedPurchaseDTOAdmin.getDateModified())
		);

	}
}
