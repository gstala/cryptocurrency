package com.stala.cryptocurrency.service;

import com.stala.cryptocurrency.dto.CurrencyExchangeRatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinApiService {

    private final RestTemplate restTemplate;

    public CurrencyExchangeRatesDto getExchangeRates(String currency, List<String> quoteCurrencyCodeFilters) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
                .pathSegment("v1", "exchangerate", currency)
                .queryParam("filter_asset_id", quoteCurrencyCodeFilters != null ? String.join(",", quoteCurrencyCodeFilters) : "");

        return restTemplate.getForObject(uriBuilder.toUriString(), CurrencyExchangeRatesDto.class);
    }
}
