package com.alonso.conversion.unit.utils;

import com.alonso.conversion.utils.ConverterUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConverterUtilsTest {

    @Test
    public void testConvert() {
        Double exchangeRate = 1.5;
        Double amountInDollars = 10.0;
        Double expected = 15.0;

        Double result = ConverterUtils.convert(exchangeRate, amountInDollars);

        assertEquals(expected, result);
    }

    @Test
    public void testRound() {
        Double amount = 15.123;
        Double expected = 15.12;

        Double result = ConverterUtils.round(amount);

        assertEquals(expected, result);
    }
}