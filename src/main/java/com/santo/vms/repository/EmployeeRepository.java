package com.santo.vms.repository;

import com.santo.vms.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findAllByDepartment(String id);
}
