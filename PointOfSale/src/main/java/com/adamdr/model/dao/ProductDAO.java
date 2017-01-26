package com.adamdr.model.dao;

import com.adamdr.model.Product;

public interface ProductDAO {

    Product findByName(String name);

    Product findByBarcode(String barcode);

    void insertProduct(Product product);

    void decreaseQuantity(Product product);

    void deleteProduct(Product product);
}
