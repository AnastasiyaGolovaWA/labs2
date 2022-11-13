package com.example.labs2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
public class Labs2Application {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://baeldung.com");

        System.out.println(url.getProtocol());
        SpringApplication.run(Labs2Application.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
