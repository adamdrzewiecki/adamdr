package com.adamdr.salcalc.service;

import com.adamdr.salcalc.model.domain.Country;
import com.adamdr.salcalc.model.dto.HomeFormInputs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SalaryService.class, CurrencyService.class}, loader = AnnotationConfigContextLoader.class)
public class SalaryServiceTest {

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void calculateSalary_WithIncorrectCurrencyParameters() {
        Country country = new Country("Niemcy", "EURO", 10, BigDecimal.valueOf(1200.00));
        HomeFormInputs homeFormInputs = new HomeFormInputs("100.00", 3L);
        assertThat(salaryService.calculateSalary(country, homeFormInputs)).isEqualByComparingTo(BigDecimal.valueOf(900.00));
    }

    @Test
    public void calculateSalary_WithNullCountryParameters() {
        Country country = null;
        HomeFormInputs homeFormInputs = new HomeFormInputs("100.00", 3L);
        try {
            assertThat(salaryService.calculateSalary(country, homeFormInputs));
        } catch (NullPointerException e) {
            assertThat(e).isInstanceOf(NullPointerException.class);
        }
    }

    @Test
    public void calculateSalary_WithNullHomeFormInputsParameters() {
        Country country = new Country("Niemcy", "EURO", 10, BigDecimal.valueOf(1200.00));
        HomeFormInputs homeFormInputs = null;
        try {
            assertThat(salaryService.calculateSalary(country, homeFormInputs));
        } catch (NullPointerException e) {
            assertThat(e).isInstanceOf(NullPointerException.class);
        }
    }

    @Test
    public void calculateSalary_WithCorrectParameters() {
        Object object = new SalaryService();
        BigDecimal salary = BigDecimal.valueOf(120.00);
        BigDecimal currency = currencyService.getCurrencyCurrentValue("EUR");
        Country country = new Country("Niemcy", "EUR", 20, BigDecimal.valueOf(800.00));
        HomeFormInputs homeFormInputs = new HomeFormInputs("120.00", 3L);

        BigDecimal result = calculateResultSalary(object, salary, currency, country);
        assertThat(salaryService.calculateSalary(country, homeFormInputs)).isEqualByComparingTo(result);
    }

    @Test
    public void calculateSalary_WithMaxSalaryParameters() {
        Object object = new SalaryService();
        BigDecimal salary = BigDecimal.valueOf(999999999.99);
        BigDecimal currency = currencyService.getCurrencyCurrentValue("EUR");
        Country country = new Country("Niemcy", "EUR", 20, BigDecimal.valueOf(800.00));
        HomeFormInputs homeFormInputs = new HomeFormInputs("999999999.99", 3L);

        BigDecimal result = calculateResultSalary(object, salary, currency, country);
        assertThat(salaryService.calculateSalary(country, homeFormInputs)).isEqualByComparingTo(result);
    }

    private BigDecimal calculateResultSalary(Object object, BigDecimal salary, BigDecimal currency, Country country) {
        try {
            Method method = SalaryService.class.getDeclaredMethod("calculateResultSalary", BigDecimal.class, BigDecimal.class, com.adamdr.salcalc.model.domain.Country.class);
            method.setAccessible(true);
             return (BigDecimal) method.invoke(object, salary, currency, country);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return BigDecimal.valueOf(0.0);
    }
}
