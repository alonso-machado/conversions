package com.alonso.conversion.unit.utils;

import com.alonso.conversion.model.response.FiscalData;
import com.alonso.conversion.utils.FiscalDataUtils;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FiscalDataUtilsTest {

	@Test
	void testUniqueFiscalDataList() {
		List<FiscalData> fiscalDataList = new ArrayList<>();
		fiscalDataList.add(new FiscalData("USD", 38.0 ,LocalDate.of(2022, 1, 10)));
		fiscalDataList.add(new FiscalData("EUR", 39.50 ,LocalDate.of(2022, 1, 10)));
		fiscalDataList.add(new FiscalData("USD", 35.0 ,LocalDate.of(2023, 5, 11)));
		fiscalDataList.add(new FiscalData("EUR", 40.0 ,LocalDate.of(2023, 4, 22)));
		List<FiscalData> expectedUniqueFiscalData = new ArrayList<>();
		expectedUniqueFiscalData.add(new FiscalData("EUR", 40.0 ,LocalDate.of(2023, 4, 22)));
		expectedUniqueFiscalData.add(new FiscalData("USD", 35.0 ,LocalDate.of(2023, 5, 11)));
		List<FiscalData> actualUniqueFiscalData = FiscalDataUtils.UniqueFiscalDataList(fiscalDataList);
		assertEquals(expectedUniqueFiscalData, actualUniqueFiscalData);
	}
}
