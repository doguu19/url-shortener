package com.dogukan.urlshortener.dto;


import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class ShortenRequest {
    @NotBlank(message = "URL boş olamaz")
    @URL(message = "Geçersiz URL formatı")
    private String url;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}