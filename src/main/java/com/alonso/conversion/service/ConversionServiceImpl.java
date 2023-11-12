package com.alonso.conversion.service;

import com.alonso.conversion.CachingConfig;
import com.alonso.conversion.exceptions.FiscalApiException;
import com.alonso.conversion.mappers.ConversionMapper;
import com.alonso.conversion.model.dto.ConversionPurchaseDTO;
import com.alonso.conversion.model.entity.Purchase;
import com.alonso.conversion.model.response.Fiscal;
import com.alonso.conversion.model.response.FiscalData;
import com.alonso.conversion.repository.PurchaseRepository;
import com.alonso.conversion.utils.FiscalDataUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static com.alonso.conversion.utils.WebClientUtils.filterPreviousSixMonths;

@Service
@Slf4j
@AllArgsConstructor
public class ConversionServiceImpl implements ConversionService {

	private PurchaseRepository purchaseRepository;

	private WebClient webClient;

	@SneakyThrows
	@Override
	@Cacheable("ConversionPurchaseDTO")
	public Mono<ConversionPurchaseDTO> findById(Long id) {
		Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Purchase with this ID does not Exist in our System!"));

		// Expecting this array to be filled by the Reactive Calls to the API
		return fiscalApiCallCaffeine(purchase.getDateTransaction()).map(fd -> ConversionMapper.toDto(purchase, FiscalDataUtils.UniqueFiscalDataList(fd)));
	}

	@Cacheable(key="#dateTransaction", cacheNames = "FiscalDataCacheCaffeine")
	public Mono<List<FiscalData>> fiscalApiCallCaffeine(LocalDate dateTransaction) {
		return CachingConfig.ofMonoFixedKey(Duration.ofSeconds(330), fiscalApiCallMonoCache(dateTransaction));
	}

	@Cacheable("FiscalDataCache")
	public Mono<List<FiscalData>> fiscalApiCallMonoCache(LocalDate dateTransaction) {
		return fiscalApiCall(dateTransaction).cache(Duration.ofSeconds(330));
	}

	@Cacheable("FiscalDataCache")
	public Mono<List<FiscalData>> fiscalApiCall(LocalDate dateTransaction) {

		String dateFilter = filterPreviousSixMonths(dateTransaction);

		return fetchDataConversionFromAPI(dateFilter).expand(response -> {
			String nextPage = response.getLinks().getNext();
			if (nextPage == null) { // No more pages
				return Mono.empty();
			} else {
				return fetchDataConversionFromAPI(dateFilter + UriUtils.decode(nextPage, "UTF-8"));
			}
		}).flatMap(responses -> Flux.fromIterable(responses.getData())).collectList();
	}


	public Mono<Fiscal> fetchDataConversionFromAPI(String urlFilters) {

		log.info("Fetching data from API: " + urlFilters);

		return webClient.get().uri(urlFilters).retrieve()
				.bodyToMono(Fiscal.class)
				.retryWhen(Retry.backoff(4,
								Duration.ofSeconds(5))
						.onRetryExhaustedThrow((spec, signal) -> {
							throw new FiscalApiException(
									"Service call failed even after retrying " + signal.totalRetries() + " times");
						}));
	}
}
