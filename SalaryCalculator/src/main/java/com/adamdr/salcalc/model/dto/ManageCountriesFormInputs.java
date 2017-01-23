package com.adamdr.salcalc.model.dto;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

@Data
public class ManageCountriesFormInputs {
    @NotEmpty
    private Long countryId;
}
