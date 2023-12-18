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
        return "OK";
    }

    @GetMapping("/")
    public ResponseEntity<?> home() {
        HashMap<Object, Object> body = new HashMap<>();
        body.put("status", 200);
        body.put("title", "Community API");
        body.put("version", "1.0.0");

        HashMap<Object, Object> post = new HashMap<>();
        HashMap<Object, Object> get = new HashMap<>();

        get.put("profile", "/api/auth/user");
        get.put("health", "/health");
        post.put("authenticate", "/api/auth/authenticate");
        post.put("signup", "/api/auth/signup");

        Map<String, Object> links = new HashMap<>();
        links.put("self", "/");
        links.put("GET", get);
        links.put("POST", post);

        body.put("links", links);
        return ResponseEntity.ok(body);
    }
}
