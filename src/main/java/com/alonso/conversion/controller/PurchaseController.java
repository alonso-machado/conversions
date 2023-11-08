package com.alonso.conversion.controller;


import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/transaction")
public class PurchaseController {

	private PurchaseService service;

	@GetMapping("/")
	@Operation(summary = "Return all Purchases in the System Paged (Default is 20 per page)")
	public ResponseEntity<Page<PurchaseDTO>> getAll(@RequestParam(required = false) @PageableDefault(size = 20) Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}

	@PostMapping("/")
	@Operation(summary = "Add a new Purchase in the System.")
	public ResponseEntity<PurchaseDTO> addPurchase(@Parameter(description = "The description of the Purchase") @Valid @RequestParam String description,
	                                          @Parameter(description = "The Price of the Purchase", required = true) @RequestParam(defaultValue = "0.0") @Valid Double amount,
	                                          @Parameter(description = "The Date of the Purchase", required = true) @RequestParam LocalDate purchaseDate) {
		return ResponseEntity.ok(service.addPurchase(description, amount, purchaseDate));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get the Purchase with that ID.")
	public ResponseEntity<PurchaseDTO> getPurchase(@NotNull @PathVariable Long id) {
		return ResponseEntity.ok(service.findOne(id));
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Update the specific Purchase in the System.")
	public ResponseEntity<PurchaseDTO> updatePurchase(@NotNull @PathVariable Long id, @RequestBody Map<String, Object> updatingFields) {
		return ResponseEntity.ok(service.update(id, updatingFields));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete the Purchase with that ID.")
	public ResponseEntity<String> deletePost(@NotNull @PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>("Purchase deleted successfully.", HttpStatus.OK);
	}
}
