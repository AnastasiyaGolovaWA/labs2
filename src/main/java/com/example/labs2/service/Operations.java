package com.example.labs2.service;

import com.example.labs2.models.Numbers;
import org.springframework.stereotype.Service;

@Service
public class Operations {
    public static int plus(String num1, String num2) {
        return Integer.parseInt(num1) + Integer.parseInt(num2);
    }

    public static int minus(String num1, String num2) {
        return Integer.parseInt(num1) - Integer.parseInt(num2);
    }

    public static int multiplication(String num1, String num2) {
        return Integer.parseInt(num1) * Integer.parseInt(num2);
    }

    public static int division(String num1, String num2) {
        return Integer.parseInt(num1) / Integer.parseInt(num2);
    }

    public static int getSystem(String num) {
        int system = 0;
        String[] numbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        if (num.startsWith("0b") || num.startsWith("0B")) {
            system = 2;
        }
        if (num.startsWith("0x") || num.startsWith("0X")) {
            system = 16;
        }
        if (!num.contains("b") && !num.contains("x") && !num.contains("B") && !num.contains("X")) {
            system = 8;
        }
        for (String number : numbers) {
            if (num.startsWith(number)) {
                system = 10;
            }
        }
        return system;
    }

    public static String convert(String num) {
        String res = "";
        String[] numbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        if (num.startsWith("0b") || num.startsWith("0B")) {
            res = Numbers.toBinary(num.substring(2));
        }
        if (num.startsWith("0x") || num.startsWith("0X")) {
            res = Numbers.toHexString(num.substring(2));
        }
        if (!num.contains("b") && !num.contains("x") && !num.contains("B") && !num.contains("X")) {
            res = Numbers.toOctalString(num.substring(1));
        }
        for (String number : numbers) {
            if (num.startsWith(number)) {
                res = Numbers.toDecimalString(num);
            }
        }
        return res;
    }

    public static int calculate(Numbers numbers, String op) {
        int result = 0;
        switch (op) {
            case "+":
                result = plus(convert(numbers.getNum1()), convert(numbers.getNum2()));
                break;
            case "-":
                result = minus(convert(numbers.getNum1()), convert(numbers.getNum2()));
                break;
            case "*":
                result = multiplication(convert(numbers.getNum1()), convert(numbers.getNum2()));
                break;
            case "/":
                result = division(convert(numbers.getNum1()), convert(numbers.getNum2()));
                break;
        }
        return result;
    }
}
