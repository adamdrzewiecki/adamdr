package com.adamdr.salcalc.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, message = "Minimum size: 2")
    String name;
    @Size(min = 3, max = 3, message = "Requied size: 3")
    @Pattern(regexp = "[A-Za-z]+", message = "Just letters")
    String currency;
    @NotNull(message = "Field cannot be left blank")
    @Column(name = "income_tax")
    @Digits(integer = 9, fraction = 2)
    Integer incomeTax;
    @NotNull(message = "Field cannot be left blank")
    @Column(name = "fixed_costs")
    @Digits(integer = 9, fraction = 2)
    BigDecimal fixedCosts;

    public Country(String name, String currency, Integer incomeTax, BigDecimal fixedCosts) {
        this.name = name;
        this.currency = currency;
        this.incomeTax = incomeTax;
        this.fixedCosts = fixedCosts;
    }
}
