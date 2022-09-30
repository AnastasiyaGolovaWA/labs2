package com.example.labs2;

import com.example.labs2.models.Calculations;
import com.example.labs2.models.Numbers;
import com.example.labs2.repository.CalculationsRepository;
import com.example.labs2.service.CalculationsService;
import com.example.labs2.service.Operations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(initializers = {Labs2ApplicationTests.Initializer.class})
@Testcontainers
public class Labs2ApplicationTests {
    @Autowired
    CalculationsRepository calculationsRepository;

    @Autowired
    CalculationsService calculationsService;
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("numbers")
            .withUsername("postgres")
            .withPassword("root")
            .withInitScript("database/V1__create.sql");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()

            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    @Transactional
    public void calculatorByParameters() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2022-09-20");
        Date endDate = df.parse("2022-10-20");
        List<Calculations> calculations = calculationsRepository.findByParameters(2, 10, "ADDITION", startDate, endDate);
        assertEquals(1, calculations.size());
    }

    @Test
    @Transactional
    public void calculatorWithoutOperation() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2022-09-20");
        Date endDate = df.parse("2022-10-20");
        List<Calculations> calculations = calculationsRepository.findByParameters(2, 10, "", startDate, endDate);
        assertEquals(4, calculations.size());
    }

    @Test
    @Transactional
    public void calculatorFindByDates() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2022-09-20");
        Date endDate = df.parse("2022-10-20");
        List<Calculations> calculations = calculationsRepository.findByParameters(0, 0, "", startDate, endDate);
        assertEquals(16, calculations.size());
    }

    @Test
    @Transactional
    public void calculatorFindByDatesAndDivisionOperation() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2022-09-20");
        Date endDate = df.parse("2022-10-20");
        List<Calculations> calculations = calculationsRepository.findByParameters(0, 0, "DIVISION", startDate, endDate);
        assertEquals(4, calculations.size());
    }

    @Test
    @Transactional
    public void createPlus() {
        Numbers numbers = new Numbers();
        numbers.setNum1("0b101");
        numbers.setNum2("0b111");
        var res = Operations.calculate(numbers, "+");
        assertEquals(12, res);
    }

    @Test
    @Transactional
    public void createDivision() {
        Numbers numbers = new Numbers();
        numbers.setNum1("0x152");
        numbers.setNum2("0b11");
        var res = Operations.calculate(numbers, "/");
        assertEquals(112, res);
    }

    @Test
    @Transactional
    public void createMult() {
        Numbers numbers = new Numbers();
        numbers.setNum1("0x152");
        numbers.setNum2("0b11");
        var res = Operations.calculate(numbers, "*");
        assertEquals(1014, res);
    }

    @Test
    @Transactional
    public void createSubtract() {
        Numbers numbers = new Numbers();
        numbers.setNum1("0x152");
        numbers.setNum2("0b11");
        var res = Operations.calculate(numbers, "-");
        assertEquals(335, res);
    }

}