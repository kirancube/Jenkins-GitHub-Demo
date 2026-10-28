
package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void testAddition() {
        Calculator calculator = new Calculator();
        assertEquals(8, calculator.add(5, 3));
    }

    @Test
    void testMultiplication() {
        Calculator calculator = new Calculator();
        assertEquals(15, calculator.multiply(5, 3));
    }
}
