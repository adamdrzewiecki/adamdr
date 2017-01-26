package com.adamdr.device.impl;

import com.adamdr.model.Product;
import com.adamdr.device.Display;

import java.util.logging.*;

public class LCDDisplay implements Display {
    private Logger display = Logger.getLogger(LCDDisplay.class.getName());

    @Override
    public void displayProduct(Product product) {
        display.log(Level.INFO, "Product name:   " + product.getName() + ", Price:   " + product.getPrice());
    }

    @Override
    public void displayMessage(String message) {
        display.log(Level.INFO, message);
    }
}
