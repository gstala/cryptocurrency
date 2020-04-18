package com.stala.cryptocurrency.service;

import com.stala.cryptocurrency.dto.CurrencyExchangeForecastDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CryptoCurrencyService {

    private static final String PROVISION_PCT = "0.01";

    @Async
    public CompletableFuture<CurrencyExchangeForecastDto> createCurrencyExchangeForecast(String currencyCode, BigDecimal amount, BigDecimal rate) {
        BigDecimal result = amount.multiply(rate);
        BigDecimal fee = result.multiply(new BigDecimal(PROVISION_PCT));
        CurrencyExchangeForecastDto currencyExchangeForecast = CurrencyExchangeForecastDto.of(currencyCode, result.add(fee), rate, fee);

        return CompletableFuture.completedFuture(currencyExchangeForecast);
    }
}
