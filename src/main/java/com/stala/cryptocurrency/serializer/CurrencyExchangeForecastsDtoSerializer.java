package com.stala.cryptocurrency.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.stala.cryptocurrency.dto.CurrencyExchangeForecastDto;
import com.stala.cryptocurrency.dto.CurrencyExchangeForecastsDto;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class CurrencyExchangeForecastsDtoSerializer extends JsonSerializer<CurrencyExchangeForecastsDto> {

    @Override
    public void serialize(CurrencyExchangeForecastsDto currencyExchangeForecasts, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("from", currencyExchangeForecasts.getBaseCurrencyCode());
        for (CurrencyExchangeForecastDto currencyExchangeForecast : currencyExchangeForecasts.getQuoteForecasts()) {
            jsonGenerator.writeObjectFieldStart(currencyExchangeForecast.getQuoteCurrencyCode());
            jsonGenerator.writeStringField("rate", currencyExchangeForecast.getRate().toString());
            jsonGenerator.writeStringField("amount", currencyExchangeForecasts.getAmount().toString());
            jsonGenerator.writeStringField("result", currencyExchangeForecast.getResult().toString());
            jsonGenerator.writeStringField("fee", currencyExchangeForecast.getFee().toString());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndObject();
    }
}
