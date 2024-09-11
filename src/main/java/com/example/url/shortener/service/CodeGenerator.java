package com.example.url.shortener.service;

import com.example.url.shortener.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class CodeGenerator {
    private final char[] DICT = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890-".toCharArray();
    private final int SIZE = 8;

    private final LinkRepository linkRepository;

    @Autowired
    public CodeGenerator(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public String getCode() {
        String code;
        do {
            code = generateCode();
        } while (linkRepository.findByCode(code).isPresent());

        return code;
    }

    private String generateCode() {
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            codeBuilder.append(DICT[ThreadLocalRandom.current().nextInt(DICT.length)]);
        }

        return codeBuilder.toString();
    }
}
