package com.alonso.conversion;


import io.netty.handler.logging.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
public class WebClientConfig {

	@Value("${fiscaldata.base.url}")
	public String addressBaseUrl;


	private HttpClient httpClient = HttpClient.create()
			.protocol(HttpProtocol.H2C, HttpProtocol.H2, HttpProtocol.HTTP11)
			.wiretap("reactor.client.ProductWebClient", LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);


	@Bean
	public WebClient webClient() {
		//return WebClient.builder().baseUrl(addressBaseUrl).build();
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
	}
}
