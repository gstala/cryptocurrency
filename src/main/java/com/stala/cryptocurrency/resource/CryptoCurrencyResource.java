package com.stala.cryptocurrency.resource;

import com.stala.cryptocurrency.dto.CurrencyExchangeDto;
import com.stala.cryptocurrency.dto.CurrencyExchangeForecastsDto;
import com.stala.cryptocurrency.dto.CurrencyExchangeRatesDto;
import com.stala.cryptocurrency.handler.CryptoCurrencyHandler;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.text.MessageFormat.format;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/currencies")
public class CryptoCurrencyResource {

    private final CryptoCurrencyHandler cryptoCurrencyHandler;

    @GetMapping("/{currency}")
    @ApiOperation("Get Crypto Currency rates")
    public CurrencyExchangeRatesDto getCurrencyRates(@PathVariable("currency") String currency, @RequestParam(value = "filter", required = false) List<String> currencyRateFilters) {
        log.info(format("Requested Crypto Currency rates with data: [currency: {0}, currencyRateFilters: {1}]", currency, currencyRateFilters));

        return cryptoCurrencyHandler.getCurrencyRates(currency, currencyRateFilters);
    }

    @PostMapping("/exchange")
    @ApiOperation("Get Crypto Currency exchange forecast")
    public CurrencyExchangeForecastsDto getCurrencyExchangeForecasts(@RequestBody CurrencyExchangeDto currencyExchange) {
        log.info(format("Requested Crypto Currency exchange forecast with data: [currencyExchange: {0}]", currencyExchange));

        return cryptoCurrencyHandler.getCurrencyExchangeForecasts(currencyExchange);
    }

}
