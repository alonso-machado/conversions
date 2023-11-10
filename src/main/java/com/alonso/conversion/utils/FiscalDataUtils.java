package com.alonso.conversion.utils;

import com.alonso.conversion.model.response.Fiscal;
import com.alonso.conversion.model.response.FiscalData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FiscalDataUtils {
	public static List<FiscalData> UniqueFiscalDataList(List<FiscalData> fd) {
		Map<String, List<FiscalData>> currencyGroup = fd.stream()
				.collect(Collectors.groupingBy(FiscalData::country_currency_desc));

		List<FiscalData> uniqueFiscalData = new ArrayList<>();
		currencyGroup.forEach((key, value) -> {
			LocalDate maxDate = value.stream()
					.map(FiscalData::record_date)
					.max(LocalDate::compareTo).get();

			List<FiscalData> filteredData = value.stream()
					.filter(f -> !f.record_date().isBefore(maxDate))
					.collect(Collectors.toList());
			uniqueFiscalData.addAll(filteredData);
		});

		return uniqueFiscalData.stream().sorted(fiscalDataCountryNameComparator).collect(Collectors.toList());
	}
	public static Comparator<FiscalData> fiscalDataCountryNameComparator = Comparator.comparing(FiscalData::country_currency_desc);

}
