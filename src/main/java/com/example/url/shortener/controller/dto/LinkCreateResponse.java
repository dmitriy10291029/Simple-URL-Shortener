package com.example.url.shortener.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinkCreateResponse {
    private String code;
}
