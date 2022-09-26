package com.example.labs2.service;

import com.example.labs2.models.Numbers;
import org.springframework.stereotype.Service;

@Service
public class Operations {
    public static int plus(String num1, String num2) {
        return Integer.parseInt(num1) + Integer.parseInt(num2);
    }

    public static String convert(String num) {
        String res = "";
        if (num.startsWith("0b")) {
            res = Numbers.toBinary(num.substring(2));
        } else {
            res = Numbers.toDecimalString(num);
        }
        return res;
    }

    public static int calculate(Numbers numbers, String op) {
        int result = 0;
        switch (op) {
            case "+":
                result = plus(convert(numbers.getNum1()), convert(numbers.getNum2()));
                break;
        }
        return result;
    }
}
