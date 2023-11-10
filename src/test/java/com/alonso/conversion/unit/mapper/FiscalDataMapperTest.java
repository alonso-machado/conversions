package com.alonso.conversion.unit.mapper;

import com.alonso.conversion.mappers.FiscalDataMapper;
import com.alonso.conversion.model.dto.FiscalDataUpdatedDTO;
import com.alonso.conversion.model.response.FiscalData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.alonso.conversion.utils.ConverterUtils.convert;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiscalDataMapperTest {

    @Test
    void whenToDto_thenReturnAmountNotRounding() {
        // Arrange
        Double amount = 90.0;
        FiscalData fiscalData = new FiscalData("USD", 2.0, LocalDate.now());

        // Act
        FiscalDataUpdatedDTO result = FiscalDataMapper.toDto(fiscalData, amount);

        // Assert
        assertEquals(fiscalData.exchange_rate(), result.getExchange_rate());
        assertEquals(fiscalData.country_currency_desc(), result.getCountry_currency_desc());
        assertEquals(fiscalData.exchange_rate() * amount, result.getConverted_amount());
    }
    @Test
    void whenToDto_thenReturnAmountRounded() {
        // Arrange
        Double amount = 91.39;
        FiscalData fiscalData = new FiscalData("USD", 2.72, LocalDate.now());
        Double roundedConvertedAmount = convert(fiscalData.exchange_rate(), amount);

        // Act
        FiscalDataUpdatedDTO result = FiscalDataMapper.toDto(fiscalData, amount);


        // Assert
        assertEquals(fiscalData.exchange_rate(), result.getExchange_rate());
        assertEquals(fiscalData.country_currency_desc(), result.getCountry_currency_desc());
        assertEquals(roundedConvertedAmount, result.getConverted_amount());
    }
}