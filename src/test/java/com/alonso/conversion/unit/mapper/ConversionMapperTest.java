package com.alonso.conversion.unit.mapper;

import com.alonso.conversion.exceptions.ConversionNotFoundInPrevious6MonthsException;
import com.alonso.conversion.mappers.ConversionMapper;
import com.alonso.conversion.model.dto.ConversionPurchaseDTO;
import com.alonso.conversion.model.entity.Purchase;
import com.alonso.conversion.model.response.FiscalData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.alonso.conversion.utils.ConverterUtils.convert;

public class ConversionMapperTest {

    private ConversionMapper conversionMapper;

    @BeforeEach
    public void setUp() {
        conversionMapper = new ConversionMapper();
    }

    @Test
    public void whenToDtoWithEmptyFiscalDatas_thenReturnConversionPurchaseDTO() {
        String descriptionTest = "MapperTestName";
        Double amount = 15.50;
        LocalDate purchaseTestDate = LocalDate.parse("2023-11-08");
        Long idPurchaseTest = 1L;
        Purchase newPurchase = Purchase.builder().id(idPurchaseTest).description(descriptionTest).amount(amount).dateTransaction(purchaseTestDate).dateCreated(LocalDateTime.now()).build();

        List<FiscalData> fiscalDatas = new ArrayList<>();
        FiscalData fiscalDataTestMock = new FiscalData("USD", 1.2, purchaseTestDate);
        Double roundedConvertedAmount = convert(fiscalDataTestMock.exchange_rate(), amount);
        fiscalDatas.add(fiscalDataTestMock);

        ConversionPurchaseDTO conversionPurchaseDTO = conversionMapper.toDto(newPurchase, fiscalDatas);

        Assertions.assertEquals(newPurchase.getId(), conversionPurchaseDTO.getId());
        Assertions.assertEquals(newPurchase.getDateTransaction(), conversionPurchaseDTO.getDateTransaction());
        Assertions.assertEquals(newPurchase.getDescription(), conversionPurchaseDTO.getDescription());
        Assertions.assertEquals(newPurchase.getAmount(), conversionPurchaseDTO.getAmount());
        Assertions.assertEquals(1, conversionPurchaseDTO.getConversions().size());
        Assertions.assertEquals(fiscalDataTestMock.exchange_rate(), conversionPurchaseDTO.getConversions().get(0).getExchange_rate());
        Assertions.assertEquals(fiscalDataTestMock.country_currency_desc(), conversionPurchaseDTO.getConversions().get(0).getCountry_currency_desc());
        Assertions.assertEquals(roundedConvertedAmount , conversionPurchaseDTO.getConversions().get(0).getConverted_amount());
    }

    @Test
    public void whenToDtoWithEmptyFiscalDatas_thenReturnException() {
        String descriptionTest = "TestToDtoWithEmptyFiscalDatas";
        Double amount = 15.15;
        LocalDate purchaseTestDate = LocalDate.parse("2022-11-09");
        Long idPurchaseTest = 1L;
        Purchase newPurchase = Purchase.builder().id(idPurchaseTest).description(descriptionTest).amount(amount).dateTransaction(purchaseTestDate).dateCreated(LocalDateTime.now()).build();


        List<FiscalData> fiscalDatas = new ArrayList<>();

        Assertions.assertThrows(ConversionNotFoundInPrevious6MonthsException.class, () -> {
            conversionMapper.toDto(newPurchase, fiscalDatas);
        });
    }
}