package com.stala.cryptocurrency.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.stala.cryptocurrency.dto.CurrencyExchangeRateDto;
import com.stala.cryptocurrency.dto.CurrencyExchangeRatesDto;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class CurrencyExchangeRatesDtoSerializer extends JsonSerializer<CurrencyExchangeRatesDto> {

    @Override
    public void serialize(CurrencyExchangeRatesDto currencyExchangeRates, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("from", currencyExchangeRates.getSourceCurrencyCode());
        jsonGenerator.writeObjectFieldStart("rates");
        for (CurrencyExchangeRateDto exchangeRate : currencyExchangeRates.getRates()) {
            jsonGenerator.writeStringField(exchangeRate.getQuoteCurrencyCode(), exchangeRate.getRate().toString());
        }
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }
}
