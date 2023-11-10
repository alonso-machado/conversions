package com.alonso.conversion.mappers;


import com.alonso.conversion.exceptions.ConversionNotFoundInPrevious6MonthsException;
import com.alonso.conversion.model.dto.ConversionPurchaseDTO;;
import com.alonso.conversion.model.response.FiscalData;
import com.alonso.conversion.model.entity.Purchase;

import java.util.List;

// Mapper to Convert to DTO
// Doing this by hand usually get more Performance / Memory usage then using ModelMapper or MapStruct
public class ConversionMapper {

	public static ConversionPurchaseDTO toDto(Purchase purchase, List<FiscalData> fiscalDatas) {
		ConversionPurchaseDTO conversionPurchaseDTO = new ConversionPurchaseDTO();
		conversionPurchaseDTO.setId(purchase.getId());
		conversionPurchaseDTO.setDateTransaction(purchase.getDateTransaction());
		conversionPurchaseDTO.setDescription(purchase.getDescription());
		conversionPurchaseDTO.setAmount(purchase.getAmount());
		if(fiscalDatas.size() > 0) {
			conversionPurchaseDTO.setConversions(fiscalDatas.stream().map(fd -> FiscalDataMapper.toDto(fd, purchase.getAmount())).toList());
		} else {
			throw new ConversionNotFoundInPrevious6MonthsException();
		}

		return conversionPurchaseDTO;
	}

}
