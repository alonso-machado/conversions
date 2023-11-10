package com.alonso.conversion.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ConversionPurchaseDTO extends PurchaseDTO {

	List<FiscalDataUpdatedDTO> conversions;
}
