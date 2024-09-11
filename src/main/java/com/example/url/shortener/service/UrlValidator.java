package com.example.url.shortener.service;

import com.example.url.shortener.exception.ValidationFailedException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class UrlValidator {
    public void validate(String url) {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        try {
            HttpResponse<Void> response = client.send(
                    HttpRequest.newBuilder(new URI(url)).build(),
                    HttpResponse.BodyHandlers.discarding());
            int responseCode = response.statusCode();

            if (responseCode >= 400) {
                throw new ValidationFailedException(
                        String.format("invalid response http code %d from url %s", responseCode, url));
            }

        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new ValidationFailedException(
                    String.format("Error occurred %s while sending request to %s", e, url), e);
        }
    }
}
