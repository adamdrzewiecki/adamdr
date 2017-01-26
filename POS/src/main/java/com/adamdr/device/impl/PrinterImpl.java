package com.adamdr.device.impl;

import com.adamdr.model.Receipt;
import com.adamdr.device.Printer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PrinterImpl implements Printer {
    private Logger printer = Logger.getLogger(PrinterImpl.class.getName());

    @Override
    public void printReceipt(Receipt receipt) {
        printer.log(Level.INFO, "\nReceipt:");
        receipt.getListOfAllProducts()
                .forEach(product -> printer.log(Level.INFO, "Product name:   " + product.getName() + ", Price:   " + product.getPrice()));
        printer.log(Level.INFO, "SUM: " + receipt.getSum());
    }
}
