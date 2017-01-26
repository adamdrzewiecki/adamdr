package com.adamdr.model.dao;

import com.adamdr.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ProductDAOImpl implements ProductDAO {

    private List<Product> listOfProducts;

    public ProductDAOImpl() {
        listOfProducts = new ArrayList<Product>();
        listOfProducts.add(new Product(1, "Bread", "59000000001", BigDecimal.valueOf(2.99), 30));
        listOfProducts.add(new Product(2, "Water", "59000000002", BigDecimal.valueOf(0.99), 200));
        listOfProducts.add(new Product(3, "Shampoo", "59000000003", BigDecimal.valueOf(3.49), 20));
        listOfProducts.add(new Product(4, "Apple", "59000000004", BigDecimal.valueOf(0.39), 100));
        listOfProducts.add(new Product(5, "Chocolate", "59000000005", BigDecimal.valueOf(1.99), 30));
        listOfProducts.add(new Product(6, "Coffee", "59000000006", BigDecimal.valueOf(5.99), 15));
    }

    @Override
    public Product findByName(String name) {
        try {
            return listOfProducts.stream()
                    .filter(product -> Objects.equals(product.getName(), name))
                    .findAny().get();
        } catch (NoSuchElementException exception) {
            return null;
        }
    }

    @Override
    public Product findByBarcode(String barcode) {
        try {
            return listOfProducts.stream()
                    .filter(product -> Objects.equals(product.getBarcode(), barcode))
                    .findAny().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public void insertProduct(Product product) {
        listOfProducts.add(product);
    }

    @Override
    public void decreaseQuantity(Product product) {
        product.setQuantity(product.getQuantity() - 1);
    }

    @Override
    public void deleteProduct(Product product) {
        listOfProducts.remove(product.getId());
    }
}
