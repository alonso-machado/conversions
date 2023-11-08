package com.alonso.conversion.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

	private Long id;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd") // ISO8601 - US / International Standard
	//@JsonFormat(pattern = "dd/MM/yyyy") // Brazil and Most Western Countries Standard
	private LocalDate dateTransaction;
	private Double amount;
}
