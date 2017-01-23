package com.adamdr.salcalc.model.dao;

import com.adamdr.salcalc.model.domain.Country;

import java.util.List;

public interface CountryDao {

    List<Country> getAll();

    Country findByName(String name);

    Country findByID(Long id);

    void save(Country countryToInsert);

    void update(Country countryToUpdate);

    void delete(Country countryToRemove);
}
