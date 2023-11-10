package com.alonso.conversion.mappers;


import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.model.dto.PurchaseDTOAdmin;
import com.alonso.conversion.model.entity.Purchase;

// Mapper to Convert to DTO or to Entity
// Doing this by hand usually get more Performance / Memory usage then using ModelMapper or MapStruct
public class PurchaseMapper {

	public static Purchase toEntity(PurchaseDTO pDto) {

		Purchase purchase = new Purchase();
		purchase.setId(pDto.getId());
		purchase.setDescription(pDto.getDescription());
		purchase.setDateTransaction(pDto.getDateTransaction());
		purchase.setAmount(pDto.getAmount());

		return purchase;
	}

	public static PurchaseDTO toDto(Purchase purchase) {
		PurchaseDTO purchaseDTO = new PurchaseDTO(
				purchase.getId(),
				purchase.getDescription(),
				purchase.getDateTransaction(),
				purchase.getAmount()
		);
		return purchaseDTO;
	}

	public static PurchaseDTOAdmin toAdminDto(Purchase purchase) {
		PurchaseDTOAdmin adminDTO = new PurchaseDTOAdmin(
				purchase.getId(),
				purchase.getDescription(),
				purchase.getDateTransaction(),
				purchase.getAmount(),
				purchase.getDateCreated(),
				purchase.getDateModified()
		);
		return adminDTO;
	}

}
