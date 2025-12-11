package com.urlshortener2.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class URLs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String origionalUrl;
    private String shortUrl;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigionalUrl() {
        return origionalUrl;
    }

    public void setOrigionalUrl(String origionalUrl) {
        this.origionalUrl = origionalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
