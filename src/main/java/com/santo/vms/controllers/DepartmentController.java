package com.santo.vms.controllers;

import com.santo.vms.dto.DepartmentDTO;
import com.santo.vms.dto.EmployeeDTO;
import com.santo.vms.dto.EmployeeDepartmentAssignmentDTO;
import com.santo.vms.model.Department;
import com.santo.vms.model.Employee;
import com.santo.vms.service.ifaces.DepartmentService;
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
@RequestMapping(value="/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/add")
    public String showAddDepartmentPage(Model model) {
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "VMS - Add Department");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Departments");

        return "departments/create_department";
    }

    @PostMapping("/add")
    public String postAddEmployee(@Valid DepartmentDTO departmentDTO, Errors errors){
        log.info("Received DTO: "+ departmentDTO);
        if (errors.hasErrors()) {
            return "departments/create_employee";
        }

        Department department = departmentService.createDepartment(departmentDTO);
        return "redirect:/departments/details/" + department.getId();
    }

    @GetMapping("/details/{id}")
    public String viewDepartment(Model model, @PathVariable(name = "id") String id){
        Optional<Department> optionalDepartment = departmentService.findDepartmentById(id);
        if(optionalDepartment.isPresent()){
            //Attributes required for every page inheriting from general.html
            model.addAttribute("title", "VMS - View Department");
            model.addAttribute("breadCrumbLeft", "Application");
            model.addAttribute("breadCrumbRight", "Departments");
            //Other attributes
            model.addAttribute("department", optionalDepartment.get());
            model.addAttribute("departmentEmployees", optionalDepartment.get().getEmployees());
            return "departments/view_department";
        }else {
            return "departments/view_department";
        }
    }
    @GetMapping("/view")
    public String showAllDepartmentsPage(Model model) {
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "VMS - View Employees");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Departments");

        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments/view_departments";
    }

    @GetMapping("/assign/{id}")
    public String showAssignEmployeeToDepartmentsPage(@PathVariable(name = "id") String id, Model model) {
        //Attributes required for every page inheriting from general.html
        model.addAttribute("title", "VMS - Assign Employee");
        model.addAttribute("breadCrumbLeft", "Application");
        model.addAttribute("breadCrumbRight", "Departments");
        Optional<Department> optionalDepartment = departmentService.findDepartmentById(id);
        model.addAttribute("department", optionalDepartment.get());
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("dto", new EmployeeDepartmentAssignmentDTO());
        return "departments/assign_employee";
    }

    @PostMapping("/assign")
    public String processAssignEmployeeToDepartment(Model model,
                                                    @Valid EmployeeDepartmentAssignmentDTO departmentAssignmentDTO){
        log.info("Received DTO: {}", departmentAssignmentDTO);
        Department department = departmentService.assignEmployees(departmentAssignmentDTO);

        return "redirect:/departments/details/"+department.getId();
    }
}
