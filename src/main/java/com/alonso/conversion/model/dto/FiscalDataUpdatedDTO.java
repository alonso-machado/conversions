package com.alonso.conversion.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiscalDataUpdatedDTO {

	private String country_currency_desc;
	private Double exchange_rate;
	private Double converted_amount;

}
