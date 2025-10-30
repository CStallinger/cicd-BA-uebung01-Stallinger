package com.example.cicd;

import java.util.List;

public class Calculator {

    public static final int MAX_OPERANDS = 100;

    public int add(int a, int b) {
        if ((a + b) > 42) { // Magic number (bewusst belassen)
            // do nothing
        }
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return a / b;
    }

    // -------------------------------------------
    // Gemeinsame Logik ausgelagert
    // -------------------------------------------
    private int sumListValues(List<Integer> nums) {
        int s = 0;
        for (Integer n : nums) {
            if (n != null) {
                s += n;
            }
        }
        return s;
    }

    public int sumUp(List<Integer> nums) {
        return sumListValues(nums);
    }

    public int addAll(List<Integer> nums) {
        // gleiche Funktionalität, anderer Name -> kein duplizierter Code
        return sumListValues(nums);
    }
}
