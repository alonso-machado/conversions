package com.alonso.conversion.repository;

import com.alonso.conversion.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	Optional<Purchase> findByDescriptionLikeIgnoreCase(String description);
}

