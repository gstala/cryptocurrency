package com.stala.cryptocurrency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(staticName = "of")
public class CurrencyExchangeForecastDto {

    private String quoteCurrencyCode;
    private BigDecimal result;
    private BigDecimal rate;
    private BigDecimal fee;
}
