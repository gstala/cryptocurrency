package com.stala.cryptocurrency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor(staticName = "of")
public class CurrencyExchangeForecastsDto {

    private String baseCurrencyCode;
    private BigDecimal amount;
    private List<CurrencyExchangeForecastDto> quoteForecasts;
}
