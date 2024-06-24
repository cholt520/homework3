package com.example.search.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class SearchService {

    private RestTemplate restTemplate;

    @Value("${spring.cloud.config.bookApi}")
    private String bookApi;

    @Value("${spring.cloud.config.detailPortApi}")
    private String detailPortApi;

    @Value("${spring.cloud.config.security}")
    private String authentication;

    @Autowired
    public SearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CompletableFuture<Boolean> authenticate(String token) {
        return CompletableFuture.supplyAsync(() -> {
            // Replace with actual security service URL
            String url = authentication + "/authenticate?token=" + token;
            return restTemplate.getForObject(url, Boolean.class);
        });
    }

    @HystrixCommand(fallbackMethod = "fallbackBookAuthorService")
    public CompletableFuture<String> callBookAuthorService() {
        return CompletableFuture.supplyAsync(() -> {
            return restTemplate.getForObject(bookApi, String.class);
        });
    }

    @HystrixCommand(fallbackMethod = "fallbackDetailsService")
    public CompletableFuture<String> callDetailsService() {
        return CompletableFuture.supplyAsync(() -> {
            return restTemplate.getForObject(detailPortApi, String.class);
        });
    }

    public CompletableFuture<String> fallbackBookAuthorService() {
        return CompletableFuture.supplyAsync(() -> "Fallback Book/Author Service");
    }

    public CompletableFuture<String> fallbackDetailsService() {
        return CompletableFuture.supplyAsync(() -> "Fallback Details Service");
    }
}
