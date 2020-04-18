package com.stala.cryptocurrency.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public class CurrencyExchangeRatesDto {

    private String sourceCurrencyCode;
    private List<CurrencyExchangeRateDto> rates;
}
