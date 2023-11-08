package com.alonso.conversion.service;

import com.alonso.conversion.mappers.PurchaseMapper;
import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.model.entity.Purchase;
import com.alonso.conversion.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

	private PurchaseRepository purchaseRepository;

	@Override
	public Page<PurchaseDTO> findAll(Pageable pageable) {
		if(pageable == null){
			pageable = PageRequest.of(0, 20, Sort.Direction.ASC, "id");
		} else {
			pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
		}
		return purchaseRepository.findAll(pageable).map(PurchaseMapper::toDto);
	}

	@Override
	public PurchaseDTO findOne(Long id) {
		Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Purchase with this ID does not Exist in our System!"));
		return PurchaseMapper.toDto(purchase);
	}

	@Override
	public PurchaseDTO addPurchase(String description, Double amount, LocalDate purchaseDate) {
		Double rounded = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
		Purchase newPurchase = Purchase.builder().description(description).dateTransaction(purchaseDate).amount(rounded).build();
		purchaseRepository.save(newPurchase);
		return PurchaseMapper.toDto(newPurchase);
	}

	@Override
	public PurchaseDTO update(Long id, Map<String, Object> updatingFields) {
		Purchase previous = purchaseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Purchase with this ID does not Exist in our System! / We will not Update!"));
		List<String> fieldNames = Arrays.stream(Purchase.class.getDeclaredFields()).map(x -> x.getName()).collect(Collectors.toList());
		if (updatingFields.keySet().stream().anyMatch(fieldNames::contains)) {
			updatingFields.forEach((key, value) -> {
				Field f = ReflectionUtils.findField(Purchase.class, key);
				if (fieldNames.contains(key)) { //This seems overhead, but it protects again edge cases when PATCH enters with extra / misspelled fields
					f.setAccessible(true); //To Handle Private Fields
					ReflectionUtils.setField(f, previous, value);
				}
			});
			purchaseRepository.save(previous);
		} else {
			throw new IllegalArgumentException("There is no Valid Field to be Updated! The Purchase will stay the same!");
		}
		return PurchaseMapper.toDto(previous);
	}

	@Override
	public void delete(Long id) {
		Purchase toDelete = purchaseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Purchase with this ID does not Exist in our System!"));
		purchaseRepository.delete(toDelete);
	}

	@Override
	public PurchaseDTO findByDescriptionLikeIgnoreCase(String description){
		Purchase found = purchaseRepository.findByDescriptionLikeIgnoreCase(description);
		return PurchaseMapper.toDto(found);
	}
}
