package com.alonso.conversion.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FiscalDataUpdatedDTO {

	private String country_currency_desc;
	private Double exchange_rate;
	private Double converted_amount;
}
