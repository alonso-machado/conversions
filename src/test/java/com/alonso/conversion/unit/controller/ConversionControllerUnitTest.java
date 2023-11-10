package com.alonso.conversion.unit.controller;

import com.alonso.conversion.controller.ConversionController;
import com.alonso.conversion.mappers.ConversionMapper;
import com.alonso.conversion.model.dto.ConversionPurchaseDTO;
import com.alonso.conversion.model.entity.Purchase;
import com.alonso.conversion.model.response.FiscalData;
import com.alonso.conversion.service.ConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConversionControllerUnitTest {

	private ConversionController conversionController;
	private ConversionService conversionService;

	@BeforeEach
	public void setup() {
		conversionService = mock(ConversionService.class);
		conversionController = new ConversionController(conversionService);
	}

	@Test
	public void whenGetById_thenReturnConversion() {
		// Arrange
		long purchaseId = 1L;
		String descriptionTest = "ConversionControllerTestName";
		Double amount = 15.50;
		LocalDate purchaseDate = LocalDate.parse("2019-07-07");
		Purchase newPurchase = Purchase.builder().id(purchaseId).description(descriptionTest).amount(amount).dateTransaction(purchaseDate).dateCreated(LocalDateTime.now()).build();

		List<FiscalData> fiscalDataMock = new ArrayList<>();
		FiscalData fiscalDataTestMock = new FiscalData("USD", 1.2, purchaseDate);
		fiscalDataMock.add(fiscalDataTestMock);
		ConversionPurchaseDTO expectedConversionPurchaseDTO = ConversionMapper.toDto(newPurchase, fiscalDataMock);

		//Mock
		when(conversionService.findById(purchaseId)).thenReturn(Mono.just(expectedConversionPurchaseDTO));

		// Act
		Mono<ConversionPurchaseDTO> response = conversionController.getPurchaseConversions(purchaseId);

		// Assert
		assertEquals(expectedConversionPurchaseDTO, response.block());
	}

}
