package com.urlshortener2.repository;

import com.urlshortener2.model.URLs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URLs,Long> {
    Optional<URLs> findByShortUrl(String shortUrl);
    boolean existsByShortUrl(String shortUrl);

}
