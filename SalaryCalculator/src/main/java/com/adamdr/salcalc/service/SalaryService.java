package com.adamdr.salcalc.service;

import com.adamdr.salcalc.model.domain.Country;
import com.adamdr.salcalc.model.dto.HomeFormInputs;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Log4j
@Service
public class SalaryService {

    private CurrencyService currencyService;

    public BigDecimal calculateSalary(Country country, HomeFormInputs homeFormInputs) {
        BigDecimal inputSalary = getSalaryFromInputs(homeFormInputs);
        log.warn(inputSalary);
        BigDecimal currencyCurrentValue = currencyService.getCurrencyCurrentValue(country.getCurrency());
        log.warn(currencyCurrentValue);
        return calculateResultSalary(inputSalary, currencyCurrentValue, country);
    }

    private BigDecimal getSalaryFromInputs(HomeFormInputs homeFormInputs) {
        return new BigDecimal(homeFormInputs.getSalary());
    }

    private BigDecimal calculateResultSalary(BigDecimal daySalary, BigDecimal currencyCurrentValue, Country country) {
        BigDecimal monthlySalary = (daySalary.multiply(new BigDecimal(22)));
        log.warn(monthlySalary);
        BigDecimal monthlySalaryDecreasedByFixedCosts = monthlySalary.subtract(country.getFixedCosts());
        log.warn(monthlySalaryDecreasedByFixedCosts);
        BigDecimal monthlySalaryDecreasedByFixedCostsAndIncomeTax =
                monthlySalaryDecreasedByFixedCosts.
                        subtract(monthlySalaryDecreasedByFixedCosts.
                                multiply(BigDecimal.valueOf((country.getIncomeTax().doubleValue() / ((double) 100)))));
        return monthlySalaryDecreasedByFixedCostsAndIncomeTax.multiply(currencyCurrentValue).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Autowired
    public void setService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
}
