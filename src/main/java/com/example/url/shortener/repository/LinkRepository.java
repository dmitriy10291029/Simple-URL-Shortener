package com.example.url.shortener.repository;

import com.example.url.shortener.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByCode(String code);
    Optional<Link> findByLongUrl(String longUrl);
}
