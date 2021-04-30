package com.santo.vms.dto;

import com.santo.vms.model.Department;
import com.santo.vms.model.Employee;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDepartmentAssignmentDTO {
    private List<Employee> employees;
    private Department department;
}
