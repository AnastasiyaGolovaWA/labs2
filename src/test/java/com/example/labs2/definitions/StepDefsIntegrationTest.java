package com.example.labs2.definitions;

import com.example.labs2.models.Numbers;
import com.example.labs2.repository.CalculationsRepository;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import io.cucumber.java.Before;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class StepDefsIntegrationTest extends SpringIntegrationTest {
    @Autowired
    CalculationsRepository calculationsRepository;

    private final ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new JSR310Module());

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object defaultTransformer(Object fromValue, Type toValueType) {
        JavaType javaType = objectMapper.constructType(toValueType);
        return objectMapper.convertValue(fromValue, javaType);
    }

    @When("i execute endpoint is {string} with num1 is {string} and num2 is {string}")
    public void theClientCallsEndpointIs(final String path, final String num1, final String num2) throws IOException {
        executeGet("http://localhost:8080/calculations/" + path, num1, num2);
    }

    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(final int statusCode) {
        final var currentResult = status.value();
        assertThat("status is incorrect : " + status, currentResult, is(statusCode));
    }

    @And("the client receives result is {string}")
    public void theClientReceivesResultIs(final String result) {
        final var currentResult = latestResponse.getBody();
        assertThat("result is incorrect : " + latestResponse.getBody(), currentResult, is(result));
    }

    @Given("test get parameters for class")
    public void the_price_list_for_an_international_coffee_shop(final List<Numbers> arg) throws Throwable {
        for (final Numbers numbers : arg) {
            System.out.print(numbers.getNum1());
            System.out.print(numbers.getNum2());
        }
    }

    @Before
    public void cleanDatabase() {
        calculationsRepository.clear();
    }
}