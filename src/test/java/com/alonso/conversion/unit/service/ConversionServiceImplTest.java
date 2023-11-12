package com.alonso.conversion.unit.service;

import com.alonso.conversion.model.entity.Purchase;
import com.alonso.conversion.model.response.Fiscal;
import com.alonso.conversion.model.response.Links;
import com.alonso.conversion.repository.PurchaseRepository;
import com.alonso.conversion.service.ConversionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConversionServiceImplTest {
     /*
    @ExtendWith(MockitoExtension.class)
    class ConversionServiceImplTest {

        @InjectMocks
        private ConversionServiceImpl conversionService;

        @Mock
        private WebClient webClientMock;

        @Mock
        private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;

        @Mock
        private WebClient.RequestBodySpec requestBodySpecMock;

        @SuppressWarnings("rawtypes")
        @Mock
        private WebClient.RequestHeadersSpec requestHeadersSpecMock;

        @SuppressWarnings("rawtypes")
        @Mock
        private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

        @Mock
        private WebClient.ResponseSpec responseSpecMock;

        @SuppressWarnings("unchecked")
        @Test
        public void fetchDataConversionFromAPI_shouldRetrieveData()  throws Exception {

            // Arrange
            String urlFilters = "https://example.com/api/data";
            Fiscal expectedFiscalData = new Fiscal();
            expectedFiscalData.setLinks(Links.builder().next("/nextPage").build());

            when(webClientMock.post()).thenReturn(requestBodyUriSpecMock);
            when(requestBodyUriSpecMock.uri(anyString())).thenReturn(requestBodySpecMock);
            when(requestBodySpecMock.header(any(), any())).thenReturn(requestBodySpecMock);
            when(requestBodySpecMock.body(any())).thenReturn(requestHeadersSpecMock);
            when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
            when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Fiscal>>notNull())).thenReturn(Mono.just(expectedFiscalData));

            // Act
            Mono<Fiscal> response = conversionService.fetchDataConversionFromAPI(urlFilters);

            // Assert
            StepVerifier.create(response)
                    .expectNext(expectedFiscalData)
                    .verifyComplete();

        }
        */

    public static MockWebServer mockBackEnd;

    ObjectMapper objectMapper = new ObjectMapper();

    private ConversionServiceImpl conversionService;

    private PurchaseRepository purchaseRepository;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        purchaseRepository = mock(PurchaseRepository.class);
        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        conversionService = new ConversionServiceImpl(purchaseRepository, WebClient.create(baseUrl));
    }

    @Test
    void fetchDataConversionFromAPI_shouldRetrieveData() throws Exception {
        // Arrange
        Long purchaseId = 5L;
        String urlFilters = "/api/v1/conversion/"+purchaseId;
        Fiscal expectedFiscalData = new Fiscal();
        expectedFiscalData.setLinks(Links.builder().next("/nextPage").build());
        Purchase expectedPurchase = Purchase.builder().id(purchaseId).description("descriptionTest").amount(5.0).dateTransaction(LocalDate.now()).dateCreated(LocalDateTime.now()).build();
        when(purchaseRepository.findById(purchaseId)).thenReturn(Optional.ofNullable(expectedPurchase));

        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(expectedFiscalData))
                .addHeader("Content-Type", "application/json"));

        // Act
        Mono<Fiscal> response = conversionService.fetchDataConversionFromAPI(urlFilters);

        // Assert
        StepVerifier.create(response)
                .expectNextMatches(r -> r.getLinks().getNext().equals("/nextPage"))
                .verifyComplete();

        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals(urlFilters, recordedRequest.getPath());
    }



}