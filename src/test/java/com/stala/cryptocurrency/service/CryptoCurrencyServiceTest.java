package com.stala.cryptocurrency.service;

import com.stala.cryptocurrency.dto.CurrencyExchangeForecastDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CryptoCurrencyServiceTest {

    private CryptoCurrencyService cryptoCurrencyService = new CryptoCurrencyService();

    @Test
    public void createCurrencyExchangeForecast_shouldCreateProperly() throws ExecutionException, InterruptedException {
        //given
        String currencyCode = "BTC";
        BigDecimal amount = new BigDecimal("22.34");
        BigDecimal rate = new BigDecimal("0.12");
        BigDecimal result = new BigDecimal("2.707608");
        BigDecimal fee = new BigDecimal("0.026808");
        //when
        CurrencyExchangeForecastDto currencyExchangeForecast = cryptoCurrencyService.createCurrencyExchangeForecast(currencyCode, amount, rate).get();

        //then
        assertEquals(currencyCode, currencyExchangeForecast.getQuoteCurrencyCode());
        assertEquals(rate, currencyExchangeForecast.getRate());
        assertEquals(result, currencyExchangeForecast.getResult());
        assertEquals(fee, currencyExchangeForecast.getFee());
    }
}
