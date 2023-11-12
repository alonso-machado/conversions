package com.alonso.conversion.handler;

import com.alonso.conversion.model.dto.ConversionPurchaseDTO;
import com.alonso.conversion.service.ConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ConversionHandler {

	private ConversionService conversionService;

	public Mono<ServerResponse> findOneFunctional(ServerRequest serverRequest) {
		String id = serverRequest.pathVariable("id");
		Mono<ConversionPurchaseDTO> aw = conversionService.findById(Long.valueOf(id));
		Mono<ServerResponse> notFoundResponse = ServerResponse.notFound().build();

		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(aw, ConversionPurchaseDTO.class)
				.switchIfEmpty(notFoundResponse);
	}
}
