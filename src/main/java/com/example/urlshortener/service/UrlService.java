package com.example.urlshortener.service;

import org.springframework.stereotype.Service;

import com.example.urlshortener.model.UrlMapping;
import com.example.urlshortener.storage.InMemoryStorage;

@Service
public class UrlService {

    private final InMemoryStorage storage;
    private long counter = 1;

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public UrlService(InMemoryStorage storage) {
        this.storage = storage;

        // preload one mapping
        storage.save(new UrlMapping("a", "https://www.google.com"));
        counter = 2;
    }

    public String shortenUrl(String originalUrl) {
        String shortKey = encodeBase62(counter);
        counter++;

        UrlMapping mapping = new UrlMapping(shortKey, originalUrl);
        storage.save(mapping);

        return shortKey;
    }

    public String getOriginalUrl(String shortKey) {
        UrlMapping mapping = storage.findByShortKey(shortKey);
        return mapping != null ? mapping.getOriginalUrl() : "https://www.example.com";
    }

    private String encodeBase62(long num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int rem = (int) (num % 62);
            sb.append(BASE62.charAt(rem));
            num = num / 62;
        }

        return sb.reverse().toString();
    }
}
