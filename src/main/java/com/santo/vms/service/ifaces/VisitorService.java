package com.santo.vms.service.ifaces;

import com.santo.vms.dto.DepartmentDTO;
import com.santo.vms.dto.EmployeeDepartmentAssignmentDTO;
import com.santo.vms.dto.VisitorDTO;
import com.santo.vms.model.Department;
import com.santo.vms.model.Visitor;

import java.util.List;
import java.util.Optional;

public interface VisitorService {
    Visitor createVisitor(VisitorDTO visitorDTO);
    Optional<Visitor> findVisitorById(String id);
    List<Visitor> getAllVisitors();
}
