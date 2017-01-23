package com.adamdr.salcalc.web.controller;

import com.adamdr.salcalc.model.dao.CountryDao;
import com.adamdr.salcalc.model.domain.Country;
import com.adamdr.salcalc.model.dto.ManageCountriesFormInputs;
import com.adamdr.salcalc.web.validators.CountryValidator;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Log4j
@Controller
public class CountryContoller {

    private CountryDao countryDao;

    @InitBinder("country")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(countryValidator);
    }

    @ModelAttribute(name = "countryList")
    public List<Country> getCountryList() {
        return countryDao.getAll();
    }

    @GetMapping("/manage")
    public String manageCountriesForm(Model model) {
        model.addAttribute("manageCountriesFormInputs", new ManageCountriesFormInputs());
        return "manage";
    }

    @GetMapping("/createcountry")
    public String addCountryForm(Model model) {
        model.addAttribute("country", new Country());
        return "country";
    }

    @GetMapping("/editcountry")
    public String editCountryForm(@ModelAttribute ManageCountriesFormInputs manageCountriesFormInputs, Model model) {
        Country country = countryDao.findByID(manageCountriesFormInputs.getCountryId());
        log.warn(country + " edit");
        model.addAttribute("country", country);
        return "country";
    }

    @GetMapping("/deletecountry")
    public String deleteCountry(@ModelAttribute ManageCountriesFormInputs manageCountriesFormInputs) {
        Country country = countryDao.findByID(manageCountriesFormInputs.getCountryId());
        log.warn(country + " deleted");
        countryDao.delete(country);
        return "redirect:/manage";
    }

    @PostMapping("/editcountry")
    public String editCountrySubmit(@ModelAttribute("country") @Valid Country country, BindingResult result) {
        if (result.hasErrors()) {
            return "country";
        } else {
            log.warn(country + " updated");
            countryDao.update(country);
            return "redirect:/manage";
        }
    }

    @Autowired
    private CountryValidator countryValidator;

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
}
