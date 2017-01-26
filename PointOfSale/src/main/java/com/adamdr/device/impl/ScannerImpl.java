package com.adamdr.device.impl;

import com.adamdr.device.ScannerDevice;

import java.util.Scanner;

public class ScannerImpl implements ScannerDevice {
    public String read() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Barcode: ");
        return scanner.nextLine();
    }
}
