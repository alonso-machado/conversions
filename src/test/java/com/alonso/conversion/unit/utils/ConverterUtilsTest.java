package com.alonso.conversion.unit.utils;

import com.alonso.conversion.utils.ConverterUtils;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class ConverterUtilsTest {

    @Test
    void testConvert() {
        Double exchangeRate = 1.5;
        Double amountInDollars = 10.0;
        Double expected = 15.0;

        Double result = ConverterUtils.convert(exchangeRate, amountInDollars);

        assertEquals(expected, result);
    }

    @Test
    void testRound() {
        Double amount = 15.123;
        Double expected = 15.12;

        Double result = ConverterUtils.round(amount);

        assertEquals(expected, result);
    }
}