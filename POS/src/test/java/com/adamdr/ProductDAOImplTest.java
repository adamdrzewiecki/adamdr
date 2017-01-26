package com.adamdr;

import static org.assertj.core.api.Assertions.*;

import com.adamdr.model.dao.ProductDAO;
import com.adamdr.model.dao.ProductDAOImpl;
import com.adamdr.model.Product;
import org.junit.Test;

public class ProductDAOImplTest {
    
    ProductDAO productDAO = new ProductDAOImpl();

    @Test
    public void shouldReturnNullWhenPastNullToFindByName () {
        assertThat(productDAO.findByName(null)).isNull();
    }

    @Test
    public void shouldReturnNullWhenPastNullToFindByBarcode () {
        assertThat(productDAO.findByBarcode(null)).isNull();
    }

    @Test
    public void shouldReturnNullWhenNotPastNullToFindByName () {
        assertThat(productDAO.findByName("Bread")).isNotNull();
    }

    @Test
    public void shouldReturnNullWhenNotPastNullToFindByBarcode () {
        assertThat(productDAO.findByBarcode("59000000005")).isNotNull();
    }
    
    @Test
    public void testDecreaseQuantity () {
        Product product = productDAO.findByName("Bread");
        assertThat(product.getQuantity()).isEqualTo(30);
        productDAO.decreaseQuantity(product);
        assertThat(product.getQuantity()).isEqualTo(29);
    }
}