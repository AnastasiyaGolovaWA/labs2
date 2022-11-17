package com.example.labs2.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Numbers {
    private int num1;
    private int num2;
    private int system;

    public static int toBinary(int num) {
        int intBits = Integer.parseInt(String.valueOf(num), 2);
        return intBits;
    }

    public static int toOctalString(int num) {
        int intBits = Integer.parseInt(String.valueOf(num), 8);
        return intBits;
    }

    public static int toHexString(int num) {
        int intBits = Integer.parseInt(String.valueOf(num), 16);
        return intBits;
    }
}
