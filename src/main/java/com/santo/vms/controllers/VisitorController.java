package com.santo.vms.controllers;

import com.santo.vms.dto.EmployeeDTO;
import com.santo.vms.dto.EmployeeDepartmentAssignmentDTO;
import com.santo.vms.dto.VisitorCheckInDTO;
import com.santo.vms.dto.VisitorDTO;
import com.santo.vms.model.Employee;
import com.santo.vms.model.VisitLog;
import com.santo.vms.model.Visitor;
import com.santo.vms.service.ifaces.EmployeeService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/visitors")
public class VisitorController {
    @Autowired
    VisitorService visitorService;

    @Autowired
    EmployeeService employeeService;

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
        model.addAttribute("title", "VMS - Check In Visitor");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Visitors");

        final List<Employee> employees = employeeService.getAllEmployees();
        final List<Visitor> visitors = visitorService.getAllVisitors();

        model.addAttribute("employees", employees);
        model.addAttribute("visitors", visitors);
        model.addAttribute("dto", new VisitorCheckInDTO());
        model.addAttribute("repeatDays", new ArrayList<String>());
        return "visitors/check_in";
    }


    @PostMapping("/check-in")
    public String postCheckInVisitor(@Valid VisitorCheckInDTO visitorDTO, Errors errors, Model model){
        log.info("Received DTO: "+ visitorDTO);
        if (errors.hasErrors()) {
            log.info("Errors:::" + errors.getAllErrors());
            model.addAttribute("error", errors);
            return "visitors/check_in";
        }

        final VisitLog visitLog = visitorService.checkInVisitor(visitorDTO);
        log.info("Visit Log ID " + visitLog.getId());
        return "redirect:/visitors/check-in/details/"+visitLog.getId();
    }

    @GetMapping("/check-in/details/{id}")
    public String showCheckInDetailsPage(Model model, @PathVariable(name = "id") String id) {

        final Optional<VisitLog> optionalVisitLog = visitorService.findVisitLogById(id);

        if(optionalVisitLog.isPresent()){
            //Attributes required for every page inheriting from general.html
            model.addAttribute("title", "VMS - Pending Check In Visitor");
            model.addAttribute("breadCrumbLeft", "Application");
            model.addAttribute("breadCrumbRight", "Visitors");
            //Other attributes
            model.addAttribute("visitLog", optionalVisitLog.get());
            return "visitors/check_in_details";
        }else {
            //Attributes required for every page inheriting from general.html
            model.addAttribute("title", "VMS - Pending Check In Visitor");
            model.addAttribute("breadCrumbLeft", "Application");
            model.addAttribute("breadCrumbRight", "Visitors");

            return "visitors/check_in_details";
        }

    }

    @GetMapping("/check-out")
    public String showCheckoutPage(Model model) {
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "VMS - Check Out Visitor");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Visitors");

        return "visitors/check_out";
    }

}
