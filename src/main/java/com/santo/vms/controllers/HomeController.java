package com.santo.vms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage() {
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "next";
    }


    @GetMapping("/index-dark")
    public String homePageDark() {
        return "index-dark";
    }

    @GetMapping("/dashboard")
    public String displayDashboard(Model model){
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "VMS - Dashboard");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Dashboard");
        return "dashboard";
    }

    @GetMapping("/dummy")
    public String dummy(Model model){
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "Dummy Page");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Dummy");
        return "dummy";
    }
}
