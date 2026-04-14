package com.dogukan.urlshortener.service;

import com.dogukan.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import com.dogukan.urlshortener.entity.Url;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String originalUrl) {

        if (originalUrl == null || originalUrl.trim().isEmpty() || !originalUrl.startsWith("http")) {
            throw new RuntimeException("Geçersiz URL! Lütfen http veya https ile başlayan bir link girin.");
        }


        String shortCode = UUID.randomUUID().toString().substring(0 , 6);
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);


        url.setClickCount(0L);
        url.setCreatedAt(LocalDateTime.now());

        urlRepository.save(url);

        return "http://localhost:8080/" + shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Kısa kod bulunamadı!"));


        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);

        return url.getOriginalUrl();
    }


    public Url getUrlEntity(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL bulunamadı!"));
    }
}