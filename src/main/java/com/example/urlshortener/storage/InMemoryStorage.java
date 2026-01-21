package com.example.urlshortener.storage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.urlshortener.model.UrlMapping;

@Component
public class InMemoryStorage {

    private Map<String, UrlMapping> shortToUrl = new HashMap<>();

    public void save(UrlMapping mapping) {
        shortToUrl.put(mapping.getShortKey(), mapping);
    }

    public UrlMapping findByShortKey(String shortKey) {
        return shortToUrl.get(shortKey);
    }
}
