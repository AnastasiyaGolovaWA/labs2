package com.example.labs2.definitions;

import com.example.labs2.Labs2Application;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(classes = Labs2Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    static HttpEntity<String> latestResponse = null;
    static HttpStatus status = null;

    @Autowired
    protected RestTemplate restTemplate;

    void executeGet(String url, String num1, String num2) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("num1", "{num1}")
                .queryParam("num2", "{num2}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("num1", num1);
        params.put("num2", num2);

        HttpEntity<String> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );
        status = ((ResponseEntity<String>) response).getStatusCode();
        latestResponse = response;
    }
}