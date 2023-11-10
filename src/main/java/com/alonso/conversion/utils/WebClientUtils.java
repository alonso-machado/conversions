package com.alonso.conversion.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class WebClientUtils {

	private static String FISCAL_API_URL = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?fields=country_currency_desc,exchange_rate,record_date";

	public static String filterPreviousSixMonths(LocalDate dateTransaction) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		StringBuilder sb = new StringBuilder();
		sb.append(FISCAL_API_URL);
		sb.append("&filter=effective_date:lte:");
		sb.append(dateTransaction.format(formatter));
		sb.append(",effective_date:gte:");
		sb.append(dateTransaction.minusMonths(6).format(formatter));
		return sb.toString();
	}

}
