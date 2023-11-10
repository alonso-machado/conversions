package com.alonso.conversion.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTOAdmin extends PurchaseDTO{
	@Builder(builderMethodName = "AdminBuilder")
	public PurchaseDTOAdmin(Long id, String description, LocalDate dateTransaction, Double amount, LocalDateTime dateCreated, LocalDateTime dateModified) {
		super(id, description, dateTransaction, amount);
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "UTC")
	private LocalDateTime dateCreated;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "UTC")
	private LocalDateTime dateModified;
}
