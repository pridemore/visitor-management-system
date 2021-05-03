package com.santo.vms.service.ifaces;

import com.santo.vms.model.Employee;
import com.santo.vms.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee addEmployee(EmployeeDTO employeeDTO);
    Employee updateEmployee(String id,EmployeeDTO employeeDTO);
    Optional<Employee> findEmployeeById(String id);
    List<Employee> getAllEmployees();
    long getEmployeeCount();
    String deleteEmployee(String id);

}
