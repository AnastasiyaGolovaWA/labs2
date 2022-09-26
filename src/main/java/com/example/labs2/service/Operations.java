package com.example.labs2.service;

import com.example.labs2.models.Numbers;
import org.springframework.stereotype.Service;

@Service
public class Operations {
    public static int plus(Numbers numbers) {
        int res = numbers.getNum1() + numbers.getNum2();
        return res;
    }
}
