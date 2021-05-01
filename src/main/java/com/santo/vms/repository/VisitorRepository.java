package com.santo.vms.repository;

import com.santo.vms.model.Employee;
import com.santo.vms.model.Visitor;
import com.santo.vms.utilities.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, String> {
    long countVisitorByEntityStatus(EntityStatus entityStatus);
}
