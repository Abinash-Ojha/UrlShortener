package com.urlshortener2.controller;

import com.urlshortener2.model.URLs;
import com.urlshortener2.service.RateLimitorService;
import com.urlshortener2.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/url")
public class UrlController {
    UrlService urlService;
    UrlController(UrlService urlService){
        this.urlService=urlService;
    }
    @Autowired
    private RateLimitorService rateLimitorService;


    @PostMapping("/shortenUrl")
    public ResponseEntity<String> getShortUrl(@RequestBody Map<String,String> request){
        String origionalUrl =request.get("longUrl");
        String shortUrl=urlService.getShortendUrl(origionalUrl);
        return ResponseEntity.ok(shortUrl);
    }
    @GetMapping("/{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletResponse response,HttpServletRequest request) throws IOException {
        String key=request.getRemoteAddr();
        if(!rateLimitorService.isAllowed(key)){
            response.sendError(429,"Too Many Requests");
            return;
        }
       String origionalUrl=urlService.getOrigionalUrl(shortUrl);
        response.sendRedirect(origionalUrl);


    }
    @GetMapping("/test/{shortUrl}")
    public ResponseEntity<?> getLongUrl(@PathVariable String shortUrl, HttpServletRequest request){
        String key=request.getRemoteAddr();
        if(!rateLimitorService.isAllowed(key)){
            return ResponseEntity.status(429).body("Too Many requests");
        }

        String longUrl=urlService.getOrigionalUrl(shortUrl);
        return ResponseEntity.ok(longUrl);
    }

}
