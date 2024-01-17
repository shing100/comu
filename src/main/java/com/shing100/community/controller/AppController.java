package com.shing100.community.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    @GetMapping("/health")
    public String heathCheck() {
        return "{\"status\": \"UP\"}";
    }

}
