package com.adamdr.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<Product> listOfAllProducts;

    public Receipt() {
        listOfAllProducts = new ArrayList<>();
    }

    public void addProduct(Product product){
        listOfAllProducts.add(product);
    }

    public List<Product> getListOfAllProducts(){
        return listOfAllProducts;
    }

    public BigDecimal getSum() {
        return listOfAllProducts.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
