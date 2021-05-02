package com.santo.vms.service.impl;

import antlr.StringUtils;
import com.santo.vms.dto.DepartmentDTO;
import com.santo.vms.dto.EditDepartmentDTO;
import com.santo.vms.dto.EmployeeDepartmentAssignmentDTO;
import com.santo.vms.model.Department;
import com.santo.vms.model.Employee;
import com.santo.vms.repository.DepartmentRepository;
import com.santo.vms.repository.EmployeeRepository;
import com.santo.vms.service.ifaces.DepartmentService;
import com.santo.vms.utilities.enums.EntityStatus;
import com.santo.vms.utilities.enums.SystemConstants;
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

    @Override
    public String updateDepartment(EditDepartmentDTO departmentDTO) {

        // Retrieve the Department from the DB
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentDTO.getEditId());

        final String updateStatus = optionalDepartment.map(department -> {
            department.setBuildingName(departmentDTO.getBuildingNameEdit());
            department.setFloorLevel(departmentDTO.getFloorLevelEdit());
            department.setName(departmentDTO.getNameEdit());
            department.setCode(departmentDTO.getCodeEdit());
            departmentRepository.save(department);
            return SystemConstants.UPDATED.getMessage();
        }).orElse(SystemConstants.NOT_FOUND.getMessage());
        log.info("UPDATE STATUS: {}", updateStatus);
        return updateStatus;

    }


    @Override
    public String deleteDepartment(String id) {

        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        final String status = optionalDepartment.map(department -> {
            //Check if there are employees assigned to the department
            if (!department.checkEmployeeAssignment()) {
                return "Department cannot be deleted, employees still assigned to the department";
            }
            // Proceed to delete the entity (ie soft delete)
            department.setStatus(EntityStatus.DELETED.name());
            department.setEntityStatus(EntityStatus.DELETED);
            departmentRepository.save(department);

            return SystemConstants.DELETED.getMessage();
        }).orElse(SystemConstants.NOT_FOUND.getMessage());

        // This is not necessary because the employees are contained in the Department object as it owns the relationship!
       //List<Employee> employeesAssignedToDepartment=employeeRepository.findAllByDepartment(optionalDepartment.get().getId());

        return status;
    }
}
