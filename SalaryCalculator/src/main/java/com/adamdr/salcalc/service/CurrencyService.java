package com.adamdr.salcalc.service;

import com.adamdr.salcalc.model.dto.Currency;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

@Log4j
@Service
public class CurrencyService {

    private static final String A = "A";
    private static final String B = "B";

    public BigDecimal getCurrencyCurrentValue(String currency) {
        if (getCurrency(currency).isPresent())
            return getCurrency(currency).get().getRates().stream().findFirst().get().getCurrentValue();
        else return BigDecimal.valueOf(1.0);
    }

    public Optional<Currency> getCurrency(String currency) {
        try {
            return Optional.of(internalGetCurrency(A, currency));
        } catch (HttpClientErrorException e) {
            try {
                return Optional.of(internalGetCurrency(B, currency));
            } catch (Exception ex) {
                log.error("Get Currency ERROR: " + ex);
                return Optional.empty();
            }
        }
    }

    private Currency internalGetCurrency(String table, String currency) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/{table}/{currency}?format=json";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Currency.class, table, currency);
    }
}
