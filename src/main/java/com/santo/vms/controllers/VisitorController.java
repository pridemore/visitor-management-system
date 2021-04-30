package com.santo.vms.controllers;

import com.santo.vms.dto.EmployeeDTO;
import com.santo.vms.dto.VisitorDTO;
import com.santo.vms.model.Employee;
import com.santo.vms.model.Visitor;
import com.santo.vms.service.ifaces.VisitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/visitors")
public class VisitorController {
    @Autowired
    VisitorService visitorService;

    @GetMapping("/add")
    public String showAddVisitorPage(Model model) {
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "VMS - Add Visitor");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Visitors");

        return "visitors/create_visitor";
    }

    @PostMapping("/add")
    public String postAddVisitor(@Valid VisitorDTO visitorDTO, Errors errors){
        log.info("Received DTO: "+ visitorDTO);
        if (errors.hasErrors()) {
            return "visitors/create_employee";
        }

        Visitor visitor = visitorService.createVisitor(visitorDTO);
        return "redirect:/visitors/details/"+visitor.getId();
    }

    @GetMapping("/details/{id}")
    public String viewVisitor(Model model, @PathVariable(name = "id") String id){
        Optional<Visitor> optionalVisitor = visitorService.findVisitorById(id);
        if(optionalVisitor.isPresent()){
            //Attributes required for every page inheriting from general.html
            model.addAttribute("title", "VMS - View Visitor");
            model.addAttribute("breadCrumbLeft", "Application");
            model.addAttribute("breadCrumbRight", "Visitors");
            //Other attributes
            model.addAttribute("visitor", optionalVisitor.get());
            return "visitors/view_visitor";
        }else {
            return "visitors/view_visitor";
        }
    }

    @GetMapping("/view")
    public String showAllVisitorsPage(Model model) {
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "VMS - View Visitors");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Visitors");

        List<Visitor> visitors = visitorService.getAllVisitors();
        model.addAttribute("visitors", visitors);
        return "visitors/view_visitors";
    }


    @GetMapping("/check-in")
    public String showCheckInPage(Model model) {
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "VMS - Add Visitor");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Visitors");
        return "visitors/check_in";
    }

    @GetMapping("/check-out")
    public String showCheckoutPage(Model model) {
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "VMS - Add Visitor");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Visitors");

        return "visitors/check_out";
    }

}
