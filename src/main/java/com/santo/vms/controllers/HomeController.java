package com.santo.vms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "next";
    }
}
