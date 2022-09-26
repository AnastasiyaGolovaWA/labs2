package com.example.labs2.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Numbers {
    private String num1;
    private String num2;

    public static String toBinary(String num) {
        return String.valueOf(Integer.parseInt(num, 2));
    }

    public static String toOctalString(String num) {
        return String.valueOf(Integer.parseInt(num, 8));
    }

    public static String toHexString(String num) {
        return String.valueOf(Integer.parseInt(num, 16));
    }

    public static String toDecimalString(String num) {
        return String.valueOf(Integer.parseInt(num));
    }
}
