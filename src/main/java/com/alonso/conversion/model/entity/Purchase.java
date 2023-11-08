package com.alonso.conversion.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Purchase POJO using LOMBOK for less boilerplate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class) //This will enable the Spring to configure automatically Modified and Created Date of the Objects
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Description is mandatory")
	@Size(max = 50)
	private String description;
	private LocalDate dateTransaction;
	@Positive
	@Max(value =15000000l)
	private Double amount;
	@CreatedDate
	@Column(name = "dateCreated", updatable = false, nullable = false)
	private LocalDateTime dateCreated;
	@LastModifiedDate
	private LocalDateTime dateModified;
}
