package com.example.url.shortener.controller;

import com.example.url.shortener.controller.dto.LinkCreateRequest;
import com.example.url.shortener.controller.dto.LinkCreateResponse;
import com.example.url.shortener.model.Link;
import com.example.url.shortener.service.LinkCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/link")
public class LinkController {
    private final LinkCrudService service;

    @Autowired
    public LinkController(LinkCrudService service) {
        this.service = service;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public LinkCreateResponse create(@RequestBody LinkCreateRequest request) {
        Link link = service.createLink(request.getLongUrl());

        return LinkCreateResponse.builder().code(link.getCode()).build();
    }
}
