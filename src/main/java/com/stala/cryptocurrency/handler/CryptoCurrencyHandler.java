package com.stala.cryptocurrency.handler;

import com.stala.cryptocurrency.dto.CurrencyExchangeDto;
import com.stala.cryptocurrency.dto.CurrencyExchangeForecastDto;
import com.stala.cryptocurrency.dto.CurrencyExchangeForecastsDto;
import com.stala.cryptocurrency.dto.CurrencyExchangeRatesDto;
import com.stala.cryptocurrency.service.CoinApiService;
import com.stala.cryptocurrency.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CryptoCurrencyHandler {

    private final CoinApiService coinApiService;
    private final CryptoCurrencyService cryptoCurrencyService;

    public CurrencyExchangeRatesDto getCurrencyRates(String currency, List<String> currencyRateFilters) {
        return coinApiService.getExchangeRates(currency, currencyRateFilters);
    }

    public CurrencyExchangeForecastsDto getCurrencyExchangeForecasts(CurrencyExchangeDto currencyExchange) {
        CurrencyExchangeRatesDto exchangeRates = coinApiService.getExchangeRates(currencyExchange.getBaseCurrencyCode(), currencyExchange.getQuoteCurrencyCodes());
        List<CompletableFuture<CurrencyExchangeForecastDto>> exchangeRateFutures = exchangeRates.getRates().stream()
                .map(exchangeRate -> cryptoCurrencyService.createCurrencyExchangeForecast(exchangeRate.getQuoteCurrencyCode(), currencyExchange.getAmount(), exchangeRate.getRate()))
                .collect(Collectors.toList());
        List<CurrencyExchangeForecastDto> exchangeForecasts = exchangeRateFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        return CurrencyExchangeForecastsDto.of(currencyExchange.getBaseCurrencyCode(), currencyExchange.getAmount(), exchangeForecasts);
    }

}
