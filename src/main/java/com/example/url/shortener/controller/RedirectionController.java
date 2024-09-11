package com.example.url.shortener.controller;

import com.example.url.shortener.model.Link;
import com.example.url.shortener.service.LinkCrudService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectionController {
    private final LinkCrudService service;

    @Autowired
    public RedirectionController(LinkCrudService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public void redirect(@PathVariable String code, HttpServletResponse response) {
        Link link = service.getLink(code);
        response.setStatus(302);
        response.setHeader("Location", link.getLongUrl());
    }
}
