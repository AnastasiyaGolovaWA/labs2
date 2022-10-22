package com.example.labs2.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class StepDefsIntegrationTest extends SpringIntegrationTest {

    @When("i execute endpoint is {string}")
    public void theClientCallsEndpointIs(final String path) throws IOException {
        executeGet("http://localhost:8080/calculations/" + path);
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
}