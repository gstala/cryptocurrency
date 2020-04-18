package com.stala.cryptocurrency.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public class CurrencyExchangeRateDto {

    private String quoteCurrencyCode;
    private BigDecimal rate;
}
