package com.stala.cryptocurrency.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stala.cryptocurrency.dto.CurrencyExchangeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CryptoCurrencyResourceIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getCurrencyRates_shouldReturnStatusCodeOk() throws Exception {
        mvc.perform(get("/currencies/BTC?filter=USD")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getCurrencyExchangeForecasts_shouldReturnStatusCodeOk() throws Exception {
        CurrencyExchangeDto currencyExchange = CurrencyExchangeDto.of("BTC", Arrays.asList("USD"), new BigDecimal(1));

        mvc.perform(post("/currencies/exchange")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(currencyExchange)))
                .andExpect(status().isOk());
    }

}
