package com.example.search.controller;

import com.example.search.dto.GeneralResponse;
import com.example.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<GeneralResponse>> search(@RequestHeader("Authorization") String token) {
        CompletableFuture<Boolean> authFuture = searchService.authenticate(token);

        return authFuture.thenCompose(isAuthenticated -> {
            if (Boolean.TRUE.equals(isAuthenticated)) {
                CompletableFuture<String> bookAuthorFuture = searchService.callBookAuthorService();
                CompletableFuture<String> detailsFuture = searchService.callDetailsService();

                return bookAuthorFuture.thenCombine(detailsFuture, (bookAuthorResult, detailsResult) -> {
                    GeneralResponse response = new GeneralResponse(200, Instant.now().toString(), bookAuthorResult + " " + detailsResult);
                    return ResponseEntity.ok(response);
                });
            } else {
                return CompletableFuture.completedFuture(ResponseEntity.status(401).body(new GeneralResponse(401, Instant.now().toString(), "Unauthorized")));
            }
        });
    }
}
