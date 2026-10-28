
package com.example;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println("5 + 3 = " + calculator.add(5, 3));
        System.out.println("5 * 3 = " + calculator.multiply(5, 3));
    }
}
