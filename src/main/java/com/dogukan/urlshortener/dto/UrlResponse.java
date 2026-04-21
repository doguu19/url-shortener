package com.dogukan.urlshortener.dto;

import java.time.LocalDateTime;

public class UrlResponse {
    private String originalUrl;
    private String shortCode;
    private Long clickCount;
    private LocalDateTime createdAt;

    public UrlResponse(String originalUrl, String shortCode, long clickCount, LocalDateTime createdAt) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.clickCount = clickCount;
        this.createdAt = createdAt;
    }

    public String getOriginalUrl() { return originalUrl; }
    public String getShortCode() { return shortCode; }
    public long getClickCount() { return clickCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }

}
