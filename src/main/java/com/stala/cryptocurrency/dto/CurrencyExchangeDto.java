package com.stala.cryptocurrency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor(staticName = "of")
public class CurrencyExchangeDto {

    @JsonProperty("from")
    private String baseCurrencyCode;
    @JsonProperty("to")
    private List<String> quoteCurrencyCodes;
    private BigDecimal amount;
}
