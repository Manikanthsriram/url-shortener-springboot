package com.example.urlshortener.model;

public class UrlMapping {

    private String shortKey;
    private String originalUrl;

    public UrlMapping(String shortKey, String originalUrl) {
        this.shortKey = shortKey;
        this.originalUrl = originalUrl;
    }

    public String getShortKey() {
        return shortKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
