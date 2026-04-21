package com.dogukan.urlshortener.service;

import com.dogukan.urlshortener.dto.UrlResponse;

public interface UrlService {
    UrlResponse shortenUrl(String originalUrl);
    String getOriginalUrl(String shortCode);
    UrlResponse getUrlDetails(String shortCode);
}