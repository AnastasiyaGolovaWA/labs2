package com.example.labs2.service;

import com.example.labs2.models.Numbers;
import org.springframework.stereotype.Service;

@Service
public class Operations {
    public static int plus(Numbers numbers) {
        int res = numbers.getNum1() + numbers.getNum2();
        return res;
    }

    public static int minus(Numbers numbers) {
        int res = numbers.getNum1() - numbers.getNum2();
        return res;
    }

    public static int multiplication(Numbers numbers) {
        int res = numbers.getNum1() * numbers.getNum2();
        return res;
    }

    public static double division(Numbers numbers) {
        double res = (double)numbers.getNum1() / numbers.getNum2();
        return res;
    }

    public static Numbers convert(int num1, int num2, int system) {
        Numbers numbers = new Numbers();
        if (system == 2) {
            numbers.setNum1(Numbers.toBinary(num1));
            numbers.setNum2(Numbers.toBinary(num2));
        }
        if (system == 8) {
            numbers.setNum1(Numbers.toOctalString(num1));
            numbers.setNum2(Numbers.toOctalString(num2));
        }
        if (system == 16) {
            numbers.setNum1(Numbers.toHexString(num1));
            numbers.setNum2(Numbers.toHexString(num2));
        }
        if (system == 10) {
            numbers.setNum1(num1);
            numbers.setNum2(num2);
        }
        return numbers;
    }

    public static double calculate(Numbers numbers, char op) {
        double result = 0.0;
        switch (op) {
            case '+':
                result = plus(convert(numbers.getNum1(), numbers.getNum2(), numbers.getSystem()));
                break;
            case '-':
                result = minus(convert(numbers.getNum1(), numbers.getNum2(), numbers.getSystem()));
                break;
            case '*':
                result = multiplication(convert(numbers.getNum1(), numbers.getNum2(), numbers.getSystem()));
                break;
            case '/':
                result = division(convert(numbers.getNum1(), numbers.getNum2(), numbers.getSystem()));
                break;
        }
        return result;
    }
}
