package com.adamdr.salcalc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Currency {
    @JsonProperty(value = "currency")
    private String currency;
    @JsonProperty(value = "code")
    private String code;
    @JsonProperty(value = "rates")
    private List<Rates> rates;

    @Data
    @AllArgsConstructor
    public static class Rates {
        @JsonProperty(value = "no")
        private String no;
        @JsonProperty(value = "effectiveDate")
        private String effectiveDate;
        @JsonProperty(value = "mid")
        private BigDecimal currentValue;
    }

    public Currency(String currencyCode) {
        this.code = currencyCode;
    }
}
