package com.adamdr.salcalc.web.validators;

import com.adamdr.salcalc.model.dao.CountryDao;
import com.adamdr.salcalc.model.domain.Country;
import com.adamdr.salcalc.service.CurrencyService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Log4j
public class CountryValidator implements Validator {

    private CurrencyService currencyService;
    private CountryDao countryDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return Country.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Country country = (Country) o;
        String currency = country.getCurrency();

        validateIsCurrencyInApi(currency, errors);
        validateIsCountryExistInDatabase(country, errors);
    }

    private void validateIsCurrencyInApi(String currency, Errors errors) {
        if (!(currencyService.getCurrency(currency).isPresent() || currency.equals("PLN")))
            errors.rejectValue("currency", "country.currency.exist");
    }

    private void validateIsCountryExistInDatabase(Country country, Errors errors) {
        try {
            Country countryInDatabase = countryDao.findByName(country.getName());
            if (!(country.getId().equals(countryInDatabase.getId()))) {
                log.error("Another country have the same name");
                errors.rejectValue("name", "country.name.exist");
            }
        } catch (EmptyResultDataAccessException e) {
            log.warn("This country doesn't exist in database");
        } catch (NullPointerException ex) {
            log.error("New country have the same name as existing one");
            errors.rejectValue("name", "country.name.exist");
        }
    }

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
}
