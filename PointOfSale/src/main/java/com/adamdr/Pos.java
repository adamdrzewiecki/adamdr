package com.adamdr;

import com.adamdr.device.Display;
import com.adamdr.device.Printer;
import com.adamdr.device.ScannerDevice;
import com.adamdr.device.impl.LCDDisplay;
import com.adamdr.device.impl.PrinterImpl;
import com.adamdr.device.impl.ScannerImpl;
import com.adamdr.model.dao.ProductDAO;
import com.adamdr.model.dao.ProductDAOImpl;
import com.adamdr.model.Product;
import com.adamdr.model.Receipt;

public class Pos {

    private ScannerDevice scanner = new ScannerImpl();
    private ProductDAO productDao = new ProductDAOImpl();

    private Printer printer = new PrinterImpl();
    private Display lcdDisplay = new LCDDisplay();

    private Receipt receipt;
    private Boolean end;

    public void start() {

        receipt = new Receipt();
        end = false;
        String barcode;

        do {
            barcode = scanner.read();
            switch (barcode) {
                case "exit":
                    end = true;
                    break;
                case "":
                    lcdDisplay.displayMessage("Invalid barcode");
                    break;
                default:
                    sellProduct(productDao.findByBarcode(barcode));
            }
        } while (!end);

        printer.printReceipt(receipt);
        lcdDisplay.displayMessage(receipt.getSum().toString());
   }

    private void sellProduct(Product product){
        if (product != null) {
            productDao.decreaseQuantity(product);
            receipt.addProduct(product);
            lcdDisplay.displayProduct(product);
        } else {
            lcdDisplay.displayMessage("Product not found");
        }
    }
}
