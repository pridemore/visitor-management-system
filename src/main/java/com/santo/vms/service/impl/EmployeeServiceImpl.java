package com.santo.vms.service.impl;

import com.santo.vms.enums.Gender;
import com.santo.vms.model.Employee;
import com.santo.vms.dto.EmployeeDTO;
import com.santo.vms.repository.EmployeeRepository;
import com.santo.vms.service.ifaces.EmployeeService;
import com.santo.vms.utilities.enums.EntityStatus;
import com.santo.vms.utilities.util.GenerateKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(GenerateKey.generateEntityId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setEmployeeNo(employeeDTO.getEmployeeNo());
        employee.setEmail(employeeDTO.getEmail());
        employee.setNationalId(employeeDTO.getNationalId());
        employee.setPhoneNo(employeeDTO.getPhoneNo());
        employee.setStatus("ACTIVE");
        employee.setEntityVersion(1L);
        //Get DOB from DTO
        employee.setDob(LocalDate.now());
        employee.setGender(Gender.valueOf(employeeDTO.getGender()));
        employee.setDesignation(employeeDTO.getDesignation());

        employee.setEntityStatus(EntityStatus.ACTIVE);
        employee.setDateCreated(OffsetDateTime.now());
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    @Override
    public Optional<Employee> findEmployeeById(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public long getEmployeeCount() {
        final long count = employeeRepository.count();
        return count;
    }

    @Override
    public Employee updateEmployee(String id, EmployeeDTO employeeDTO) {

        Optional<Employee> employee=employeeRepository.findById(id);

//        if(!employee.isPresent())
//            return "";

        employee.get().setFirstName(employeeDTO.getFirstName());
        employee.get().setSurname(employeeDTO.getSurname());
        employee.get().setEmployeeNo(employeeDTO.getEmployeeNo());
        employee.get().setEmail(employeeDTO.getEmail());
        employee.get().setNationalId(employeeDTO.getNationalId());
        employee.get().setPhoneNo(employeeDTO.getPhoneNo());
        employee.get().setGender(Gender.valueOf(employeeDTO.getGender()));
        employee.get().setDesignation(employeeDTO.getDesignation());

        Employee updatedEmployee=employeeRepository.save(employee.get());
        return updatedEmployee;
    }

    @Override
    public String deleteEmployee(String id) {
        Optional<Employee> employee=employeeRepository.findById(id);

        if(!employee.isPresent())
            return "Employee not found";

        employee.get().setStatus("DELETED");

        Employee deletedEmployee=employeeRepository.save(employee.get());

        return "Employee deleted successfully";
    }
}
