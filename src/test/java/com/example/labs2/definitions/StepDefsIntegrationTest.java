package com.example.labs2.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class StepDefsIntegrationTest extends SpringIntegrationTest {

    @When("^the client calls /version$")
    public void the_client_issues_GET_version() throws Throwable {
        executeGet("http://localhost:8080/calculations/addition");
    }

    @And("^the client receives result is (\\d+)$")
    public void the_client_receives_result_is(final String result) {
        final var currentResult = latestResponse.getBody();
        assertThat("result is incorrect : " + latestResponse.getBody(), currentResult, is(result));
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(final int statusCode) {
        final var currentResult = status.value();
        assertThat("status is incorrect : " + status, currentResult, is(statusCode));
    }
}