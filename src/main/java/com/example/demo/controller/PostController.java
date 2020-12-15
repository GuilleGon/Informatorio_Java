package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @GetMapping
    public List<String> getPosts(){
        List<String> post = new ArrayList<>();
        post.add("Java");
        post.add("Maven");
        return post;
    }
}
