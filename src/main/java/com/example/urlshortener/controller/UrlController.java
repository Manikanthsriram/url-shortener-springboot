package com.example.urlshortener.controller;

import com.example.urlshortener.service.UrlService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.urlshortener.model.ShortenRequest;


@Controller
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }
@GetMapping("/")
public String home() {
    return "forward:/index.html";
}

    // 🔹 create short URL
   @PostMapping("/shorten")
@ResponseBody
public String shorten(@RequestBody ShortenRequest request) {
    String shortKey = urlService.shortenUrl(request.getUrl());
    return "/r/" + shortKey;


}
 // 🔹 redirect endpoint
    @GetMapping("/r/{shortKey}")
public String redirect(@PathVariable String shortKey) {
    String originalUrl = urlService.getOriginalUrl(shortKey);
    return "redirect:" + originalUrl;
}

}
