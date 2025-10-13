package com.example.cicd;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Hinweise:
 * - Ergänze weitere Testfälle (z. B. negative Zahlen, große Werte)
 * - Diskutiere das Verhalten von divide(… , 0) und entscheide in Ü2, ob Exception sinnvoller ist
 */
class CalculatorTest {

    @Test
    void add_shouldSumTwoIntegers() {
        Calculator c = new Calculator();
        assertEquals(5, c.add(2, 3));
        // Erweiterte Tests:
        assertEquals(-1, c.add(2, -3));          // negative Zahl
        assertEquals(-5, c.add(-2, -3));         // beide negativ
        assertEquals(Integer.MIN_VALUE, c.add(Integer.MAX_VALUE, 1)); // Überlauf (aktuelles Verhalten)
    }

    @Test
    void divide_byZero_shouldReturnZero_currentBehavior() {
        Calculator c = new Calculator();
        // Aktuelles (schlechtes) Verhalten absichtlich bestätigt – Refactoring in Ü2
        assertEquals(0, c.divide(10, 0));
    }

    @Test
    void divide_shouldHandleNegativeAndTruncation() {
        Calculator c = new Calculator();
        // Integer-Division rundet gegen 0
        assertEquals(3, c.divide(7, 2));
        assertEquals(-3, c.divide(-7, 2));
        assertEquals(-3, c.divide(7, -2));
        assertEquals(0, c.divide(1, 3)); // Trunkierung Richtung 0
    }

    @Test
    void sum_methods_shouldProduceSameResult() {
        Calculator c = new Calculator();
        List<Integer> nums = Arrays.asList(1, 2, 3, null, 4);
        assertEquals(c.sumUp(nums), c.addAll(nums));
    }

    @Test
    void sum_methods_shouldIgnoreNulls() {
        Calculator c = new Calculator();
        List<Integer> nums = Arrays.asList(1, null, -2, 3, null);
        assertEquals(2, c.sumUp(nums));   // 1 + (-2) + 3 = 2
        assertEquals(2, c.addAll(nums));  // identisch
    }
}
