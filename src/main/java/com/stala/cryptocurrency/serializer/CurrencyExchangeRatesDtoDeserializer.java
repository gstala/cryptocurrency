package com.stala.cryptocurrency.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.stala.cryptocurrency.dto.CurrencyExchangeRateDto;
import com.stala.cryptocurrency.dto.CurrencyExchangeRatesDto;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JsonComponent
public class CurrencyExchangeRatesDtoDeserializer extends JsonDeserializer<CurrencyExchangeRatesDto> {

    @Override
    public CurrencyExchangeRatesDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode rootNode = deserializationContext.readTree(jsonParser);
        String baseCurrencyCode = rootNode.get("asset_id_base").textValue();
        Iterator<JsonNode> rateIterator = rootNode.get("rates").elements();
        List<CurrencyExchangeRateDto> rates = new ArrayList<>();
        while (rateIterator.hasNext()) {
            JsonNode rateNode = rateIterator.next();
            rates.add(CurrencyExchangeRateDto.of(rateNode.get("asset_id_quote").asText(), new BigDecimal(rateNode.get("rate").asText())));
        }

        return CurrencyExchangeRatesDto.of(baseCurrencyCode, rates);
    }
}
