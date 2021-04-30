package com.santo.vms.service.ifaces;

import com.santo.vms.dto.DepartmentDTO;
import com.santo.vms.dto.EmployeeDepartmentAssignmentDTO;
import com.santo.vms.model.Department;
import com.santo.vms.model.Employee;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department createDepartment(DepartmentDTO departmentDTO);
    Optional<Department> findDepartmentById(String id);
    List<Department> getAllDepartments();
    Department assignEmployees(EmployeeDepartmentAssignmentDTO employeeDTO);
}
