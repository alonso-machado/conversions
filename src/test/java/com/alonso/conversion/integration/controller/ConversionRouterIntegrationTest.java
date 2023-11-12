package com.alonso.conversion.integration.controller;

import com.alonso.conversion.controller.PurchaseController;
import com.alonso.conversion.mappers.ConversionMapper;
import com.alonso.conversion.mappers.PurchaseMapper;
import com.alonso.conversion.model.dto.ConversionPurchaseDTO;
import com.alonso.conversion.model.dto.FiscalDataUpdatedDTO;
import com.alonso.conversion.model.dto.PurchaseDTO;
import com.alonso.conversion.model.entity.Purchase;
import com.alonso.conversion.model.response.FiscalData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConversionRouterIntegrationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private PurchaseController purchaseService;

	private ConversionMapper conversionMapper;

	private PurchaseMapper purchaseMapper;

	@Test
	public void whenTestGetConversionRouter_thenReturnConversionDTO() {
		//Arrange
		LocalDate purchaseTestDate = LocalDate.parse("2023-11-12");
		PurchaseDTO savedPurchase = purchaseService.addPurchase("ConversionRouterDescription", 25.68, purchaseTestDate).getBody();
		Purchase newPurchase = purchaseMapper.toEntity(savedPurchase);

		List<FiscalData> fiscalDatas = realConversionFrom12ofNovember2023();


		ConversionPurchaseDTO conversionPurchaseDTO = conversionMapper.toDto(newPurchase, fiscalDatas);

		//Act
		// GET request to /api/v1/conversion-functional/{id}
		WebTestClient.BodySpec<ConversionPurchaseDTO, ?> webResponse = webTestClient.get().uri("/api/v1/conversion-functional/{id}", newPurchase.getId())
				.exchange()
				.expectStatus().isOk()
				.expectBody(ConversionPurchaseDTO.class);

		List<FiscalDataUpdatedDTO> responseList = webResponse.returnResult().getResponseBody().getConversions();
		List<FiscalDataUpdatedDTO> expectedMockedList = conversionPurchaseDTO.getConversions();

		//Assert
		assertThatList(responseList).containsExactlyInAnyOrderElementsOf(expectedMockedList);
	}

	public List<FiscalData> realConversionFrom12ofNovember2023() {
		List<FiscalData> fiscalDatas =
				Arrays.asList(
						new FiscalData("Afghanistan-Afghani", 77.86, LocalDate.parse("2023-11-12")),
						new FiscalData("Albania-Lek", 100.05, LocalDate.parse("2023-11-12")),
						new FiscalData("Algeria-Dinar", 137.067, LocalDate.parse("2023-11-12")),
						new FiscalData("Angola-Kwanza", 830.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Antigua & Barbuda-E. Caribbean Dollar", 2.7, LocalDate.parse("2023-11-12")),
						new FiscalData("Argentina-Peso", 365.5, LocalDate.parse("2023-11-12")),
						new FiscalData("Armenia-Dram", 390.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Australia-Dollar", 1.542, LocalDate.parse("2023-11-12")),
						new FiscalData("Azerbaijan-Manat", 1.7, LocalDate.parse("2023-11-12")),
						new FiscalData("Bahamas-Dollar", 1.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Bahrain-Dinar", 0.377, LocalDate.parse("2023-11-12")),
						new FiscalData("Bangladesh-Taka", 110.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Barbados-Dollar", 2.02, LocalDate.parse("2023-11-12")),
						new FiscalData("Belarus-New Ruble", 3.264, LocalDate.parse("2023-11-12")),
						new FiscalData("Belize-Dollar", 2.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Benin-CFA Franc", 615.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Bermuda-Dollar", 1.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Bolivia-Boliviano", 6.86, LocalDate.parse("2023-11-12")),
						new FiscalData("Bosnia-Marka", 1.846, LocalDate.parse("2023-11-12")),
						new FiscalData("Botswana-Pula", 13.643, LocalDate.parse("2023-11-12")),
						new FiscalData("Brazil-Real", 5.033, LocalDate.parse("2023-11-12")),
						new FiscalData("Brunei-Dollar", 1.363, LocalDate.parse("2023-11-12")),
						new FiscalData("Bulgaria-Lev New", 1.846, LocalDate.parse("2023-11-12")),
						new FiscalData("Burkina Faso-CFA Franc", 615.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Burundi-Franc", 2841.65, LocalDate.parse("2023-11-12")),
						new FiscalData("Cambodia-Riel", 4051.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Cameroon-CFA Franc", 618.94, LocalDate.parse("2023-11-12")),
						new FiscalData("Canada-Dollar", 1.343, LocalDate.parse("2023-11-12")),
						new FiscalData("Cape Verde-Escudo", 104.03, LocalDate.parse("2023-11-12")),
						new FiscalData("Cayman Islands-Dollar", 0.82, LocalDate.parse("2023-11-12")),
						new FiscalData("Central African Rep.-CFA Franc", 618.94, LocalDate.parse("2023-11-12")),
						new FiscalData("Chad-CFA Franc", 618.94, LocalDate.parse("2023-11-12")),
						new FiscalData("Chile-Peso", 902.65, LocalDate.parse("2023-11-12")),
						new FiscalData("China-Renminbi", 7.301, LocalDate.parse("2023-11-12")),
						new FiscalData("Colombia-Peso", 4074.2, LocalDate.parse("2023-11-12")),
						new FiscalData("Comoros-Franc", 467.04, LocalDate.parse("2023-11-12")),
						new FiscalData("Congo-CFA Franc", 618.94, LocalDate.parse("2023-11-12")),
						new FiscalData("Costa Rica-Colon", 528.75, LocalDate.parse("2023-11-12")),
						new FiscalData("Cote D'ivoire-CFA Franc", 615.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Cuba-Chavito", 1.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Cuba-Peso", 24.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Cyprus-Euro", 0.92, LocalDate.parse("2023-11-12")),
						new FiscalData("Czech Republic-Koruna", 22.389, LocalDate.parse("2023-11-12")),
						new FiscalData("Dem. Rep. of Congo-Congolese Franc", 2477.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Denmark-Krone", 7.038, LocalDate.parse("2023-11-12")),
						new FiscalData("Djibouti-Franc", 177.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Dominican Republic-Peso", 56.58, LocalDate.parse("2023-11-12")),
						new FiscalData("Ecuador-Dolares", 1.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Egypt-Pound", 30.85, LocalDate.parse("2023-11-12")),
						new FiscalData("El Salvador-Dollar", 1.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Equatorial Guinea-CFA Franc", 618.94, LocalDate.parse("2023-11-12")),
						new FiscalData("Eritrea-Nakfa", 15.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Eswatini-Lilangeni", 18.864, LocalDate.parse("2023-11-12")),
						new FiscalData("Ethiopia-Birr", 55.222, LocalDate.parse("2023-11-12")),
						new FiscalData("Euro Zone-Euro", 0.944, LocalDate.parse("2023-11-12")),
						new FiscalData("Fiji-Dollar", 2.251, LocalDate.parse("2023-11-12")),
						new FiscalData("Gabon-CFA Franc", 618.94, LocalDate.parse("2023-11-12")),
						new FiscalData("Gambia-Dalasi", 61.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Georgia-Lari", 2.65, LocalDate.parse("2023-11-12")),
						new FiscalData("Ghana-Cedi", 11.57, LocalDate.parse("2023-11-12")),
						new FiscalData("Grenada-E.Caribbean Dollar", 2.7, LocalDate.parse("2023-11-12")),
						new FiscalData("Guatemala-Quentzal", 7.85, LocalDate.parse("2023-11-12")),
						new FiscalData("Guinea Bissau-CFA Franc", 615.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Guinea-Franc", 8485.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Guyana-Dollar", 215.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Haiti-Gourde", 134.019, LocalDate.parse("2023-11-12")),
						new FiscalData("Honduras-Lempira", 24.612, LocalDate.parse("2023-11-12")),
						new FiscalData("Hong Kong-Dollar", 7.831, LocalDate.parse("2023-11-12")),
						new FiscalData("Hungary-Forint", 367.93, LocalDate.parse("2023-11-12")),
						new FiscalData("Iceland-Krona", 136.6, LocalDate.parse("2023-11-12")),
						new FiscalData("India-Rupee", 83.071, LocalDate.parse("2023-11-12")),
						new FiscalData("Indonesia-Rupiah", 15471.6, LocalDate.parse("2023-11-12")),
						new FiscalData("Iran-Rial", 42000.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Iraq-Dinar", 1308.6, LocalDate.parse("2023-11-12")),
						new FiscalData("Israel-Shekel", 3.822, LocalDate.parse("2023-11-12")),
						new FiscalData("Jamaica-Dollar", 154.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Japan-Yen", 149.2, LocalDate.parse("2023-11-12")),
						new FiscalData("Jordan-Dinar", 0.708, LocalDate.parse("2023-11-12")),
						new FiscalData("Kazakhstan-Tenge", 477.55, LocalDate.parse("2023-11-12")),
						new FiscalData("Kenya-Shilling", 148.2, LocalDate.parse("2023-11-12")),
						new FiscalData("Korea-Won", 1344.07, LocalDate.parse("2023-11-12")),
						new FiscalData("Kuwait-Dinar", 0.309, LocalDate.parse("2023-11-12")),
						new FiscalData("Kyrgyzstan-Som", 88.71, LocalDate.parse("2023-11-12")),
						new FiscalData("Laos-Kip", 20322.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Lebanon-Pound", 15000.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Lesotho-Maloti", 18.864, LocalDate.parse("2023-11-12")),
						new FiscalData("Liberia-Dollar", 187.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Libya-Dinar", 4.877, LocalDate.parse("2023-11-12")),
						new FiscalData("Madagascar-Ariary", 4468.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Malawi-Kwacha", 1115.72, LocalDate.parse("2023-11-12")),
						new FiscalData("Malaysia-Ringgit", 4.694, LocalDate.parse("2023-11-12")),
						new FiscalData("Maldives-Rufiyaa", 15.42, LocalDate.parse("2023-11-12")),
						new FiscalData("Mali-CFA Franc", 615.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Marshall Islands-U.S. Dollar", 1.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Mauritania-Ouguiya", 38.15, LocalDate.parse("2023-11-12")),
						new FiscalData("Mauritius-Rupee", 44.32, LocalDate.parse("2023-11-12")),
						new FiscalData("Mexico-Peso", 17.471, LocalDate.parse("2023-11-12")),
						new FiscalData("Micronesia-U.S. Dollar", 1.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Moldova-LEU", 18.15, LocalDate.parse("2023-11-12")),
						new FiscalData("Mongolia-Tugrik", 3459.36, LocalDate.parse("2023-11-12")),
						new FiscalData("Morocco-Dirham", 10.267, LocalDate.parse("2023-11-12")),
						new FiscalData("Mozambique-Metical", 63.25, LocalDate.parse("2023-11-12")),
						new FiscalData("Myanmar-Kyat", 2930.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Nambia-Dollar", 18.864, LocalDate.parse("2023-11-12")),
						new FiscalData("Nepal-Rupee", 132.9, LocalDate.parse("2023-11-12")),
						new FiscalData("Netherlands Antilles-Guilder", 1.78, LocalDate.parse("2023-11-12")),
						new FiscalData("New Zealand-Dollar", 1.658, LocalDate.parse("2023-11-12")),
						new FiscalData("Nicaragua-Cordoba", 36.5, LocalDate.parse("2023-11-12")),
						new FiscalData("Niger-CFA Franc", 615.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Nigeria-Naira", 777.6, LocalDate.parse("2023-11-12")),
						new FiscalData("Norway-Krone", 10.623, LocalDate.parse("2023-11-12")),
						new FiscalData("Oman-Rial", 0.385, LocalDate.parse("2023-11-12")),
						new FiscalData("Pakistan-Rupee", 288.3, LocalDate.parse("2023-11-12")),
						new FiscalData("Palau-Dollar", 1.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Panama-Dolares", 1.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Papua New Guinea-Kina", 3.54, LocalDate.parse("2023-11-12")),
						new FiscalData("Paraguay-Guarani", 7288.81, LocalDate.parse("2023-11-12")),
						new FiscalData("Peru-Sol", 3.772, LocalDate.parse("2023-11-12")),
						new FiscalData("Philippines-Peso", 56.615, LocalDate.parse("2023-11-12")),
						new FiscalData("Poland-Zloty", 4.368, LocalDate.parse("2023-11-12")),
						new FiscalData("Qatar-Riyal", 3.645, LocalDate.parse("2023-11-12")),
						new FiscalData("Rep. of N. Macedonia-Denar", 57.82, LocalDate.parse("2023-11-12")),
						new FiscalData("Romania-New Leu", 4.691, LocalDate.parse("2023-11-12")),
						new FiscalData("Russia-Ruble", 97.675, LocalDate.parse("2023-11-12")),
						new FiscalData("Rwanda-Franc", 1200.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Sao Tome & Principe-New Dobras", 23.103, LocalDate.parse("2023-11-12")),
						new FiscalData("Saudi Arabia-Riyal", 3.75, LocalDate.parse("2023-11-12")),
						new FiscalData("Senegal-CFA Franc", 615.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Serbia-Dinar", 110.45, LocalDate.parse("2023-11-12")),
						new FiscalData("Seychelles-Rupee", 13.62, LocalDate.parse("2023-11-12")),
						new FiscalData("Sierra Leone-Leone", 22.35, LocalDate.parse("2023-11-12")),
						new FiscalData("Sierra Leone-Old Leone", 21.4, LocalDate.parse("2023-11-12")),
						new FiscalData("Singapore-Dollar", 1.363, LocalDate.parse("2023-11-12")),
						new FiscalData("Solomon Islands-Dollar", 8.13, LocalDate.parse("2023-11-12")),
						new FiscalData("Somali-Shilling", 568.0, LocalDate.parse("2023-11-12")),
						new FiscalData("South Africa-Rand", 18.864, LocalDate.parse("2023-11-12")),
						new FiscalData("South Sudan-Sudanese Pound", 1013.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Sri Lanka-Rupee", 323.75, LocalDate.parse("2023-11-12")),
						new FiscalData("St. Lucia-E. Caribbean Dollar", 2.7, LocalDate.parse("2023-11-12")),
						new FiscalData("Sudan-Pound", 635.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Sudan-Pound", 725.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Suriname-Dollar", 38.022, LocalDate.parse("2023-11-12")),
						new FiscalData("Sweden-Krona", 10.881, LocalDate.parse("2023-11-12")),
						new FiscalData("Switzerland-Franc", 0.913, LocalDate.parse("2023-11-12")),
						new FiscalData("Syria-Pound", 8585.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Taiwan-Dollar", 32.165, LocalDate.parse("2023-11-12")),
						new FiscalData("Tajikistan-Somoni", 10.97, LocalDate.parse("2023-11-12")),
						new FiscalData("Tanzania-Shilling", 2500.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Thailand-Baht", 36.5, LocalDate.parse("2023-11-12")),
						new FiscalData("Timor-Leste-Dili", 1.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Togo-CFA Franc", 615.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Tonga-Pa'anga", 2.315, LocalDate.parse("2023-11-12")),
						new FiscalData("Trinidad & Tobago-Dollar", 6.747, LocalDate.parse("2023-11-12")),
						new FiscalData("Tunisia-Dinar", 3.166, LocalDate.parse("2023-11-12")),
						new FiscalData("Turkey-New Lira", 27.427, LocalDate.parse("2023-11-12")),
						new FiscalData("Turkmenistan-New Manat", 3.491, LocalDate.parse("2023-11-12")),
						new FiscalData("Uganda-Shilling", 3750.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Ukraine-Hryvnia", 36.929, LocalDate.parse("2023-11-12")),
						new FiscalData("United Arab Emirates-Dirham", 3.673, LocalDate.parse("2023-11-12")),
						new FiscalData("United Kingdom-Pound", 0.816, LocalDate.parse("2023-11-12")),
						new FiscalData("Uruguay-Peso", 38.45, LocalDate.parse("2023-11-12")),
						new FiscalData("Uzbekistan-Som", 12175.72, LocalDate.parse("2023-11-12")),
						new FiscalData("Vanuatu-Vatu", 119.88, LocalDate.parse("2023-11-12")),
						new FiscalData("Venezuela-Bolivar Soberano", 34.215, LocalDate.parse("2023-11-12")),
						new FiscalData("Venezuela-Fuerte (OLD)", 248832.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Vietnam-Dong", 24280.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Western Samoa-Tala", 2.682, LocalDate.parse("2023-11-12")),
						new FiscalData("Yemen-Rial", 528.0, LocalDate.parse("2023-11-12")),
						new FiscalData("Zambia-New Kwacha", 20.9, LocalDate.parse("2023-11-12")),
						new FiscalData("Zimbabwe-RTGS", 5466.75, LocalDate.parse("2023-11-12"))
				);
		return fiscalDatas;
	}

}