package com.example.labs2.definitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.labs2.Labs2Application;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@CucumberContextConfiguration
@SpringBootTest(classes = Labs2Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    static ResponseResults latestResponse = null;

    @Autowired
    protected RestTemplate restTemplate;

    void executeGet(String url) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("num1", "{num1}")
                .queryParam("num2", "{num2}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("num1", "10001");
        params.put("num2", "10012");

        RestTemplate restOperations = new RestTemplate();
        HttpEntity<String> response = restOperations.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );
    }

    void executePost() throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate
                .execute("http://localhost:8082/baeldung", HttpMethod.POST, requestCallback, response -> {
                    if (errorHandler.hadError) {
                        return (errorHandler.getResults());
                    } else {
                        return (new ResponseResults(response));
                    }
                });
    }

    private class ResponseResultErrorHandler implements ResponseErrorHandler {
        private ResponseResults results = null;
        private Boolean hadError = false;

        private ResponseResults getResults() {
            return results;
        }

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            hadError = response.getRawStatusCode() >= 400;
            return hadError;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            results = new ResponseResults(response);
        }
    }
}