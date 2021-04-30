package com.santo.vms.service.impl;

import antlr.StringUtils;
import com.santo.vms.dto.DepartmentDTO;
import com.santo.vms.dto.EmployeeDepartmentAssignmentDTO;
import com.santo.vms.model.Department;
import com.santo.vms.model.Employee;
import com.santo.vms.repository.DepartmentRepository;
import com.santo.vms.repository.EmployeeRepository;
import com.santo.vms.service.ifaces.DepartmentService;
import com.santo.vms.utilities.enums.EntityStatus;
import com.santo.vms.utilities.util.GenerateKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Department createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setBuildingName(departmentDTO.getBuildingName());
        department.setFloorLevel(departmentDTO.getFloorLevel());
        department.setName(departmentDTO.getName());
        department.setCode(departmentDTO.getCode());
        department.setEntityStatus(EntityStatus.ACTIVE);
        department.setStatus("ACTIVE");
        department.setDateCreated(OffsetDateTime.now());
        department.setId(GenerateKey.generateEntityId());
        department.setEntityVersion(1L);
        Department savedDepartment = departmentRepository.save(department);

        return savedDepartment;
    }

    @Override
    public Optional<Department> findDepartmentById(String id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department assignEmployees(EmployeeDepartmentAssignmentDTO employeeDTO) {
        //Get the current department and the list of employees to append from DTO
        Department department = employeeDTO.getDepartment();
        List<Employee> employees = employeeDTO.getEmployees();
        log.info("Employee List from DTO -> {}", employees);

        // Get the current list of employees from DB
        List<Employee> departmentEmployees = department.getEmployees();
        log.info("Employee List from DB -> {}", departmentEmployees);
        departmentEmployees.addAll(employees);

        //Update the department List
        department.setEmployees(departmentEmployees);
        Department savedDepartment = departmentRepository.save(department);
        log.info("Updated Employee List from DB -> {}", savedDepartment.getEmployees());

        //Update the Employee object in the list
        departmentEmployees.forEach(employee -> {
            employee.setDepartment(department);
            employeeRepository.save(employee);
        });
        return savedDepartment;
    }
}
