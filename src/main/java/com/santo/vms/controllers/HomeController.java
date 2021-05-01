package com.santo.vms.controllers;

import com.santo.vms.service.ifaces.EmployeeService;
import com.santo.vms.service.ifaces.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    VisitorService visitorService;

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

        final long employeeCount = employeeService.getEmployeeCount();
        model.addAttribute("employeeCount",employeeCount);

        final long pendingCheckInCount = visitorService.getPendingCheckInCount();
        model.addAttribute("pendingCheckIns",pendingCheckInCount);

        final long registeredVisitorsCount = visitorService.getRegisteredVisitorsCount();
        model.addAttribute("registeredVisitors",registeredVisitorsCount);

        final long overdueCheckOutCount = visitorService.getOverdueCheckOutCount();
        model.addAttribute("overdueCheckOuts",overdueCheckOutCount );
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
