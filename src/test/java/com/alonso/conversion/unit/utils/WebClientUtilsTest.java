package com.alonso.conversion.unit.utils;

import com.alonso.conversion.utils.WebClientUtils;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WebClientUtilsTest {

	@Test
	void testFilterPreviousSixMonths() {
		LocalDate dateTransaction = LocalDate.of(2022, 1, 1);
		String expectedFilter = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?fields=country_currency_desc,exchange_rate,record_date&filter=effective_date:lte:2022-01-01,effective_date:gte:2021-07-01";
		String actualFilter = WebClientUtils.filterPreviousSixMonths(dateTransaction);
		assertEquals(expectedFilter, actualFilter);
	}
}
