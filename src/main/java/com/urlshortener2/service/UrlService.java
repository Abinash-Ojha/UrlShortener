package com.urlshortener2.service;

import com.urlshortener2.model.URLs;
import com.urlshortener2.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {
    private SecureRandom random=new SecureRandom();
    private URLRepository urlRepository;
    @Autowired
    private StringRedisTemplate redisTemplate;
    UrlService(URLRepository urlRepository){
        this.urlRepository=urlRepository;
    }
    public String shortenUrl(){
        String chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<7;i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
    public String getShortendUrl(String longUrl){
        String shortUrl=shortenUrl();
        while(urlRepository.existsByShortUrl(shortUrl)){
            shortUrl=shortenUrl();
        }
        URLs urls=new URLs();
        urls.setOrigionalUrl(longUrl);
        urls.setShortUrl(shortUrl);
        urls.setCreatedAt(LocalDateTime.now());
        urlRepository.save(urls);
        return urls.getShortUrl();
    }
    public String getOrigionalUrl(String shortUrl){
        String cachedUrl=redisTemplate.opsForValue().get(shortUrl);
        if(cachedUrl!=null){
            System.out.println("🔥 Cache Hit: Value retrieved from Redis");
            return cachedUrl+" From Cache";
        }
        System.out.println("❌ Cache Miss: Fetching from DB");
       Optional<URLs> urls=urlRepository.findByShortUrl(shortUrl);

       URLs url;
       if(urls.isPresent()){
           url=urls.get();
       }
       else{
           throw new RuntimeException("url not found");
       }


       String origionalUrl=  url.getOrigionalUrl();
       redisTemplate.opsForValue().set(shortUrl,origionalUrl, Duration.ofHours(1));
       return origionalUrl+" From Database";

    }




}
