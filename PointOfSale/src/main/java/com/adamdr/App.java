package com.adamdr;

import java.io.IOException;

public class App {

    private static Pos pos = new Pos();

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome");
        pos.start();
        System.out.println("Goodbye");
        System.in.read();
    }
}