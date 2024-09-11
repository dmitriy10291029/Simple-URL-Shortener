package com.example.url.shortener.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LinkCreateRequest {
    @JsonProperty(required = true)
    private String longUrl;
}
