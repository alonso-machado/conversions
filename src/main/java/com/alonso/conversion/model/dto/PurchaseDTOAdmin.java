package com.alonso.conversion.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTOAdmin {

	private Long id;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateTransaction;
	private Double amount;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "UTC")
	private LocalDateTime dateCreated;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "UTC")
	private LocalDateTime dateModified;
}
