package com.alonso.conversion.mappers;


import com.alonso.conversion.model.dto.FiscalDataUpdatedDTO;
import com.alonso.conversion.model.response.FiscalData;

import static com.alonso.conversion.utils.ConverterUtils.convert;

// Mapper to Convert to DTO
// Doing this by hand usually get more Performance / Memory usage then using ModelMapper or MapStruct

public class FiscalDataMapper {

	public static FiscalDataUpdatedDTO toDto(FiscalData fiscalData, Double amount) {
		FiscalDataUpdatedDTO fiscalDataUpdatedDTO = FiscalDataUpdatedDTO.builder()
				.exchange_rate(fiscalData.exchange_rate())
				.country_currency_desc(fiscalData.country_currency_desc())
				.converted_amount(convert(fiscalData.exchange_rate(), amount))
				.build();

		return fiscalDataUpdatedDTO;
	}

}
