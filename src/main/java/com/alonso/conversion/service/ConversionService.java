package com.alonso.conversion.service;

import com.alonso.conversion.model.dto.ConversionPurchaseDTO;
import reactor.core.publisher.Mono;

public interface ConversionService {
	Mono<ConversionPurchaseDTO> findById(Long id);
}
