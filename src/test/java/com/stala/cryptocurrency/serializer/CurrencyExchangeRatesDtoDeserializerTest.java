package com.stala.cryptocurrency.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stala.cryptocurrency.dto.CurrencyExchangeRateDto;
import com.stala.cryptocurrency.dto.CurrencyExchangeRatesDto;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JsonTest
public class CurrencyExchangeRatesDtoDeserializerTest {

    @Value("classpath:coin_api_exchangerate_response.json")
    private Resource jsonResponse;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deserialize_shouldCreateProperObject() throws IOException {
        //given
        List<CurrencyExchangeRateDto> expectedExchangeRates = Arrays.asList(CurrencyExchangeRateDto.of("USD", new BigDecimal("7249.1452562793630425765801595")));
        CurrencyExchangeRatesDto expectedExchange = CurrencyExchangeRatesDto.of("BTC", expectedExchangeRates);
        String json = FileUtils.readFileToString(jsonResponse.getFile(), StandardCharsets.UTF_8);

        //when
        CurrencyExchangeRatesDto resultExchange = objectMapper.readValue(json, CurrencyExchangeRatesDto.class);

        //then
        assertNotNull(resultExchange);
        assertEquals(resultExchange, expectedExchange);
    }
}
