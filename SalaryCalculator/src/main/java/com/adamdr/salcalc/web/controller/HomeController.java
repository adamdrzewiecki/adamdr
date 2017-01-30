package com.adamdr.salcalc.web.controller;

import com.adamdr.salcalc.model.domain.Country;
import com.adamdr.salcalc.model.dao.CountryDao;
import com.adamdr.salcalc.model.dto.HomeFormInputs;
import com.adamdr.salcalc.service.SalaryService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Log4j
@Controller
public class HomeController {

    private CountryDao countryDao;
    private SalaryService salaryService;

    @ModelAttribute(name = "countryList")
    public List<Country> getCountryList() {
        return countryDao.getAll();
    }

    @ModelAttribute(name = "country")
    public Country getCountry() {
        return new Country();
    }

    @GetMapping({"/home", "/"})
    public String homeForm(Model model) {
        model.addAttribute("homeFormInputs", new HomeFormInputs());
        return "home";
    }

    @PostMapping("/home")
    public String homeSubmit(@ModelAttribute("homeFormInputs") @Valid HomeFormInputs homeFormInputs, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "home";
        } else {
            Country country = countryDao.findById(homeFormInputs.getCountryId());
            BigDecimal targetSalary = salaryService.calculateSalary(country, homeFormInputs);
            model.addAttribute("country", country);
            model.addAttribute("homeFormInputs", homeFormInputs);
            model.addAttribute("targetSalary", targetSalary);
            return "result";
        }
    }

    @Autowired
    public void setCountryDAOImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Autowired
    public void setService(SalaryService salaryService) {
        this.salaryService = salaryService;
    }
}
