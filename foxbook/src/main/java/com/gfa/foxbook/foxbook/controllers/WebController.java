package com.gfa.foxbook.foxbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

 @GetMapping("/")
    public String index() {
        return "index.html";
    }
}