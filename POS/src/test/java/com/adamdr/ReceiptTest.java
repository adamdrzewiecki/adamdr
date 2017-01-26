package com.adamdr;

import static org.assertj.core.api.Assertions.*;

import com.adamdr.model.Product;
import com.adamdr.model.Receipt;
import org.junit.Test;

import java.math.BigDecimal;

public class ReceiptTest {
    private Receipt receipt = new Receipt();

    @Test
    public void shouldSumProperly() {
        receipt.addProduct(new Product(6, "Coffee", "59000000006", BigDecimal.valueOf(5.99), 15));
        receipt.addProduct(new Product(6, "Coffee", "59000000006", BigDecimal.valueOf(5.99), 15));
        assertThat(receipt.getSum()).isEqualTo(BigDecimal.valueOf(11.98));
    }
}
