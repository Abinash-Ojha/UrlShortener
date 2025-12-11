package com.urlshortener2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimitorService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    private final int MAX_REQUESTS = 5;
    private final int WINDOW_SECONDS = 60;


    public boolean isAllowed(String userKey){

        String key="rate_limit:"+userKey;
        Long request=redisTemplate.opsForValue().increment(key);
        if(request==1){
            redisTemplate.expire(key, Duration.ofSeconds(WINDOW_SECONDS));
        }
        return request<=MAX_REQUESTS;

    }

}
