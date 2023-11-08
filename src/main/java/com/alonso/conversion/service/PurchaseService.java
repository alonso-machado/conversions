package com.alonso.conversion.service;

import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.model.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Map;

public interface PurchaseService {
	Page<PurchaseDTO> findAll(Pageable pageable);

	PurchaseDTO findOne(Long id);

	PurchaseDTO addPurchase(String description, Double amount, LocalDate purchaseDate);

	PurchaseDTO update(Long id, Map<String, Object> updatingFields);

	void delete(Long id);

	PurchaseDTO findByDescriptionLikeIgnoreCase(String descriptionTest);
}
