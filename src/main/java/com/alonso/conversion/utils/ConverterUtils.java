package com.alonso.conversion.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConverterUtils {


	public static Double convert(Double exchangeRate, Double amountInDolars) {
		return round(exchangeRate * amountInDolars);
	}

	public static Double round(Double amount) {
		return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
