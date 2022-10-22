package com.example.labs2.runner;

import com.example.labs2.Labs2Application;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-report.html"},
        glue = { "com.example.labs2.definitions"},
        features = "src/test/resources/features")
@CucumberContextConfiguration
@SpringBootTest(classes = Labs2Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberIntegrationTest {
}