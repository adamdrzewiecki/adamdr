package com.adamdr.salcalc.web.validators;

import com.adamdr.salcalc.model.domain.Country;
import com.adamdr.salcalc.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CountryValidator implements Validator {

    private CurrencyService currencyService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Country.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Country country = (Country) o;
        String currency = country.getCurrency();

        validateIsCurrencyInApi(currency, errors);
    }

    private boolean validateIsCurrencyInApi(String currency, Errors errors) {
        if(currencyService.getCurrency(currency).isPresent() || currency.equals("PLN")) {
            return true;
        }
        else {
            errors.rejectValue("currency", "country.currency.exist");
            return false;
        }
    }

    @Autowired
    public void setService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
}
