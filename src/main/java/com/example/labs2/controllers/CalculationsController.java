package com.example.labs2.controllers;

import com.example.labs2.models.Numbers;
import com.example.labs2.service.CalculationsService;
import com.example.labs2.service.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/calculations")
@RestController
public class CalculationsController {

    private CalculationsService calculationsService;

    private Operations operations;

    @Autowired
    public void setCalculationsService(CalculationsService calculationsService) {
        this.calculationsService = calculationsService;
    }

    @Autowired
    public void setOperations(Operations operations) {
        this.operations = operations;
    }

    @GetMapping(path = "/addition")
    public String getAddition(Numbers numbers) {
        return String.valueOf(operations.plus(numbers));
    }
}
