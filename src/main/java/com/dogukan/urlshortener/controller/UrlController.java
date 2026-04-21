package com.dogukan.urlshortener.controller;

import com.dogukan.urlshortener.dto.ShortenRequest;
import com.dogukan.urlshortener.dto.UrlResponse;
import com.dogukan.urlshortener.service.UrlService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@Valid @RequestBody ShortenRequest request) {
        UrlResponse response = urlService.shortenUrl(request.getUrl());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode) {
        String originalUrl = urlService.getOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }

    @GetMapping("/info/{shortCode}")
    public ResponseEntity<UrlResponse> getUrlDetails(@PathVariable String shortCode) {
        return ResponseEntity.ok(urlService.getUrlDetails(shortCode));
    }
}