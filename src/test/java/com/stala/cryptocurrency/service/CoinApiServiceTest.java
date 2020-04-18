package com.stala.cryptocurrency.service;

import com.stala.cryptocurrency.dto.CurrencyExchangeRateDto;
import com.stala.cryptocurrency.dto.CurrencyExchangeRatesDto;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CoinApiServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private CoinApiService coinApiService;
    @Captor
    private ArgumentCaptor<String> urlCaptor;

    @Test
    public void getExchangeRates_shouldHandleRequestProperly() {
        //given
        String currencyCode = "BTC";
        List<String> quoteCurrencyCodeFilters = Arrays.asList("USD", "ETH");
        List<CurrencyExchangeRateDto> expectedExchangeRates = Arrays.asList(
                CurrencyExchangeRateDto.of("USD", new BigDecimal("0.23")),
                CurrencyExchangeRateDto.of("ETH", new BigDecimal("045"))
        );
        CurrencyExchangeRatesDto expectedExchange = CurrencyExchangeRatesDto.of("BTC", expectedExchangeRates);
        String expectedUrl = "/v1/exchangerate/BTC?filter_asset_id=USD,ETH";

        //when
        when(restTemplate.getForObject(urlCaptor.capture(), any())).thenReturn(expectedExchange);
        CurrencyExchangeRatesDto exchange = coinApiService.getExchangeRates(currencyCode, quoteCurrencyCodeFilters);

        //then
        assertEquals(expectedUrl, urlCaptor.getValue());
        assertEquals(expectedExchange.getSourceCurrencyCode(), exchange.getSourceCurrencyCode());
        assertEquals(expectedExchange.getRates().size(), exchange.getRates().size());
    }

}
