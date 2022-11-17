package com.example.labs2.controllers;

import com.example.labs2.models.Calculations;
import com.example.labs2.models.NumberDate;
import com.example.labs2.models.Numbers;
import com.example.labs2.models.NumbersDTO;
import com.example.labs2.repository.CalculationsRepository;
import com.example.labs2.service.CalculationsService;
import com.example.labs2.service.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping(value = "/calculations")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CalculationsController {

    private CalculationsService calculationsService;

    private CalculationsRepository calculationsRepository;

    private Operations operations;



    @Autowired
    public void setCalculationsService(CalculationsService calculationsService) {
        this.calculationsService = calculationsService;
    }

    @Autowired
    public void setCalculationsRepository(CalculationsRepository calculationsRepository) {
        this.calculationsRepository = calculationsRepository;
    }

    @Autowired
    public void setOperations(Operations operations) {
        this.operations = operations;
    }

    @GetMapping(path = "/addition")
    public double getAddition(Numbers numbers) {
        return Operations.calculate(numbers, '+');
    }

    @GetMapping(path = "/subtraction")
    public double getSubtraction(Numbers numbers) {
        return Operations.calculate(numbers, '-');
    }

    @GetMapping(path = "/division")
    public double getDivision(Numbers numbers) {
        return Operations.calculate(numbers, '/');
    }

    @GetMapping(path = "/multiplication")
    public double getMultiplication(Numbers numbers) {
        return Operations.calculate(numbers, '*');
    }

    @GetMapping(path = "/findByParameters")
    public List<Calculations> findByParameters(NumbersDTO numbersDTO) {
        return calculationsRepository.findByParameters(numbersDTO.getNumberSystemOne(), numbersDTO.getNumberSystemTwo(), numbersDTO.getOperationName(), numbersDTO.getStartDate(), numbersDTO.getEndDate());
    }

    @GetMapping(path = "/findByDate")
    public List<Calculations> findByDate(NumberDate number) {
        return calculationsRepository.findByDate(number.getStartDate(), number.getEndDate());
    }
}
