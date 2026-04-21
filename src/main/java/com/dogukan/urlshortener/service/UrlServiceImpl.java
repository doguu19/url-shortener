package com.dogukan.urlshortener.service;

import com.dogukan.urlshortener.dto.UrlResponse;
import com.dogukan.urlshortener.entity.Url;
import com.dogukan.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public UrlResponse shortenUrl(String originalUrl) {

        String shortCode = UUID.randomUUID().toString().substring(0, 6);

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);
        url.setClickCount(0L);
        url.setCreatedAt(LocalDateTime.now());

        urlRepository.save(url);


        return new UrlResponse(url.getOriginalUrl(), url.getShortCode(), url.getClickCount(), url.getCreatedAt());
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Kısa kod bulunamadı!"));


        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);

        return url.getOriginalUrl();
    }

    @Override
    public UrlResponse getUrlDetails(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL bulunamadı!"));

        return new UrlResponse(url.getOriginalUrl(), url.getShortCode(), url.getClickCount(), url.getCreatedAt());
    }
}