package com.cumuluscycles.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
    @Value("${data}")
    private String data;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("data", data);
        return "index";
    }
}