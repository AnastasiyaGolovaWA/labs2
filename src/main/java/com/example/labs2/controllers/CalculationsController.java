package com.example.labs2.controllers;

import com.example.labs2.models.Calculations;
import com.example.labs2.models.Numbers;
import com.example.labs2.models.NumbersDTO;
import com.example.labs2.models.enums.OperationsEnum;
import com.example.labs2.repository.CalculationsRepository;
import com.example.labs2.service.CalculationsService;
import com.example.labs2.service.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.labs2.service.Operations.getSystem;

@RequestMapping(value = "/calculations")
@RestController
public class CalculationsController {

    private CalculationsService calculationsService;

    private CalculationsRepository calculationsRepository;

    private Operations operations;

    public void createRecordToDatabase(Numbers numbers, OperationsEnum op) {
        Calculations calculations = new Calculations();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        calculations.setNumberOne(numbers.getNum1());
        calculations.setNumberTwo(numbers.getNum2());
        calculations.setNumberSystemOne(getSystem(numbers.getNum1()));
        calculations.setNumberSystemTwo(getSystem(numbers.getNum2()));
        calculations.setDateCreated(date);
        calculations.setOperationsEnum(op);
        calculationsService.save(calculations);
    }

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
    public int getAddition(Numbers numbers) {
        createRecordToDatabase(numbers, OperationsEnum.ADDITION);
        return Operations.calculate(numbers, "+");
    }

    @GetMapping(path = "/subtraction")
    public int getSubtraction(Numbers numbers) {
        createRecordToDatabase(numbers, OperationsEnum.SUBTRACTION);
        return Operations.calculate(numbers, "-");
    }

    @GetMapping(path = "/division")
    public int getDivision(Numbers numbers) {
        createRecordToDatabase(numbers, OperationsEnum.DIVISION);
        return Operations.calculate(numbers, "/");
    }

    @GetMapping(path = "/multiplication")
    public int getMultiplication(Numbers numbers) {
        createRecordToDatabase(numbers, OperationsEnum.MULTIPLICATION);
        return Operations.calculate(numbers, "*");
    }

    @GetMapping("/findByParameters")
    public List<Calculations> findByParameters(NumbersDTO numbersDTO) {
        return calculationsRepository.findByParameters(numbersDTO.getNumberSystemOne(), numbersDTO.getNumberSystemTwo(), numbersDTO.getOperationName());
    }
}
