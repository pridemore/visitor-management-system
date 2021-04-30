package com.santo.vms.controllers;

import com.santo.vms.model.Employee;
import com.santo.vms.dto.EmployeeDTO;
import com.santo.vms.service.ifaces.EmployeeService;
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
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/add")
    public String showAddEmployeePage(Model model) {
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "VMS - Add Employee");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Employees");

        return "employees/create_employee";
    }

    @PostMapping("/add")
    public String postAddEmployee(@Valid EmployeeDTO employeeDTO, Errors errors){
        log.info("Received DTO: "+ employeeDTO);
        if (errors.hasErrors()) {
            return "employees/create_employee";
        }

        Employee employee = employeeService.addEmployee(employeeDTO);
        return "redirect:/employees/details/"+employee.getId();
    }

    @GetMapping("/details/{id}")
    public String viewEmployee(Model model, @PathVariable(name = "id") String id){
        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(id);
        if(optionalEmployee.isPresent()){
            //Attributes required for every page inheriting from general.html
            model.addAttribute("title", "VMS - View Employee");
            model.addAttribute("breadCrumbLeft", "Application");
            model.addAttribute("breadCrumbRight", "Employees");
            //Other attributes
            model.addAttribute("employee", optionalEmployee.get());
            return "employees/view_employee";
        }else {
            return "employees/view_employee";
        }
    }
    @GetMapping("/view")
    public String showAllEmployeesPage(Model model) {
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "VMS - View Employees");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Employees");

        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees/view_employees";
    }
}
