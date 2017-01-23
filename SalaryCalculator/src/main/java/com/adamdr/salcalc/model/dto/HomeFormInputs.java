package com.adamdr.salcalc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeFormInputs {
    @NotNull
    @Digits(integer = 9, fraction = 2, message = "Must be a digits with max size = 9 and fraction = 2")
    @Min(value = 0, message="The value must be positive")
    private String salary = "0.0";
    private Long countryId;
}
