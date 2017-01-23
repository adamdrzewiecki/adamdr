package com.adamdr.salcalc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CurrencyService.class }, loader = AnnotationConfigContextLoader.class)
public class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void getCurrencyCurrentValue_WithCorrectCurrency()
    {
        assertThat(currencyService.getCurrencyCurrentValue("EUR")).isNotEqualByComparingTo(BigDecimal.valueOf(1.0));
    }

    @Test
    public void getCurrencyCurrentValue_WithIncorrectCurrency()
    {
        assertThat(currencyService.getCurrencyCurrentValue("EURO")).isEqualByComparingTo(BigDecimal.valueOf(1.0));
    }

    @Test
    public void getCurrencyCurrentValue_WithNull()
    {
        assertThat(currencyService.getCurrencyCurrentValue(null)).isEqualByComparingTo(BigDecimal.valueOf(1.0));
    }

    @Test
    public void getCurrency_WithCorrectCurrency()
    {
        assertThat(currencyService.getCurrency("EUR")).isNotEmpty();
    }

    @Test
    public void getCurrency_WithFakeCurrency()
    {
        assertThat(currencyService.getCurrency("EURO")).isEmpty();
    }

    @Test
    public void getCurrency_WithNull()
    {
        assertThat(currencyService.getCurrency(null)).isEmpty();
    }
}